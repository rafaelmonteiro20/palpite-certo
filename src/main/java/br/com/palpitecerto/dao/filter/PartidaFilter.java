package br.com.palpitecerto.dao.filter;

import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.model.Rodada;

public class PartidaFilter {
	
	public PartidaFilter() {
	}

	public PartidaFilter(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	private Campeonato campeonato;
	
	private Rodada rodada;

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}
	
}