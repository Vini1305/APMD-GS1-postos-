package br.com.fiap.posto.model;

import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity @Table(name = "postos")
public class Posto {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posto")
	@SequenceGenerator(sequenceName = "posto", allocationSize = 1, name = "posto")
	private Long id;
	
	private String nome;
	private String endereco;
	private String estado;
	private String avaliacao;
	private String plugsDisponiveis;
	private String valorKiloWatHora;
	
	public Posto() {}
	
	public Posto(Long id, String nome, String endereco, String estado, String avaliacao, String plugsDisponiveis, String valorKiloWatHora) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.estado = estado;
		this.avaliacao = avaliacao;
		this.plugsDisponiveis = plugsDisponiveis;
		this.valorKiloWatHora = valorKiloWatHora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getEstado() {
		return endereco;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getPlugsDisponiveis() {
		return plugsDisponiveis;
	}

	public void setPlugsDisponiveis(String plugsDisponiveis) {
		this.plugsDisponiveis = plugsDisponiveis;
	}

	public String getValorKiloWatHora() {
		return valorKiloWatHora;
	}

	public void setValorKiloWatHora(String valorKiloWatHora) {
		this.valorKiloWatHora = valorKiloWatHora;
	}
	
	public Vector<String> getData() {
		Vector<String> data = new Vector<String>();
		data.add(id.toString());
		data.add(nome);
		data.add(endereco);
		data.add(estado);
		data.add(avaliacao);
		data.add(plugsDisponiveis);
		data.add(valorKiloWatHora);

		return data;
		
	}

}
