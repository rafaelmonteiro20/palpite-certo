package br.com.palpitecerto.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.palpitecerto.dao.PalpiteDAO;
import br.com.palpitecerto.dao.dto.RankingDTO;
import br.com.palpitecerto.dao.filter.RankingFilter;
import br.com.palpitecerto.infra.jpa.Transactional;
import br.com.palpitecerto.model.Palpite;
import br.com.palpitecerto.model.Partida;
import br.com.palpitecerto.model.Rodada;
import br.com.palpitecerto.model.Usuario;

public class PalpiteService implements Serializable {

	private static final long serialVersionUID = -8314453835827991760L;

	@Inject
	private PalpiteDAO palpiteDAO;

	@Transactional
	public void salvar(Palpite palpite) {
		palpiteDAO.salvar(palpite);
	}

	public List<Palpite> listar() {
		return palpiteDAO.listar();
	}

	public Long palpiteExiste(Palpite palpite) {
		return palpiteDAO.palpiteExiste(palpite);
	}

	public List<Palpite> buscarPalpitesPor(Rodada rodada, Usuario jogador) {
		if (jogador.isAdministrador())
			return palpiteDAO.buscarPalpitesPor(rodada);

		return palpiteDAO.buscarPalpitesPorRodadaEJogador(rodada, jogador);
	}

	@Transactional
	public void validarPalpites(Rodada rodada) {
		List<Partida> partidas = rodada.getPartidas();
		List<Palpite> palpites = palpiteDAO.buscarPalpitesPor(rodada);
		for (Partida partida : partidas) {
			palpites.stream().filter(p -> p.getPartida().equals(partida)).forEach(p -> {
				if (p.getResultado().equals(partida.getResultado()))
					p.setPalpiteCerto(true);
					palpiteDAO.salvar(p);
			});
		}
	}
	
	public List<RankingDTO> buscarRanking(RankingFilter filter) {
		return palpiteDAO.buscarRanking(filter);
	}
}