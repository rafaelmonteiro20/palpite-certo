package br.com.palpitecerto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.palpitecerto.model.Campeonato;

public class CampeonatoDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public void salvar(Campeonato campeonato) {
		manager.merge(campeonato);
	}

	public void excluir(Long id) {
		Campeonato temp = buscarPorId(id);
		if (temp == null)
			throw new RuntimeException("Campeonato não existe!");
		manager.remove(temp);
	}

	public Campeonato buscarPorId(Long id) {
		try {
			return manager.createQuery("from Campeonato where id = :id", Campeonato.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			// No result
			return null;
		}
	}

	public List<Campeonato> listar() {
		return manager.createQuery("from Campeonato", Campeonato.class)
						.getResultList();
	}
	
	public List<Campeonato> buscarPorPeriodo(String periodo) {
		return manager.createQuery("from Campeonato where periodo = :periodo", Campeonato.class)
				.setParameter("periodo", periodo)
				.getResultList();
	}
}