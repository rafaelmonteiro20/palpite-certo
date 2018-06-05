package br.com.palpitecerto.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.palpitecerto.model.builder.PartidaBuilder;
import br.com.palpitecerto.model.builder.TimeBuilder;
import static br.com.palpitecerto.model.Estado.*;
import static br.com.palpitecerto.model.Resultado.*;

public class PartidaTest {

	private Partida partida;
	private Time mandante, visitante;
	
	@Before
	public void init() {
		this.mandante = new TimeBuilder()
							.addNome("Palmeiras")
							.addEstado(SP)
							.build();
		this.visitante = new TimeBuilder()
							.addNome("Fortaleza")
							.addEstado(CE)
							.build();
		this.partida = new PartidaBuilder()
							.addMandante(mandante)
							.addVisitante(visitante)
							.addResultado(EMPATE)
							.build();
	}
	
	@Test
	public void deveImprimirOsTimesEResultado() {
		assertEquals("[Palmeiras - Fortaleza: EMPATE]", this.partida.toString());
	}
	
}