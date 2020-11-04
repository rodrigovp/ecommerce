package br.com.caelum.ecommerce.dominio;

import static br.com.caelum.ecommerce.dominio.SystemUtils.REAIS;

import java.util.ArrayList;
import java.util.List;

import org.javamoney.moneta.Money;

public class RepositorioPedidos {
	
	public List<Pedido> buscarTodosOsPedidosDeHoje(){
		int totalDePedidos = 2000;
		List<Pedido> pedidos = new ArrayList<>(totalDePedidos);
		
		for(int i = 0; i < totalDePedidos; i++) {
			Pedido pedido = new Pedido();
			pedido.adicionar(new ItemPedido(Money.of(10, REAIS)));
			pedido.adicionar(new ItemPedido(Money.of(11, REAIS)));
			pedido.adicionar(new ItemPedido(Money.of(12, REAIS)));
			pedidos.add(pedido);
		}
		return pedidos;
	}
}
