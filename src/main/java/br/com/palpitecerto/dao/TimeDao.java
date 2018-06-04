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
	
	public void salvar(Time time) {
		manager.merge(time);
	}

	public List<Time> listar() {
		return manager.createQuery("from Time order by nome", Time.class)
					  .getResultList();
	}

	public Time buscarPorNome(String nome) {
		try {
			return manager.createQuery("from Time where lower(nome) = :nome", Time.class)
					  .setParameter("nome", nome.toLowerCase())
					  .getSingleResult();
		} catch (Exception e) {
			// No result
			return null;
		}
	}

	public void remover(Time time) {
		time = manager.find(Time.class, time.getId());
		
		if(time != null) {
			manager.remove(time);
		}
	}
	
}
