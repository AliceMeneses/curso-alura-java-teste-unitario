package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	public static void main(String[] args) {
		
		//parte 1: cenario
		Usuario alice = new Usuario("Alice");
		Usuario ricardo = new Usuario("Ricardo");
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("Playstation 5");
		
		leilao.propoe(new Lance (alice, 250));
		leilao.propoe(new Lance(ricardo, 300));
		leilao.propoe(new Lance(joao, 400));
		
		//parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		//parte 3: validacao
		System.out.println(leiloeiro.getMaiorLance());
		System.out.println(leiloeiro.getMenorValor());
	}

}
