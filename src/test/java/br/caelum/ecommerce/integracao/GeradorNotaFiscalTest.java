package br.caelum.ecommerce.integracao;

import static br.caelum.ecommerce.dominio.SystemUtils.REAIS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.caelum.ecommerce.dominio.NotaFiscal;
import br.caelum.ecommerce.dominio.Pedido;

public class GeradorNotaFiscalTest {
	
	private GeradorNotaFiscal gerador;
	private CalculadoraImposto calculadoraImposto;
	private CalculadoraFrete calculadoraFrete;
	
	@BeforeEach
	public void setUp() {
		calculadoraImposto = mock(CalculadoraImposto.class);
		calculadoraFrete = mock(CalculadoraFrete.class);
		gerador = new GeradorNotaFiscal(calculadoraImposto, calculadoraFrete);
	}
	
	@Test
	public void gerarNotaFiscalDePedidoDe10Reais() {
		Pedido pedido = mock(Pedido.class);
		
		when(pedido.lerValorTotal()).thenReturn(Money.of(10, REAIS));
		when(calculadoraFrete.calcularComBaseEm(pedido)).thenReturn(Money.of(1, REAIS));
		when(calculadoraImposto.calcularComBaseEm(pedido)).thenReturn(Money.of(2, REAIS));
		
		NotaFiscal nota = gerador.gerar(pedido);
		
		assertThat(nota.lerValor(), is(equalTo(Money.of(13, REAIS))));
	}
}
