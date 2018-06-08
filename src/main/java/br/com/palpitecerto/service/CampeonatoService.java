package br.com.palpitecerto.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.palpitecerto.dao.CampeonatoDao;
import br.com.palpitecerto.infra.jpa.Transactional;
import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.service.exception.RegistroExistenteException;

public class CampeonatoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CampeonatoDao campeonatoDao;
	
	@Transactional
	public void salvar(Campeonato campeonato) {

		if(isCampeonatoExistente(campeonato))
			throw new RegistroExistenteException("Campeonato já cadastrado.");
		
		campeonatoDao.salvar(campeonato);
	}
	
	@Transactional
	public void excluir(Long id) {
		campeonatoDao.excluir(id);
	}

	private boolean isCampeonatoExistente(Campeonato campeonato) {
		
		Campeonato existente = campeonatoDao.buscarPorId(campeonato.getId());
		
		if(existente == null || existente.equals(campeonato))
			return false;
		
		return true;
	}

	public Campeonato buscarPorId(Long id) {
		return campeonatoDao.buscarPorId(id);
	}
	
	public List<Campeonato> listar() {
		return campeonatoDao.listar();
	}

}
