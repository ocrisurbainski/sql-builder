package com.urbainski.test.app.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstado;
	
	@Column(name = "ds_estado")
	private String dsEstado;
	
	@Column(name = "ds_uf")
	private String ufEstado;
	
	public Integer getIdEstado() {
		return idEstado;
	}
	
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	public String getDsEstado() {
		return dsEstado;
	}
	
	public void setDsEstado(String dsEstado) {
		this.dsEstado = dsEstado;
	}
	
	public String getUfEstado() {
		return ufEstado;
	}
	
	public void setUfEstado(String ufEstado) {
		this.ufEstado = ufEstado;
	}

}
