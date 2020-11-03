package br.caelum.ecommerce.dominio;

import org.javamoney.moneta.Money;

public class ItemPedido {
	
	private final Money valor;
	
	public ItemPedido(Money valor) {
		this.valor = valor;
	}
	
	public Money lerValor() {
		return valor;
	}
}
