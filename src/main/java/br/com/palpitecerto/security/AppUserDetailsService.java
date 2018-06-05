package br.com.palpitecerto.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.palpitecerto.infra.cdi.CDIServiceLocator;
import br.com.palpitecerto.model.Usuario;
import br.com.palpitecerto.service.UsuarioService;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UsuarioService usuarioService = CDIServiceLocator.getBean(UsuarioService.class);
		Usuario usuario = usuarioService.getUsuarioPor(login);
		if(usuario == null)
			throw new UsernameNotFoundException("Erro ao buscar usuário");
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {

		List<SimpleGrantedAuthority> permissoes = new ArrayList<>();
		permissoes.add(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil()
				.toString().toUpperCase()));
	
		return permissoes;
	}
}