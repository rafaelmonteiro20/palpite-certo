package br.com.palpitecerto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.palpitecerto.model.Palpite;
import br.com.palpitecerto.model.Rodada;
import br.com.palpitecerto.model.Usuario;

public class PalpiteDAO implements Serializable {

	private static final long serialVersionUID = 9013663868932730110L;
	
	@Inject
	private EntityManager manager;
	
	public void salvar(Palpite palpite) {
		manager.merge(palpite);
	}
	
	public List<Palpite> listar() {
		return manager.createQuery("from Palpite", Palpite.class).getResultList();
	}
	
	public Long palpiteExiste(Palpite palpite) {
		try {
		return manager.createQuery("select p.id from Palpite p where p.jogador = :jogador and p.rodada = :rodada and p.partida = :partida", Long.class)
			.setParameter("jogador", palpite.getJogador())
			.setParameter("rodada", palpite.getRodada())
			.setParameter("partida", palpite.getPartida()).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Palpite> buscarPalpitesPor(Rodada rodada) {
		return manager.createQuery("from Palpite p where p.rodada = :rodada", Palpite.class)
				.setParameter("rodada", rodada).getResultList();
	}
	
	public List<Palpite> buscarPalpitesPorRodadaEJogador(Rodada rodada, Usuario jogador) {
		return manager.createQuery("from Palpite p where p.rodada = :rodada and p.jogador = :jogador", Palpite.class)
				.setParameter("rodada", rodada)
				.setParameter("jogador", jogador).getResultList();
	}
}
