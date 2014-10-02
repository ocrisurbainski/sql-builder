package com.urbainski.test.app.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cargo")
public class Cargo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_cargo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCargo;
	
	@Column(name = "ds_cargo")
	private String dsCargo;
	
	public Integer getIdCargo() {
		return idCargo;
	}
	
	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}
	
	public String getDsCargo() {
		return dsCargo;
	}
	
	public void setDsCargo(String dsCargo) {
		this.dsCargo = dsCargo;
	}
	
}
