package com.urbainski.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidade autor para teste unitário.
 * 
 * @author Cristian Urbainski <cristianurbainskips@consisanet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
@Entity
@Table(name = "autor")
public class Autor implements Serializable {

	/**
	 * SerialVersion.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador do autor.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Nome do autor.
	 */
	@Column(name = "ds_nome")
	private String nome;
	
	/**
	 * Data do nascimento.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento")
	private Date dataNascimento;
	
	/**
	 * Endereço.
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco endereco;
	
	/**
	 * Editora em qual publica seus livros atualmente.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "editora_id", referencedColumnName = "id")
	private Editora editora;
	
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
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}