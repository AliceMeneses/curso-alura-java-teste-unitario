package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class TesteDoAvaliador {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario ricardo;
	private Usuario alice;

	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		
		alice = new Usuario("Alice");
		ricardo = new Usuario("Ricardo");
		joao = new Usuario("João");
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		// parte 1: cenario


		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(alice, 250));
		leilao.propoe(new Lance(ricardo, 300));
		leilao.propoe(new Lance(joao, 400));

		// parte 2: ação
		leiloeiro.avalia(leilao);

		// parte 3: validacao

		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, leiloeiro.getMenorValor(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {

		Leilao leilao = new Leilao("Xbox");

		leilao.propoe(new Lance(alice, 4000));
		leiloeiro.avalia(leilao);

		assertEquals(4000, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(4000, leiloeiro.getMenorValor(), 0.0001);

	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		
		Leilao leilao = new Leilao("Xbox");
		
		leilao.propoe(new Lance(alice, 4000));
		leilao.propoe(new Lance(ricardo, 5000));
		leilao.propoe(new Lance(alice, 5500));
		leilao.propoe(new Lance(ricardo, 7000));
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		
		assertEquals(7000, maiores.get(0).getValor(), 0.0001);
		assertEquals(5500, maiores.get(1).getValor(), 0.0001);
		assertEquals(5000, maiores.get(2).getValor(), 0.0001);


	}
	
	@Test
	public void deveEncontrarOsMaioresLances() {
		
		Leilao leilao = new Leilao("Chocolate");
		
		leilao.propoe(new Lance(alice, 10));
		leilao.propoe(new Lance(ricardo, 30));
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(2, maiores.size());
		
		assertEquals(30, maiores.get(0).getValor(), 0.0001);
		assertEquals(30, maiores.get(0).getValor(), 0.0001);		

	}

}
