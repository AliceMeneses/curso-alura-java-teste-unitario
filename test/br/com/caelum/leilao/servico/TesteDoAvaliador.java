package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

		assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
		assertThat(leiloeiro.getMenorValor(), equalTo(250.0));
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		
		Leilao leilao = new CriadorDeLeilao().para("Xbox").lance(alice, 4000).constroi();
		
		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMaiorLance(), equalTo(4000.0));
		assertThat(leiloeiro.getMenorValor(), equalTo(4000.0));

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
		
		assertThat(maiores, hasItems(
				new Lance(ricardo,7000),
				new Lance(alice,5500),
				new Lance(ricardo,5000)
				));


	}
	
	@Test
	public void deveEncontrarOsMaioresLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Chocolate").lance(alice, 10).lance(ricardo, 30).constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(2, maiores.size());

		assertThat(maiores, hasItems(
				new Lance(ricardo,30),
				new Lance(alice,10)
				));

	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemNenhumLanceDado() {
		Leilao leilao = new CriadorDeLeilao().para("mouse").constroi();
		
		leiloeiro.avalia(leilao);
	}

}
