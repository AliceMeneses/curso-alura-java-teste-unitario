package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	double maiorValor = Double.NEGATIVE_INFINITY;
	double menorValor = Double.POSITIVE_INFINITY;
	List<Lance> maiores;

	public void avalia(Leilao leilao) {
		
		if(leilao.getLances().size() == 0) {
			throw new RuntimeException("Não é possível avaliar leilão sem lances!");
		}

		for (Lance lance : leilao.getLances()) {

			if (lance.getValor() > maiorValor) {
				maiorValor = lance.getValor();
			}
			if (lance.getValor() < menorValor) {
				menorValor = lance.getValor();
			}
		}
		
		pegaOsMaioresNo(leilao);

	}

	private void pegaOsMaioresNo(Leilao leilao) {
		maiores = new ArrayList<>(leilao.getLances());

		maiores.sort((lance1, lance2) -> {
			if (lance1.getValor() < lance2.getValor()) {
				return 1;
			}

			if (lance1.getValor() > lance2.getValor()) {
				return -1;
			}

			return 0;
		});

		maiores = maiores.subList(0, maiores.size() >= 3 ? 3 : maiores.size());
	}

	public double getMaiorLance() {
		return maiorValor;
	}

	public double getMenorValor() {
		return menorValor;
	}

	public List<Lance> getTresMaiores() {
		return maiores;
	}
}
