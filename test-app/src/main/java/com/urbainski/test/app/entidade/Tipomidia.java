package com.urbainski.test.app.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipomidia")
public class Tipomidia implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipomidia;
	
	@Column(name = "ds_tipomidia")
	private String dsTipomidia;
	
	public Integer getIdTipomidia() {
		return idTipomidia;
	}
	
	public void setIdTipomidia(Integer idTipomidia) {
		this.idTipomidia = idTipomidia;
	}
	
	public String getDsTipomidia() {
		return dsTipomidia;
	}
	
	public void setDsTipomidia(String dsTipomidia) {
		this.dsTipomidia = dsTipomidia;
	}

}
