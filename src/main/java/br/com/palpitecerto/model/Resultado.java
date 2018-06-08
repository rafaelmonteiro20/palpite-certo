package br.com.palpitecerto.model;

public enum Resultado {

	VITORIA_MANDANTE("Vitória do mandante"), VITORIA_VISITANTE("Vitória do visitante"), EMPATE("Empate");

	private String descricao;

	private Resultado(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}