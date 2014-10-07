package com.urbainski.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidade livro para teste unitário.
 * 
 * @author Cristian Urbainski <cristianurbainskips@consisanet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
@Entity
@Table(name = "livro")
public class Livro implements Serializable {

	/**
	 * SerialVersion.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador do livro.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Nome do livro.
	 */
	@Column(name = "ds_nome")
	private String nome;
	
	/**
	 * Ano de publicação do livro.
	 */
	@Column(name = "nr_anopublicacao")
	private Integer anoPublicacao;
	
	/**
	 * Autor do livro.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autor_id", referencedColumnName = "id")
	private Autor autor;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}
	
	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
}