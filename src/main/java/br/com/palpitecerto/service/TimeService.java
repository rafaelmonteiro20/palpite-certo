package br.com.palpitecerto.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.palpitecerto.dao.TimeDao;
import br.com.palpitecerto.model.Time;

public class TimeService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private TimeDao timeDao;
	
	public void salvar(Time time) {
		System.out.println("Salvando time: " + time);
	}

	public List<Time> listar() {
		return timeDao.listar();
	}

}
