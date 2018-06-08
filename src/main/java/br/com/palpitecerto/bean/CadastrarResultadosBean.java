package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;

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
public class CadastrarResultadosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesUtil facesUtil;

	@Inject
	private RodadaService rodadaService;

	@Inject
	private CampeonatoService campeonatoService;

	@Inject
	private TimeService timeService;

	@Inject
	private PartidaService partidaService;

	private List<Time> times;

	private List<Campeonato> campeonatos;

	private List<Rodada> rodadas;

	private List<Partida> partidas;

	private PartidaFilter filter;

	private Partida partida;

	@PostConstruct
	public void init() {
		filter = new PartidaFilter();
		campeonatos = campeonatoService.listar();
		times = timeService.listar();
	}

	public void salvar() {
		try {
			partidaService.salvar(partida);
			atualizarRodadas();
			facesUtil.addInfoMessage("Resultado salvo com sucesso.");
			facesUtil.updateComponents("mensagens", "partidas-tabela");
		} catch (RegistroExistenteException e) {
			facesUtil.validationFailed(e.getMessage());
		}
	}

	public void atualizarRodadas() {
		rodadas = rodadaService.buscarPorCampeonato(filter.getCampeonato());
	}

	public void atualizarPartidas() {
		partidas = rodadaService.buscarPartidasPorRodada(filter.getRodada());
	}
	
	public boolean exibirEncerrar() {
		if(partidas == null)
			return false;
		return !partidas.stream().anyMatch(p -> p.getResultado() == null);
	}
	
	public boolean desabilitarEncerrar() {
		return filter.getRodada().isEncerrada() || partidas.size() == 0;
	}

	public void encerrarRodada() {
		try {
			Rodada rodada = filter.getRodada();
			rodada.setEncerrada(true);
			rodadaService.salvar(rodada);
			atualizarRodadas();
			facesUtil.addInfoMessage("Rodada encerrada com sucesso.");
			facesUtil.updateComponents("mensagens", "partidas-tabela");
		} catch (RegistroExistenteException e) {
			facesUtil.validationFailed(e.getMessage());
		}
	}

	public PartidaFilter getFilter() {
		return filter;
	}

	public void setFilter(PartidaFilter filter) {
		this.filter = filter;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public List<Rodada> getRodadas() {
		return rodadas;
	}

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Resultado[] getResultados() {
		return Resultado.values();
	}

	public List<Time> getTimes() {
		return times;
	}
}