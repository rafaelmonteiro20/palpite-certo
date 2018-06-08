package br.com.palpitecerto.model.builder;

import br.com.palpitecerto.model.Partida;
import br.com.palpitecerto.model.Resultado;
import br.com.palpitecerto.model.Time;

public class PartidaBuilder {

	private Partida partida;
	
	public PartidaBuilder() {
		partida = new Partida();
	}
	
	public PartidaBuilder addMandante(Time mandante) {
		this.partida.setMandante(mandante);
		return this;
	}
	
	public PartidaBuilder addVisitante(Time visitante) {
		this.partida.setVisitante(visitante);
		return this;
	}
	
	public PartidaBuilder addResultado(Resultado resultado) {
		this.partida.setResultado(resultado);;
		return this;
	}
	
	public Partida build() {
		return this.partida;
	}
}