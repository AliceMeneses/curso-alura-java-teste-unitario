package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	
	double maiorValor = Double.NEGATIVE_INFINITY;
	double menorValor = Double.POSITIVE_INFINITY;
	
	public void avalia(Leilao leilao) {		
		
		for(Lance lance : leilao.getLances()) {
			
			if(lance.getValor() > maiorValor) {
				maiorValor = lance.getValor();
			}
			else {
				if(lance.getValor() < menorValor) {
					menorValor = lance.getValor();
				}
			}
		}
	}
	
	public double getMaiorLance() {
		return maiorValor;
	}
	
	public double getMenorValor() {
		return menorValor;
	}
}
