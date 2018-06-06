package br.com.palpitecerto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.palpitecerto.model.Usuario;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = -4023825849976598313L;

	@Inject
	private EntityManager manager;

	public List<Usuario> listar() {
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}

	public Usuario getUsuarioPor(String login) {
		return manager.createQuery("from Usuario u where u.login = :login and u.ativo = true", Usuario.class)
				.setParameter("login", login)
				.getSingleResult();
	}

	public void salvarOuAtualizar(Usuario usuario) {
		manager.merge(usuario);
	}

	public Usuario getPorId(Long id) {
		return manager.find(Usuario.class, id);
	}

	public void remover(Usuario usuario) {
		usuario = getPorId(usuario.getId());
		manager.remove(usuario);
		manager.flush();
	}
}
