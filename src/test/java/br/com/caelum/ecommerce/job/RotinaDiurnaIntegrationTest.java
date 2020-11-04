package br.com.caelum.ecommerce.job;

import static br.com.caelum.ecommerce.dominio.SystemUtils.REAIS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.ArrayList;
import java.util.List;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import br.com.caelum.ecommerce.dominio.ItemPedido;
import br.com.caelum.ecommerce.dominio.Pedido;
import br.com.caelum.ecommerce.integracao.CalculadoraFrete;
import br.com.caelum.ecommerce.integracao.CalculadoraImposto;
import br.com.caelum.ecommerce.integracao.GeradorNotaFiscal;

public class RotinaDiurnaIntegrationTest {

	private GeradorNotaFiscal gerador;
	
	@BeforeEach
	public void setUp() {
		gerador = 
			new GeradorNotaFiscal(new CalculadoraImposto(), new CalculadoraFrete());
	}
	
	@Test
	@Timeout(value = 25, unit = SECONDS)
	public void gerarDiversasNotas() {
		RotinaDiurna rotina = new RotinaDiurnaSequencial(gerador);
		
		rotina.gerarNotasFiscais(pedidos());
	}
	
	@Test
	public void gerarDiversasNotasEmParalelo() {
		RotinaDiurna rotina = new RotinaDiurnaParalela(gerador);
		
		rotina.gerarNotasFiscais(pedidos());
	}
	
	private List<Pedido> pedidos(){
		int qtdPedidos = 500;
		List<Pedido> pedidos = new ArrayList<>(qtdPedidos);
		for(int i = 0; i < qtdPedidos; i ++) {
			pedidos.add(pedido());
		}
		return pedidos;
	}
	
	private Pedido pedido() {
		Pedido pedido = new Pedido();
		pedido.adicionar(new ItemPedido(Money.of(10, REAIS)));
		return pedido;
	}
}
