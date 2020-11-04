package br.com.caelum.ecommerce.integracao;

import org.javamoney.moneta.Money;

import br.com.caelum.ecommerce.dominio.Pedido;

public class CalculadoraImposto {
	
	/**
	 * Retorna sempre 2% do valor do pedido
	 * 
	 * @param pedido
	 * @return
	 */
	public Money calcularComBaseEm(Pedido pedido) {
		return pedido.lerValorTotal().divide(100).multiply(2);
	}

}
