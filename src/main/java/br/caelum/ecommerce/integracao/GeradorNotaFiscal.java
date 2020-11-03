package br.caelum.ecommerce.integracao;

import org.javamoney.moneta.Money;

import br.caelum.ecommerce.dominio.NotaFiscal;
import br.caelum.ecommerce.dominio.Pedido;

public class GeradorNotaFiscal {

	private final CalculadoraImposto calculadoraImposto;
	private final CalculadoraFrete calculadoraFrete;
	
	public GeradorNotaFiscal(CalculadoraImposto calculadoraImposto, CalculadoraFrete calculadoraFrete) {
		this.calculadoraImposto = calculadoraImposto;
		this.calculadoraFrete = calculadoraFrete;
	}

	public NotaFiscal gerar(Pedido pedido) {
		Money imposto = calculadoraImposto.calcularComBaseEm(pedido);
		Money frete = calculadoraFrete.calcularComBaseEm(pedido);
		return new NotaFiscal(pedido, imposto, frete);
	}

	
}
