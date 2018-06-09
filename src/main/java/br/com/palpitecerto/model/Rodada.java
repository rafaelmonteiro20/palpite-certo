package br.com.palpitecerto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "rodada")
public class Rodada implements Serializable {

	private static final long serialVersionUID = -4900131957811087358L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Min(value = 1)
	private Integer numero;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_campeonato")
	private Campeonato campeonato;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "rodada_partida", joinColumns = @JoinColumn(name = "id_rodada"), inverseJoinColumns = @JoinColumn(name = "id_partida"))
	private List<Partida> partidas = new ArrayList<>();

	@Type(type = "true_false")
	@Column(name = "is_encerrada")
	private boolean encerrada;

	public void addPartida(Partida partida) {
		if (!partidas.contains(partida))
			partidas.add(partida);
	}

	public void updatePartida(Partida partida) {
		partidas.remove(partida);
		partidas.add(partida);
	}

	public boolean isNova() {
		return id == null;
	}
	
	public boolean isValendo() {
		return !encerrada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public boolean isEncerrada() {
		return encerrada;
	}

	public void setEncerrada(boolean encerrada) {
		this.encerrada = encerrada;
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
		Rodada other = (Rodada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}