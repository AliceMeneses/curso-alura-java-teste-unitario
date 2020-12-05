package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class TestaLeilao {

	@Test
	public void deveReceberUmLance() {
		
		Usuario alice = new Usuario("Alice");
		
		Leilao leilao = new Leilao("Livro");
		
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(alice, 30));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(30, leilao.getLances().get(0).getValor(), 0.0001);
	}

}
