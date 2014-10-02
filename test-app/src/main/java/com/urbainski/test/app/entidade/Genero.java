package com.urbainski.test.app.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genero")
public class Genero implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_genero")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGenero;
	
	@Column(name = "ds_genero")
	private String dsGenero;
	
	public Integer getIdGenero() {
		return idGenero;
	}
	
	public void setIdGenero(Integer idGenero) {
		this.idGenero = idGenero;
	}
	
	public String getDsGenero() {
		return dsGenero;
	}
	
	public void setDsGenero(String dsGenero) {
		this.dsGenero = dsGenero;
	}
	
}
