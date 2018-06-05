package br.com.palpitecerto.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.palpitecerto.dao.UsuarioDAO;
import br.com.palpitecerto.infra.jpa.Transactional;
import br.com.palpitecerto.model.Status;
import br.com.palpitecerto.model.Usuario;
import br.com.palpitecerto.security.Criptografia;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 5411157659288446766L;

	@Inject
	private UsuarioDAO usuarioDAO;

	public List<Usuario> listar() {
		return usuarioDAO.listar();
	}
	
	public Usuario getUsuarioPor(String login) {
		return usuarioDAO.getUsuarioPor(login);
	}

	@Transactional
	public void salvarOuAtualizar(Usuario usuario) {
		if (usuario.getSenha() == null || usuario.getSenha().isEmpty())
			usuario.setSenha("123");

		if(usuario.getId() == null)
			usuario.setSenha(Criptografia.criptografar(usuario.getSenha()));
		
		usuarioDAO.salvarOuAtualizar(usuario);
	}

	@Transactional
	public void mudarStatus(Usuario usuario) {
		if (usuario.getStatus().equals(Status.ATIVO))
			usuario.setStatus(Status.INATIVO);
		else
			usuario.setStatus(Status.ATIVO);

		usuarioDAO.salvarOuAtualizar(usuario);
	}

	public Usuario getPorId(Long id) {
		return usuarioDAO.getPorId(id);
	}

	public void remover(Usuario usuario) {
		usuarioDAO.remover(usuario);
	}
}
