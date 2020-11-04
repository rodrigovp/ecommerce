package br.com.caelum.ecommerce.dominio;

import static br.com.caelum.ecommerce.dominio.SystemUtils.REAIS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NotaFiscalTest {

	@Test
	public void calcularValor() {
		Pedido pedido = Mockito.mock(Pedido.class);
		when(pedido.lerValorTotal()).thenReturn(Money.of(5, REAIS));
		Money umReal = Money.of(1, REAIS);
		
		NotaFiscal notaFiscal = new NotaFiscal(pedido, umReal, umReal);
		
		assertThat(notaFiscal.lerValor(), is(equalTo(Money.of(7, REAIS))));
	}
}
