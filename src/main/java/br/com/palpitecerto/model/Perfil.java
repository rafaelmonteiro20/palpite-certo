package br.com.palpitecerto.model;

public enum Perfil {

	ADMINISTRADOR("Administrador"), JOGADOR("Jogador");

	private String descricao;

	private Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
