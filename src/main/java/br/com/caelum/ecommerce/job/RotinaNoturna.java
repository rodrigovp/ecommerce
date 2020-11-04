package br.com.caelum.ecommerce.job;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.ecommerce.dominio.NotaFiscal;
import br.com.caelum.ecommerce.dominio.Pedido;
import br.com.caelum.ecommerce.dominio.RepositorioPedidos;
import br.com.caelum.ecommerce.integracao.GeradorNotaFiscal;

public class RotinaNoturna {
	
	private final RepositorioPedidos repositorioPedidos;
	private final GeradorNotaFiscal geradorNotaFiscal;
	
	public RotinaNoturna(RepositorioPedidos repositorioPedidos, GeradorNotaFiscal geradorNotaFiscal) {
		this.repositorioPedidos = repositorioPedidos;
		this.geradorNotaFiscal = geradorNotaFiscal;
	}
	
	public List<NotaFiscal> gerarNotasFiscais() {
		List<Pedido> pedidosDoDia = repositorioPedidos.buscarTodosOsPedidosDeHoje();
		List<NotaFiscal> notasFiscais = new ArrayList<>();
		pedidosDoDia.forEach(pedido -> {
			NotaFiscal nota = geradorNotaFiscal.gerar(pedido);
			notasFiscais.add(nota);
		});
		return notasFiscais;
	}
}
