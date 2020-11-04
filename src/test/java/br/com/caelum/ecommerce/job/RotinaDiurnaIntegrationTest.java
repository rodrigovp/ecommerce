package br.com.caelum.ecommerce.job;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import br.com.caelum.ecommerce.dominio.RepositorioPedidos;
import br.com.caelum.ecommerce.integracao.CalculadoraFrete;
import br.com.caelum.ecommerce.integracao.CalculadoraImposto;
import br.com.caelum.ecommerce.integracao.GeradorNotaFiscal;

public class RotinaDiurnaIntegrationTest {

	@Test
	@Timeout(value = 25, unit = SECONDS)
	public void gerarDiversasNotas() {
		RepositorioPedidos repositorioPedidos = new RepositorioPedidos();
		GeradorNotaFiscal gerador = 
				new GeradorNotaFiscal(new CalculadoraImposto(), new CalculadoraFrete());
		
		RotinaDiurna rotina = new RotinaDiurna(repositorioPedidos, gerador);
		
		rotina.gerarNotasFiscais();
	}
}
