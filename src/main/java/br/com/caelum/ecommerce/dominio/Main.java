package br.com.caelum.ecommerce.dominio;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.ecommerce.integracao.CalculadoraFrete;
import br.com.caelum.ecommerce.integracao.CalculadoraImposto;
import br.com.caelum.ecommerce.integracao.GeradorNotaFiscal;

public class Main {
	
	public static void main(String ...args) {
		RepositorioPedidos repositorioPedidos = new RepositorioPedidos();
		GeradorNotaFiscal geradorNotaFiscal = new GeradorNotaFiscal(new CalculadoraImposto(), new CalculadoraFrete());
		
		System.out.println("Inicio...");
		long inicio = System.currentTimeMillis();
		
		List<Pedido> pedidosDoDia = repositorioPedidos.buscarTodosOsPedidosDeHoje();
		List<NotaFiscal> notasFiscais = new ArrayList<>();
		pedidosDoDia.forEach(pedido -> {
			NotaFiscal nota = geradorNotaFiscal.gerar(pedido);
			notasFiscais.add(nota);
		});
		
		long fim = System.currentTimeMillis() - inicio;
		System.out.println("Fim! Tempo: " + fim / 1000 + " segundos");
	}
}
