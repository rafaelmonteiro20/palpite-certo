package br.com.palpitecerto.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.palpitecerto.dao.PartidaDao;
import br.com.palpitecerto.dao.filter.PartidaFilter;
import br.com.palpitecerto.infra.jpa.Transactional;
import br.com.palpitecerto.model.Partida;
import br.com.palpitecerto.service.exception.RegistroExistenteException;

public class PartidaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PartidaDao partidaDao;
	
	@Transactional
	public void salvar(Partida partida) {

		if(isPartidaExistente(partida))
			throw new RegistroExistenteException("Partida já cadastrada.");
		
		partidaDao.salvar(partida);
	}
	
	@Transactional
	public void excluir(Long id) {
		partidaDao.excluir(id);
	}

	private boolean isPartidaExistente(Partida partida) {
		
		Partida existente = partidaDao.buscarPorTimes(partida.getMandante(), partida.getVisitante());
		
		if(existente == null || existente.equals(partida))
			return false;
		
		return true;
	}

	public List<Partida> listar() {
		return partidaDao.listar();
	}

	public List<Partida> buscarPorCampeonatoERodada(PartidaFilter filter) {
		return partidaDao.buscarPorCampeonatoERodada(filter);
	}

}
