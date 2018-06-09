package br.com.palpitecerto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public Md5PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}

	@Bean
	public AppUserDetailsService userDetailsService() {
		return new AppUserDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		JsfLoginUrlAuthenticationEntryPoint jsfLoginEntry = new JsfLoginUrlAuthenticationEntryPoint();
		jsfLoginEntry.setLoginFormUrl("/login.xhtml");
		jsfLoginEntry.setRedirectStrategy(new JsfRedirectStrategy());
		
		JsfAccessDeniedHandler jsfDeniedEntry = new JsfAccessDeniedHandler();
		jsfDeniedEntry.setLoginPath("/acesso-negado.xhtml");
		jsfDeniedEntry.setContextRelative(true);
		
		http
			.csrf().disable()
			.headers().frameOptions().sameOrigin()
			.and()

			.authorizeRequests()
				.antMatchers("/login.xhtml", "/javax.faces.resource/**", "/meu-cadastro.xhtml").permitAll()
				.antMatchers("/home.xhtml", "/acesso-negado.xhtml", "/pagina-nao-encontrada.xhtml").authenticated()
				.antMatchers("/administrador/**", "/campeonatos.xhtml", "/rodadas.xhtml", "/times.xhtml", "/cadastrar-resultados.xhtml").hasAnyRole("ADMINISTRADOR")
				.antMatchers("/palpites.xhtml").hasAnyRole("JOGADOR")
				.antMatchers("/lista-palpites.xhtml", "resultados-rodada.xhtml").hasAnyRole("ADMINISTRADOR", "JOGADOR")
				.and()

			.formLogin()
				.loginPage("/login.xhtml")
				.failureUrl("/login.xhtml?invalid=true")
				.and()

			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.invalidateHttpSession(true)
				.and()

			.exceptionHandling()
				.accessDeniedPage("/acesso-negado.xhtml")
				.authenticationEntryPoint(jsfLoginEntry)
				.accessDeniedHandler(jsfDeniedEntry);
	}
}