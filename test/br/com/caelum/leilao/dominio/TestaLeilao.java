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
		assertEquals(30, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void deveReceberVariosLances() {
		Usuario alice = new Usuario("Alice");
		Usuario joao = new Usuario("João");

		Leilao leilao = new Leilao("carro");

		leilao.propoe(new Lance(joao, 50000));
		leilao.propoe(new Lance(alice, 70000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(50000, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(70000, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	public void naoDeveAceitarLancesSeguidosDoMesmoUsuario() {
		
		Usuario alice = new Usuario("Alice");
		
		Leilao leilao = new Leilao("macbook");
		
		leilao.propoe(new Lance(alice, 2500));
		leilao.propoe(new Lance(alice, 3000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2500, leilao.getLances().get(0).getValor(), 0.00001);

	}
}
