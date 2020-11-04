package br.com.caelum.ecommerce.integracao;

import static br.com.caelum.ecommerce.dominio.SystemUtils.REAIS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.caelum.ecommerce.dominio.Pedido;

public class CalculadoraImpostoTest {
	
	private CalculadoraImposto calculadora;
	
	@BeforeEach
	public void setUp() {
		calculadora = new CalculadoraImposto();
	}
	
	@Test
	public void testeDoCalculo() {
		Pedido pedido = mock(Pedido.class);
		when(pedido.lerValorTotal()).thenReturn(Money.of(200, REAIS));
		
		Money frete = calculadora.calcularComBaseEm(pedido);
		
		assertThat(frete, is(equalTo(Money.of(4, REAIS))));
	}
}
