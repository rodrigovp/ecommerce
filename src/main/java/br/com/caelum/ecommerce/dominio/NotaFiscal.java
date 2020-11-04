package br.com.caelum.ecommerce.dominio;

import org.javamoney.moneta.Money;

public class NotaFiscal {

	private final Money valorDoPedido;
	private final Money imposto;
	private final Money frete;
	
	public NotaFiscal(Pedido pedido, Money imposto, Money frete) {
		this.valorDoPedido = pedido.lerValorTotal();
		this.imposto = imposto;
		this.frete = frete;
	}

	public Money lerValor() {
		return valorDoPedido.add(imposto).add(frete);
	}

}
