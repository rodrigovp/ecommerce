package br.caelum.ecommerce.dominio.jobs;

import java.util.ArrayList;
import java.util.List;

import br.caelum.ecommerce.dominio.NotaFiscal;
import br.caelum.ecommerce.dominio.Pedido;
import br.caelum.ecommerce.dominio.RepositorioPedidos;
import br.caelum.ecommerce.integracao.GeradorNotaFiscal;

public class RotinaNoturna {
	
	private final RepositorioPedidos repositorioPedidos;
	private final GeradorNotaFiscal geradorNotaFiscal;
	
	public RotinaNoturna(RepositorioPedidos repositorioPedidos, GeradorNotaFiscal geradorNotaFiscal) {
		this.repositorioPedidos = repositorioPedidos;
		this.geradorNotaFiscal = geradorNotaFiscal;
	}
	
	public void gerarNotasFiscais() {
		List<Pedido> pedidosDoDia = repositorioPedidos.buscarTodosOsPedidosDeHoje();
		List<NotaFiscal> notasFiscais = new ArrayList<>();
		pedidosDoDia.forEach(pedido -> {
			NotaFiscal nota = geradorNotaFiscal.gerar(pedido);
			notasFiscais.add(nota);
		});
	}
}
