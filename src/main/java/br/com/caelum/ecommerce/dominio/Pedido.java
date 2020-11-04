package br.com.caelum.ecommerce.dominio;

import static br.com.caelum.ecommerce.dominio.SystemUtils.REAIS;

import java.util.ArrayList;
import java.util.List;

import org.javamoney.moneta.Money;

public class Pedido {

	private final List<ItemPedido> itens;
	
	public Pedido() {
		this.itens = new ArrayList<>();
	}
	
	public void adicionar(ItemPedido item) {
		this.itens.add(item);
	}
	
	public Money lerValorTotal() {
		return itens.stream()
				.map(ItemPedido::lerValor)
				.reduce(Money.of(0, REAIS), Money::add);
	}

}
