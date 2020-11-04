package br.com.caelum.ecommerce.job;

import static br.com.caelum.ecommerce.dominio.SystemUtils.REAIS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.caelum.ecommerce.dominio.ItemPedido;
import br.com.caelum.ecommerce.dominio.NotaFiscal;
import br.com.caelum.ecommerce.dominio.Pedido;
import br.com.caelum.ecommerce.integracao.GeradorNotaFiscal;

public class RotinaDiurnaSequencialTest {

	private RotinaDiurna rotinaDiurna;
	private GeradorNotaFiscal geradorNotaFiscal;
	
	@BeforeEach
	public void setUp() {
		geradorNotaFiscal = mock(GeradorNotaFiscal.class);
		rotinaDiurna = new RotinaDiurnaSequencial(geradorNotaFiscal);
	}
	
	@Test
	public void gerarNotasFiscais() {
		NotaFiscal notaEsperada = new NotaFiscal(pedido(), Money.of(2, REAIS), Money.of(2, REAIS));
		
		when(geradorNotaFiscal.gerar(any(Pedido.class))).thenReturn(notaEsperada);
		
		List<NotaFiscal> notasFiscais = rotinaDiurna.gerarNotasFiscais(pedidos());
		
		assertThat(notasFiscais, contains(notaEsperada));
	}
	
	private List<Pedido> pedidos(){
		return Arrays.asList(pedido());
	}
	
	private Pedido pedido() {
		Pedido pedido = new Pedido();
		pedido.adicionar(new ItemPedido(Money.of(10, REAIS)));
		return pedido;
	}
	
}
