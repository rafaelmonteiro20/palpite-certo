package br.com.palpitecerto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.palpitecerto.dao.dto.RankingDTO;
import br.com.palpitecerto.dao.filter.RankingFilter;
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
			return manager.createQuery(
					"select p.id from Palpite p where p.jogador = :jogador and p.rodada = :rodada and p.partida = :partida",
					Long.class).setParameter("jogador", palpite.getJogador())
					.setParameter("rodada", palpite.getRodada()).setParameter("partida", palpite.getPartida())
					.getSingleResult();
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
				.setParameter("rodada", rodada).setParameter("jogador", jogador).getResultList();
	}

	public List<RankingDTO> buscarRanking(RankingFilter filter) {
		return manager.createQuery(getQueryRanking(filter), RankingDTO.class).getResultList();
	}

	private String getQueryRanking(RankingFilter filter) {
		/*
		 * SELECT u.login, SUM(CASE WHEN p.is_palpite_certo = 'T' THEN 1 ELSE 0 END) AS
		 * acertos FROM palpite p INNER JOIN usuario u ON p.id_jogador = u.id GROUP BY
		 * u.login ORDER BY acertos DESC;
		 */
		StringBuilder builder = new StringBuilder(
				"SELECT NEW br.com.palpitecerto.dao.dto.RankingDTO(u.login, sum(case when p.palpiteCerto = true then 1 else 0 end))");
		builder.append(" FROM Palpite p INNER JOIN p.jogador u");
		builder.append(" GROUP BY u.login ORDER BY 2 DESC, 1 ASC");
		return builder.toString();
	}

}