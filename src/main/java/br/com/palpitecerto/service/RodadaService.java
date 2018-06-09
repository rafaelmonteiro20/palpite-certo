package br.com.palpitecerto.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.palpitecerto.dao.RodadaDao;
import br.com.palpitecerto.infra.jpa.Transactional;
import br.com.palpitecerto.model.Campeonato;
import br.com.palpitecerto.model.Partida;
import br.com.palpitecerto.model.Rodada;
import br.com.palpitecerto.service.exception.RegistroExistenteException;

public class RodadaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private RodadaDao rodadaDao;
	
	@Transactional
	public void salvar(Rodada rodada) {

		if(isRodadaExistente(rodada))
			throw new RegistroExistenteException("Rodada já cadastrada.");
		
		rodadaDao.salvar(rodada);
	}
	
	@Transactional
	public void excluir(Long id) {
		rodadaDao.excluir(id);
	}

	private boolean isRodadaExistente(Rodada rodada) {
		
		Rodada existente = rodadaDao.buscarPorId(rodada.getId());
		
		if(existente == null || existente.equals(rodada))
			return false;
		
		return true;
	}
	
	public List<Rodada> listar() {
		return rodadaDao.listar();
	}
	
	public List<Rodada> buscarPorCampeonato(Campeonato campeonato) {
		return rodadaDao.buscarPorCampeonato(campeonato);
	}

	public Integer buscarUltimaRodadaCadastrada(Campeonato campeonato) {
		return rodadaDao.buscarUltimaRodadaCadastrada(campeonato);
	}
	
	public List<Partida> buscarPartidasPorRodada(Rodada rodada) {
		return rodadaDao.buscarPartidasPorRodada(rodada);
	}
	
	public List<Rodada> buscarRodadasEncerradasPor(Campeonato campeonato) {
		return rodadaDao.buscarRodadasEncerradasPor(campeonato);
	}
	
	public List<Rodada> buscarRodadasNaoEncerradasPor(Campeonato campeonato) {
		return rodadaDao.buscarRodadasNaoEncerradasPor(campeonato);
	}
}