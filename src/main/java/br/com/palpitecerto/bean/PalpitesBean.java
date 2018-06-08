package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.palpitecerto.infra.jsf.FacesUtil;
import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.model.Palpite;
import br.com.palpitecerto.model.Partida;
import br.com.palpitecerto.model.Resultado;
import br.com.palpitecerto.model.Rodada;
import br.com.palpitecerto.model.Usuario;
import br.com.palpitecerto.security.Logado;
import br.com.palpitecerto.service.CampeonatoService;
import br.com.palpitecerto.service.PalpiteService;
import br.com.palpitecerto.service.RodadaService;

@Named
@ViewScoped
public class PalpitesBean implements Serializable {

	private static final long serialVersionUID = 5985002029842407325L;

	private Palpite palpite;
	private List<Palpite> palpites;

	private Partida partida;

	private List<Campeonato> campeonatos;
	private Campeonato campeonatoSelecionado;

	private List<Rodada> rodadas;
	private Rodada rodadaSelecionada;

	private Resultado resultadoSelecionado;

	@Inject
	private PalpiteService palpiteService;

	@Inject
	private FacesUtil facesUtil;

	@Inject
	private RodadaService rodadaService;

	@Inject
	private CampeonatoService campeonatoService;

	@Inject
	@Logado
	private Usuario usuarioLogado;

	@PostConstruct
	public void init() {
		campeonatos = campeonatoService.listar();
		novoPalpite();
	}

	public void salvar() {
		palpite.setJogador(usuarioLogado);
		palpite.setRodada(rodadaSelecionada);
		palpite.setPartida(partida);
		palpite.setResultado(resultadoSelecionado);
		Long id = palpiteService.palpiteExiste(palpite);
		if (id != null)
			palpite.setId(id);
		palpiteService.salvar(palpite);
		novoPalpite();
		resultadoSelecionado = Resultado.VITORIA_MANDANTE;
		facesUtil.addInfoMessage("Palpite salvo com sucesso.");
		facesUtil.updateComponents("mensagens");
	}

	public void buscarRodadas() {
		rodadas = rodadaService.buscarPorCampeonato(campeonatoSelecionado);
	}

	public void buscarPalpites() {
		palpites = palpiteService.buscarPalpitesPor(rodadaSelecionada, usuarioLogado);
	}

	private void novoPalpite() {
		palpite = new Palpite();
	}

	public Resultado[] getResultados() {
		return Resultado.values();
	}

	public Palpite getPalpite() {
		return palpite;
	}

	public void setPalpite(Palpite palpite) {
		this.palpite = palpite;
	}

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public Campeonato getCampeonatoSelecionado() {
		return campeonatoSelecionado;
	}

	public void setCampeonatoSelecionado(Campeonato campeonatoSelecionado) {
		this.campeonatoSelecionado = campeonatoSelecionado;
	}

	public Rodada getRodadaSelecionada() {
		return rodadaSelecionada;
	}

	public void setRodadaSelecionada(Rodada rodadaSelecionada) {
		this.rodadaSelecionada = rodadaSelecionada;
	}

	public List<Rodada> getRodadas() {
		return rodadas;
	}

	public Partida getPartida() {
		if (partida == null)
			partida = new Partida();
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Resultado getResultadoSelecionado() {
		return resultadoSelecionado;
	}

	public void setResultadoSelecionado(Resultado resultadoSelecionado) {
		this.resultadoSelecionado = resultadoSelecionado;
	}

	public List<Palpite> getPalpites() {
		return palpites;
	}

}
