package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.palpitecerto.infra.jsf.FacesUtil;
import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.model.Partida;
import br.com.palpitecerto.model.Resultado;
import br.com.palpitecerto.model.Rodada;
import br.com.palpitecerto.model.Time;
import br.com.palpitecerto.service.CampeonatoService;
import br.com.palpitecerto.service.RodadaService;
import br.com.palpitecerto.service.TimeService;
import br.com.palpitecerto.service.exception.RegistroExistenteException;

@Named
@ViewScoped
public class RodadasBean implements Serializable {

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

	private List<Time> times;

	private Campeonato campeonato;
	private List<Campeonato> campeonatos;

	private Rodada rodada;
	private List<Rodada> rodadas;

	private Integer ultimaRodada;

	private Partida partida;

	@PostConstruct
	public void init() {
		campeonatos = campeonatoService.listar();
		times = timeService.listar();
		novaPartida();
		novaRodada();
	}

	public void salvar() {
		try {
			if (rodada.isNova())
				rodada.setNumero(++ultimaRodada);
			rodadaService.salvar(rodada);
			atualizarRodadas();
			facesUtil.addInfoMessage("Partida salva com sucesso.");
			facesUtil.updateComponents("mensagens", "rodadas-tabela", "partidas-tabela");
		} catch (RegistroExistenteException e) {
			facesUtil.validationFailed(e.getMessage());
		}
	}

	public void novaRodada() {
		rodada = new Rodada();
	}

	public void novaPartida() {
		partida = new Partida();
	}

	public void salvarPartida() {
		try {
			if (partida.isNova()) {
				rodada.addPartida(partida);
			} else {
				rodada.updatePartida(partida);
			}
			rodadaService.salvar(rodada);
			atualizarRodadas();
			facesUtil.addInfoMessage("Rodada salva com sucesso.");
			facesUtil.updateComponents("mensagens", "rodadas-tabela");
		} catch (RegistroExistenteException e) {
			facesUtil.validationFailed(e.getMessage());
		}
	}

	public void buscarUltimaRodada() {
		ultimaRodada = rodadaService.buscarUltimaRodadaCadastrada(rodada.getCampeonato());
	}

	public void atualizarRodadas() {
		rodadas = rodadaService.buscarPorCampeonato(campeonato);
	}

	public void resetUltimaRodada() {
		ultimaRodada = null;
	}

	public List<Partida> getPartidasPorRodada(Rodada rodada) {
		return rodadaService.buscarPartidasPorRodada(rodada);
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

	public List<Rodada> getRodadas() {
		return rodadas;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public Integer getUltimaRodada() {
		return ultimaRodada;
	}

	public void setUltimaRodada(Integer ultimaRodada) {
		this.ultimaRodada = ultimaRodada;
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