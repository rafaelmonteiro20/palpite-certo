package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.model.Rodada;
import br.com.palpitecerto.service.CampeonatoService;
import br.com.palpitecerto.service.RodadaService;

@Named
@ViewScoped
public class ResultadosRodadasBean implements Serializable {

	private static final long serialVersionUID = -3939751081188551146L;
	
	private Campeonato campeonato;
	private Rodada rodada;
	private List<Campeonato> campeonatos;
	private List<Rodada> rodadas;

	@Inject
	private CampeonatoService campeonatoService;

	@Inject
	private RodadaService rodadaService;

	@PostConstruct
	public void init() {
		campeonatos = campeonatoService.listar();
	}

	public void buscarRodadas() {
		rodadas = rodadaService.buscarRodadasEncerradasPor(campeonato);
	}

	public Campeonato getCampeonato() {
		if (campeonato == null)
			campeonato = new Campeonato();
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Rodada getRodada() {
		if (rodada == null)
			rodada = new Rodada();
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public List<Rodada> getRodadas() {
		return rodadas;
	}

}
