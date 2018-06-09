package br.com.palpitecerto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "palpite")
public class Palpite implements Serializable {

	private static final long serialVersionUID = 4333810730502992549L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_jogador")
	private Usuario jogador;

	@ManyToOne
	@JoinColumn(name = "id_rodada")
	private Rodada rodada;

	@ManyToOne
	@JoinColumn(name = "id_partida")
	private Partida partida;

	@Enumerated(EnumType.STRING)
	private Resultado resultado;

	@Type(type = "true_false")
	@Column(name = "is_palpite_certo")
	private boolean palpiteCerto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getJogador() {
		return jogador;
	}

	public void setJogador(Usuario jogador) {
		this.jogador = jogador;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public boolean isPalpiteCerto() {
		return palpiteCerto;
	}

	public void setPalpiteCerto(boolean palpiteCerto) {
		this.palpiteCerto = palpiteCerto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Palpite other = (Palpite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
