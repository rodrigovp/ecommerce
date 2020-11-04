package br.com.caelum.ecommerce.job;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.ecommerce.dominio.NotaFiscal;
import br.com.caelum.ecommerce.dominio.Pedido;
import br.com.caelum.ecommerce.integracao.GeradorNotaFiscal;

public class RotinaDiurnaSequencial implements RotinaDiurna {
	
	private final GeradorNotaFiscal geradorNotaFiscal;
	
	public RotinaDiurnaSequencial(GeradorNotaFiscal geradorNotaFiscal) {
		this.geradorNotaFiscal = geradorNotaFiscal;
	}
	
	@Override
	public List<NotaFiscal> gerarNotasFiscais(List<Pedido> pedidosDoDia) {
		List<NotaFiscal> notasFiscais = new ArrayList<>();
		pedidosDoDia.forEach(pedido -> {
			NotaFiscal nota = geradorNotaFiscal.gerar(pedido);
			notasFiscais.add(nota);
		});
		return notasFiscais;
	}
}
