package br.com.palpitecerto.dao.filter;

import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.model.Rodada;
import br.com.palpitecerto.model.Usuario;

public class RankingFilter {

	private Usuario jogador;
	
	private Campeonato campeonato;
	
	private Rodada rodada;
	
	public boolean hasJogador() {
		return jogador != null;
	}
	
	public boolean hasCampeonato() {
		return campeonato != null;
	}
	
	public boolean hasRodada() {
		return rodada != null;
	}

	public Usuario getJogador() {
		return jogador;
	}

	public void setJogador(Usuario jogador) {
		this.jogador = jogador;
	}

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