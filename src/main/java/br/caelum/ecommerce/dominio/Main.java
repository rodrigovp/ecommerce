package br.caelum.ecommerce.dominio;

import br.caelum.ecommerce.dominio.jobs.RotinaNoturna;
import br.caelum.ecommerce.integracao.CalculadoraFrete;
import br.caelum.ecommerce.integracao.CalculadoraImposto;
import br.caelum.ecommerce.integracao.GeradorNotaFiscal;

public class Main {
	
	public static void main(String ...args) {
		RepositorioPedidos repositorioPedidos = new RepositorioPedidos();
		GeradorNotaFiscal geradorNotaFiscal = new GeradorNotaFiscal(new CalculadoraImposto(), new CalculadoraFrete());
		RotinaNoturna rotina = new RotinaNoturna(repositorioPedidos, geradorNotaFiscal);
		
		System.out.println("Inicio...");
		long inicio = System.currentTimeMillis();
		rotina.gerarNotasFiscais();
		long fim = System.currentTimeMillis() - inicio;
		System.out.println("Fim! Tempo: " + fim / 1000 + " segundos");
	}
}
