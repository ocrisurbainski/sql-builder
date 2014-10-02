package com.urbainski.test.app.entidade;

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

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_endereco")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEndereco;
	
	@Column(name = "ds_endereco")
	private String dsEndereco;
	
	@Column(name = "nr_endereco")
	private String nrEndereco;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
	private Municipio municipio;

	public Integer getIdEndereco() {
		return idEndereco;
	}
	
	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}
	
	public String getDsEndereco() {
		return dsEndereco;
	}
	
	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}
	
	public String getNrEndereco() {
		return nrEndereco;
	}
	
	public void setNrEndereco(String nrEndereco) {
		this.nrEndereco = nrEndereco;
	}
	
	public Municipio getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
}
