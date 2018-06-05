package br.com.palpitecerto.model.builder;

import br.com.palpitecerto.model.Estado;
import br.com.palpitecerto.model.Time;

public class TimeBuilder {

	private Time time;
	
	public TimeBuilder() {
		this.time = new Time();
	}
	
	public TimeBuilder addNome(String nome) {
		this.time.setNome(nome);
		return this;
	}
	
	public TimeBuilder addEstado(Estado estado) {
		this.time.setEstado(estado);
		return this;
	}
	
	public Time build() {
		return this.time;
	}
}