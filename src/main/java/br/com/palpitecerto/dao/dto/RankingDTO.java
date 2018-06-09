package br.com.palpitecerto.dao.dto;

import br.com.palpitecerto.model.Usuario;

public class RankingDTO {
	private Usuario jogador;
	
	private Integer pontuacao;
	
	public RankingDTO() {
	}

	public RankingDTO(Usuario jogador, Integer pontuacao) {
		this.jogador = jogador;
		this.pontuacao = pontuacao;
	}

	public Usuario getJogador() {
		return jogador;
	}

	public void setJogador(Usuario jogador) {
		this.jogador = jogador;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
}