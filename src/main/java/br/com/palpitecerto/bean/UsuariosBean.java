package br.com.palpitecerto.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.palpitecerto.infra.jsf.MessagesHelper;
import br.com.palpitecerto.model.Perfil;
import br.com.palpitecerto.model.Usuario;
import br.com.palpitecerto.service.UsuarioService;

@Named
@ViewScoped
public class UsuariosBean implements Serializable {

	private static final long serialVersionUID = 4964721631844062183L;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private MessagesHelper helper;

	private Usuario usuario;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public String salvarJogador() {
		usuario.setPerfil(Perfil.JOGADOR);
		usuarioService.salvarOuAtualizar(usuario);
		helper.addFlash("Usuário criado com sucesso, realize seu login.");
		return "/login.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
