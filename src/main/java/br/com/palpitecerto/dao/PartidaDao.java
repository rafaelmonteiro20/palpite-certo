package br.com.palpitecerto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.palpitecerto.dao.filter.PartidaFilter;
import br.com.palpitecerto.model.Partida;
import br.com.palpitecerto.model.Time;

public class PartidaDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public void salvar(Partida partida) {
		manager.merge(partida);
	}

	public void excluir(Long id) {
		Partida temp = buscarPorId(id);
		if (temp == null)
			throw new RuntimeException("Partida não existe!");
		manager.remove(temp);
	}

	public Partida buscarPorId(Long id) {
		try {
			return manager.createQuery("from Partida where id = :id", Partida.class).setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			// No result
			return null;
		}
	}

	public List<Partida> listar() {
		return manager.createQuery("from Partida", Partida.class).getResultList();
	}
	
	public List<Partida> buscarPorTime(Time time) {
		return manager.createQuery("from Partida where mandante = :time or visitante = :time", Partida.class)
				.setParameter("time", time)
				.getResultList();
	}

	public Partida buscarPorTimes(Time mandante, Time visitante) {
		try {
			return manager.createQuery("from Partida where mandante = :mandante and visitante = :visitante", Partida.class)
				.setParameter("mandante", mandante)
				.setParameter("visitante", visitante)
				.getSingleResult();
		} catch (Exception e) {
			// No result
			return null;
		}
	}

	public List<Partida> buscarPorCampeonatoERodada(PartidaFilter filter) {
		return manager.createQuery("SELECT p FROM Partida p INNER JOIN p.rodada r INNER JOIN r.campeonato c " + 
				"WHERE c = :campeonato AND r = :rodada", Partida.class)
				.setParameter("campeonato", filter.getCampeonato())
				.setParameter("rodada", filter.getRodada())
				.getResultList();
	}
}