package br.com.palpitecerto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.palpitecerto.model.Time;

public class TimeDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public List<Time> listar() {
		return manager.createQuery("from Time", Time.class)
					  .getResultList();
	}
	
}
