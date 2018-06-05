package br.com.palpitecerto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.palpitecerto.infra.jsf.FacesUtil;
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
	private FacesUtil facesUtil;

	private Usuario usuario;
	private List<Usuario> usuarios;

	@PostConstruct
	public void init() {
		novoUsuario();
	}

	public String salvarJogador() {
		usuario.setPerfil(Perfil.JOGADOR);
		usuarioService.salvarOuAtualizar(usuario);
		facesUtil.addFlash("Usuário criado com sucesso, realize seu login.");
		facesUtil.updateComponents("mensagens");
		return "/login.xhtml?faces-redirect=true";
	}
	
	public void salvar() {
		usuarioService.salvarOuAtualizar(usuario);
		buscarUsuarios();
		facesUtil.addInfoMessage("Usuário salvo com sucesso.");
		facesUtil.updateComponents("mensagens", "usuarios-tabela");
	}
	
	public void mudarStatus() {
		usuarioService.mudarStatus(usuario);
		facesUtil.addInfoMessage("Status modificado com sucesso.");
		facesUtil.updateComponents("mensagens", "usuarios-tabela");
	}

	public void buscarUsuarios() {
		usuarios = usuarioService.listar();
	}
	
	public Perfil[] getPerfis() {
		return Perfil.values();
	}
	
	public void novoUsuario() {
		usuario = new Usuario();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
