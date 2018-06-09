package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.palpitecerto.dao.dto.RankingDTO;
import br.com.palpitecerto.dao.filter.RankingFilter;
import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.model.Rodada;
import br.com.palpitecerto.model.TipoRanking;
import br.com.palpitecerto.model.Usuario;
import br.com.palpitecerto.security.Logado;
import br.com.palpitecerto.service.CampeonatoService;
import br.com.palpitecerto.service.PalpiteService;
import br.com.palpitecerto.service.RodadaService;

@Named
@ViewScoped
public class RankingBean implements Serializable {

	private static final long serialVersionUID = 3143011367492276511L;

	@Inject
	@Logado
	private Usuario usuarioLogado;

//	@Inject
//	private FacesUtil facesUtil;

	@Inject
	private RodadaService rodadaService;

	@Inject
	private CampeonatoService campeonatoService;
	
	@Inject
	private PalpiteService palpiteService;

	private List<Campeonato> campeonatos;

	private List<Rodada> rodadas;

	private RankingFilter filter;
	private List<RankingDTO> rankiados;

	@PostConstruct
	public void init() {
		filter = new RankingFilter();
		campeonatos = campeonatoService.listar();
		buscarRanking();
	}

	public void buscarRodadas() {
		rodadas = rodadaService.buscarPorCampeonato(filter.getCampeonato());
	}
	
	public void buscarRanking() {
		rankiados = palpiteService.buscarRanking(filter);
	}

	public TipoRanking[] getTipos() {
		return TipoRanking.values();
	}

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public List<Rodada> getRodadas() {
		return rodadas;
	}

	public List<RankingDTO> getRankiados() {
		return rankiados;
	}
	
	public RankingFilter getFilter() {
		return filter;
	}
	
}