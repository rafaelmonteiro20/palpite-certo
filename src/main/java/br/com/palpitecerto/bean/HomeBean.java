package br.com.palpitecerto.bean;

import javax.enterprise.inject.Model;

@Model
public class HomeBean {
	
	public String getTitulo() {
		return "Bem-vindo! Faça sua aposta.";
	}
	
}
