package br.caelum.ecommerce.dominio;

import static br.caelum.ecommerce.dominio.SystemUtils.REAIS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

public class PedidoTestt {
	
	@Test
	public void lerValorTotal() {
		Pedido pedido = new Pedido();
		pedido.adicionar(new ItemPedido(Money.of(5, REAIS)));
		pedido.adicionar(new ItemPedido(Money.of(5, REAIS)));
		
		assertThat(pedido.lerValorTotal(), is(equalTo(Money.of(10, REAIS))));
	}
}
