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
@Table(name = "municipio")
public class Municipio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_municipio")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMunicipio;
	
	@Column(name = "ds_municipio")
	private String dsMunicipio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
	private Estado estado;
	
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	
	public String getDsMunicipio() {
		return dsMunicipio;
	}
	
	public void setDsMunicipio(String dsMunicipio) {
		this.dsMunicipio = dsMunicipio;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
