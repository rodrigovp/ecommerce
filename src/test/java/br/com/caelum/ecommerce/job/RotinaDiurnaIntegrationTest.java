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
	@Timeout(value = 5, unit = SECONDS)
	public void gerarDiversasNotas() {
		GeradorNotaFiscal gerador = 
				new GeradorNotaFiscal(new CalculadoraImposto(), new CalculadoraFrete());
		RepositorioPedidos repositorio = new RepositorioPedidos();
		
		RotinaDiurna rotina = new RotinaDiurna(repositorio, gerador);
		
		rotina.gerarNotasFiscais();
	}
}
