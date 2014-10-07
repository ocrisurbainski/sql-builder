package com.urbainski.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entidade editora para teste unitário.
 * 
 * @author Cristian Urbainski <cristianurbainskips@consisanet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
@Entity
@Table(name = "editora")
public class Editora implements Serializable {
	
	/**
	 * SerialVersion.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador da editora.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Nome.
	 */
	@Column(name = "nm_editora")
	private String nomeEditora;
	
	/**
	 * Endereço.
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private Endereco endereco;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNomeEditora() {
		return nomeEditora;
	}
	
	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}