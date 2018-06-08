package br.com.palpitecerto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.model.Rodada;

public class RodadaDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public void salvar(Rodada rodada) {
		manager.merge(rodada);
	}

	public void excluir(Long id) {
		Rodada temp = buscarPorId(id);
		if (temp == null)
			throw new RuntimeException("Rodada não existe!");
		manager.remove(temp);
	}

	public Rodada buscarPorId(Long id) {
		try {
			return manager.createQuery("from Rodada where id = :id", Rodada.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			// No result
			return null;
		}
	}

	public List<Rodada> listar() {
		return manager.createQuery("from Rodada", Rodada.class).getResultList();
	}
	
	public List<Rodada> buscarPorCampeonato(Campeonato campeonato) {
		return manager.createQuery("from Rodada where campeonato = :campeonato", Rodada.class)
				.setParameter("campeonato", campeonato)
				.getResultList();
	}
}