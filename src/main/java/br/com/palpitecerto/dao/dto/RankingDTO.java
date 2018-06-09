package br.com.palpitecerto.dao.dto;

public class RankingDTO {
	
	private String jogador;
	private Long pontuacao;
	
	public RankingDTO(String jogador, Long pontuacao) {
		this.jogador = jogador;
		this.pontuacao = pontuacao;
	}

	public String getJogador() {
		return jogador;
	}

	public Long getPontuacao() {
		return pontuacao;
	}

}