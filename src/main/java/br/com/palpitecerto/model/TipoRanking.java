package br.com.palpitecerto.model;

public enum TipoRanking {
	GERAL("Geral"), POR_CAMPEONATO("Por campeonato"), POR_RODADA("Por rodada");
	
	private String descricao;

	private TipoRanking(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}