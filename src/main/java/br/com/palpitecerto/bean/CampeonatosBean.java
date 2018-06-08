package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.palpitecerto.infra.jsf.FacesUtil;
import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.service.CampeonatoService;
import br.com.palpitecerto.service.exception.RegistroExistenteException;

@Named
@ViewScoped
public class CampeonatosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesUtil facesUtil;
	
	@Inject
	private CampeonatoService campeonatoService;
	
	private Campeonato campeonato;
	private List<Campeonato> campeonatos;
	
	@PostConstruct
	public void init() {
		atualizarCampeonatos();
		novoCampeonato();
	}
	
	public void salvar() {
		try {
			campeonatoService.salvar(campeonato);
			atualizarCampeonatos();
			facesUtil.addInfoMessage("Campeonato salvo com sucesso.");
			facesUtil.updateComponents("mensagens", "campeonatos-tabela");
		} catch (RegistroExistenteException e) {
			facesUtil.validationFailed(e.getMessage());
		}
	}
	
	public void novoCampeonato() {
		campeonato = new Campeonato();
	}
	
	private void atualizarCampeonatos() {
		campeonatos = campeonatoService.listar();
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
}