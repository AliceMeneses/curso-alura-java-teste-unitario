package br.com.caelum.leilao.servico;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		// parte 1: cenario
		Usuario alice = new Usuario("Alice");
		Usuario ricardo = new Usuario("Ricardo");
		Usuario joao = new Usuario("Jo�o");

		Leilao leilao = new Leilao("Playstation 5");

		leilao.propoe(new Lance(alice, 250));
		leilao.propoe(new Lance(ricardo, 300));
		leilao.propoe(new Lance(joao, 400));

		// parte 2: a��o
		Avaliador leiloeiro = new Avaliador();

		leiloeiro.avalia(leilao);

		// parte 3: validacao

		double maiorEsperado = 400;
		double menorEsperado = 250;

		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorValor(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {

		Usuario alice = new Usuario("alice");
		Leilao leilao = new Leilao("Xbox");

		leilao.propoe(new Lance(alice, 4000));
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		Assert.assertEquals(4000, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(4000, leiloeiro.getMenorValor(), 0.0001);

	}

}
