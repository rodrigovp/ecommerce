package br.com.caelum.ecommerce.dominio;

import static java.lang.String.format;

import java.util.List;

import br.com.caelum.ecommerce.integracao.CalculadoraFrete;
import br.com.caelum.ecommerce.integracao.CalculadoraImposto;
import br.com.caelum.ecommerce.integracao.GeradorNotaFiscal;
import br.com.caelum.ecommerce.job.RotinaDiurna;

public class Main {
	
	public static void main(String ...args) {
		RepositorioPedidos repositorioPedidos = new RepositorioPedidos();
		GeradorNotaFiscal geradorNotaFiscal = new GeradorNotaFiscal(new CalculadoraImposto(), new CalculadoraFrete());
		RotinaDiurna rotinaDiurna = new RotinaDiurna(geradorNotaFiscal);
		
		
		System.out.println("Inicio...");
		long inicio = System.currentTimeMillis();
		
		List<Pedido> pedidosDoDia = repositorioPedidos.buscarTodosOsPedidosDeHoje();
		System.out.println(String.format("Foram obtidos %d pedidos", pedidosDoDia.size()));
		
		List<NotaFiscal> notasFiscais = rotinaDiurna.gerarNotasFiscais(pedidosDoDia);
		
		long fim = System.currentTimeMillis() - inicio;
		System.out.println("Fim! Tempo: " + fim / 1000 + " segundos");
		System.out.println(format("Notas geradas: %s", notasFiscais));
	}
}
