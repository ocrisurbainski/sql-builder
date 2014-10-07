package com.urbainski.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade endereco para teste unitário.
 * 
 * @author Cristian Urbainski <cristianurbainskips@consisanet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	/**
	 * SerialVersion.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador do endereço.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Descrição do endereço.
	 */
	@Column(name = "ds_endereco")
	private String endereco;
	
	/**
	 * Número.
	 */
	@Column(name = "nr_numero")
	private String numero;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
