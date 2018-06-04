package br.com.palpitecerto.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.palpitecerto.dao.TimeDao;
import br.com.palpitecerto.infra.jpa.Transactional;
import br.com.palpitecerto.model.Time;
import br.com.palpitecerto.service.exception.RegistroExistenteException;

public class TimeService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private TimeDao timeDao;
	
	@Transactional
	public void salvar(Time time) {

		if(isTimeExistente(time))
			throw new RegistroExistenteException("Time já cadastrado.");
		
		timeDao.salvar(time);
	}

	private boolean isTimeExistente(Time time) {
		
		Time existente = timeDao.buscarPorNome(time.getNome());
		
		if(existente == null || existente.equals(time))
			return false;
		
		return true;
	}

	public List<Time> listar() {
		return timeDao.listar();
	}

	@Transactional
	public void remover(Time time) {
		timeDao.remover(time);
	}

}
