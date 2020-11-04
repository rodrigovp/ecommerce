package br.com.caelum.ecommerce.job;

import java.util.List;

import br.com.caelum.ecommerce.dominio.NotaFiscal;
import br.com.caelum.ecommerce.dominio.Pedido;

public interface RotinaDiurna {

	List<NotaFiscal> gerarNotasFiscais(List<Pedido> pedidosDoDia);

}