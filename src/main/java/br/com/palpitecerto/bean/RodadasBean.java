package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.palpitecerto.infra.jsf.FacesUtil;
import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.model.Rodada;
import br.com.palpitecerto.service.CampeonatoService;
import br.com.palpitecerto.service.RodadaService;
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
	
	private Rodada rodada;
	private List<Rodada> rodadas;

	
	@PostConstruct
	public void init() {
		atualizarRodadas();
		novaRodada();
	}
	
	public void salvar() {
		try {
			rodadaService.salvar(rodada);
			atualizarRodadas();
			facesUtil.addInfoMessage("Rodada salva com sucesso.");
			facesUtil.updateComponents("mensagens", "rodadas-tabela");
		} catch (RegistroExistenteException e) {
			facesUtil.validationFailed(e.getMessage());
		}
	}
	
	public void novaRodada() {
		rodada = new Rodada();
	}
	
	private void atualizarRodadas() {
		rodadas = rodadaService.listar();
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
	
	public List<Campeonato> getCampeonatos() {
		return campeonatoService.listar();
	}
}