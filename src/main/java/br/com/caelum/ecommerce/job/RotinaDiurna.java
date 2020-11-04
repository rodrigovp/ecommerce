package br.com.caelum.ecommerce.job;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.ecommerce.dominio.NotaFiscal;
import br.com.caelum.ecommerce.dominio.Pedido;
import br.com.caelum.ecommerce.dominio.RepositorioPedidos;
import br.com.caelum.ecommerce.integracao.GeradorNotaFiscal;

public class RotinaDiurna {
	
	private final GeradorNotaFiscal geradorNotaFiscal;
	private final RepositorioPedidos repositorioPedidos;
	
	public RotinaDiurna(RepositorioPedidos repositorioPedidos, GeradorNotaFiscal geradorNotaFiscal) {
		this.repositorioPedidos = repositorioPedidos;
		this.geradorNotaFiscal = geradorNotaFiscal;
	}
	
	public List<NotaFiscal> gerarNotasFiscais() {
		List<Pedido> pedidosDoDia = repositorioPedidos.buscarTodosOsPedidosDeHoje();
		System.out.println(String.format("Foram obtidos %d pedidos", pedidosDoDia.size()));
		
		List<NotaFiscal> notasFiscais = new ArrayList<>();
		pedidosDoDia.forEach(pedido -> {
			NotaFiscal nota = geradorNotaFiscal.gerar(pedido);
			notasFiscais.add(nota);
		});
		return notasFiscais;
	}
}
