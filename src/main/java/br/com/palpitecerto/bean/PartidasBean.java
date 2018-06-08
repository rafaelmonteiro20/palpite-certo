package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.palpitecerto.dao.filter.PartidaFilter;
import br.com.palpitecerto.infra.jsf.FacesUtil;
import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.model.Partida;
import br.com.palpitecerto.model.Resultado;
import br.com.palpitecerto.model.Rodada;
import br.com.palpitecerto.model.Time;
import br.com.palpitecerto.service.CampeonatoService;
import br.com.palpitecerto.service.PartidaService;
import br.com.palpitecerto.service.RodadaService;
import br.com.palpitecerto.service.TimeService;
import br.com.palpitecerto.service.exception.RegistroExistenteException;

@Named
@ViewScoped
public class PartidasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesUtil facesUtil;

	@Inject
	private PartidaService partidaService;

	@Inject
	private TimeService timeService;

	@Inject
	private RodadaService rodadaService;

	@Inject
	private CampeonatoService campeonatoService;

	private Partida partida;
	private List<Partida> partidas;

	private List<Time> times;

	private List<Campeonato> campeonatos;

	private List<Rodada> rodadas;

	private PartidaFilter filter;

	@PostConstruct
	public void init() {
		campeonatos = campeonatoService.listar();
		times = timeService.listar();
		filter = new PartidaFilter(campeonatos.get(0));
		buscarRodadasPorCampeonato();
		filter.setRodada(rodadas.get(0));
		atualizarPartidas();
	}

	public void salvar() {
		try {
			partidaService.salvar(partida);
			atualizarPartidas();
			facesUtil.addInfoMessage("Partida salva com sucesso.");
			facesUtil.updateComponents("mensagens", "partidas-tabela");
		} catch (RegistroExistenteException e) {
			facesUtil.validationFailed(e.getMessage());
		}
	}

	public void novaPartida() {
		partida = new Partida();
		buscarRodadasPorCampeonato();
	}

	public void buscarRodadasPorCampeonato() {
		rodadas = rodadaService.buscarPorCampeonato(filter.getCampeonato());
	}
	
	public List<Partida> partidasPorRodada(Rodada rodada) {
		return partidas.stream().filter(p -> p.getRodada().equals(rodada)).collect(Collectors.toList());
	}

	public void atualizarPartidas() {
		partidas = partidaService.buscarPorCampeonatoERodada(filter);
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public List<Time> getTimes() {
		return times;
	}

	public List<Rodada> getRodadas() {
		return rodadas;
	}

	public Resultado[] getResultados() {
		return Resultado.values();
	}

	public PartidaFilter getFilter() {
		return filter;
	}

	public void setFilter(PartidaFilter filter) {
		this.filter = filter;
	}

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}
}