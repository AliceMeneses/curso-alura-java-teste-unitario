package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
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
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
				.lance(alice, 250)
				.lance(ricardo, 300)
				.lance(joao, 400)
				.constroi();

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
		
		Leilao leilao = new CriadorDeLeilao().para("Xbox").lance(alice, 4000).constroi();
		
		leiloeiro.avalia(leilao);

		assertEquals(4000, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(4000, leiloeiro.getMenorValor(), 0.0001);

	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Xbox")
				.lance(alice, 4000)
				.lance(ricardo, 5000)
				.lance(alice, 5500)
				.lance(ricardo, 7000)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		
		assertEquals(7000, maiores.get(0).getValor(), 0.0001);
		assertEquals(5500, maiores.get(1).getValor(), 0.0001);
		assertEquals(5000, maiores.get(2).getValor(), 0.0001);


	}
	
	@Test
	public void deveEncontrarOsMaioresLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Chocolate").lance(alice, 10).lance(ricardo, 30).constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(2, maiores.size());
		
		assertEquals(30, maiores.get(0).getValor(), 0.0001);
		assertEquals(30, maiores.get(0).getValor(), 0.0001);		

	}

}
