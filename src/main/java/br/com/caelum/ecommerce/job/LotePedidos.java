package br.com.caelum.ecommerce.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import br.com.caelum.ecommerce.dominio.NotaFiscal;
import br.com.caelum.ecommerce.dominio.Pedido;
import br.com.caelum.ecommerce.integracao.GeradorNotaFiscal;

public class LotePedidos implements Callable<List<NotaFiscal>> {

	private final List<Pedido> pedidos;
	private final GeradorNotaFiscal geradorNotaFiscal;
	
	public LotePedidos(List<Pedido> pedidos, GeradorNotaFiscal geradorNotaFiscal) {
		this.pedidos = pedidos;
		this.geradorNotaFiscal = geradorNotaFiscal;
	}

	@Override
	public List<NotaFiscal> call() throws Exception {
		List<NotaFiscal> notas = new ArrayList<>();
		for(Pedido pedido : pedidos) {
			notas.add(geradorNotaFiscal.gerar(pedido));
		}
		return notas;
	}

}
