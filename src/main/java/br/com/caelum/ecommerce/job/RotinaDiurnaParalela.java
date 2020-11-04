package br.com.caelum.ecommerce.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.collections4.ListUtils;

import br.com.caelum.ecommerce.dominio.NotaFiscal;
import br.com.caelum.ecommerce.dominio.Pedido;
import br.com.caelum.ecommerce.integracao.GeradorNotaFiscal;

public class RotinaDiurnaParalela implements RotinaDiurna {

	private final GeradorNotaFiscal gerador;
	
	public RotinaDiurnaParalela(GeradorNotaFiscal gerador) {
		this.gerador = gerador;
	}
	
	@Override
	public List<NotaFiscal> gerarNotasFiscais(List<Pedido> pedidosDoDia) {
		ExecutorService e = Executors.newFixedThreadPool(10);
		List<LotePedidos> lotesPedidos = gerarLotesPedidos(pedidosDoDia);
		
		List<NotaFiscal> notasFiscais = new ArrayList<>();
		try {
			List<Future<List<NotaFiscal>>> futures = e.invokeAll(lotesPedidos);
			for(Future<List<NotaFiscal>> future : futures) {
				notasFiscais.addAll(future.get());
			}
				
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return notasFiscais;
	}

	private List<LotePedidos> gerarLotesPedidos(List<Pedido> pedidosDoDia) {
		List<List<Pedido>> listasPedidos = ListUtils.partition(pedidosDoDia, 10);
		List<LotePedidos> lotes = new ArrayList<>();
		
		for(List<Pedido> pedidos : listasPedidos) {
			lotes.add(new LotePedidos(pedidos, gerador));
		}
		return lotes;
	}

}
