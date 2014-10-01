package com.urbainski.test.app.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipolocacoes")
public class Tipolocacoes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipolocacoes;
	
	@Column(name = "ds_tipolocacoes")
	private String dsTipolocacoes;
	
	@Column(name = "nr_valorlocacao")
	private Double nrValorlocacao;
	
	public Integer getIdTipolocacoes() {
		return idTipolocacoes;
	}
	
	public void setIdTipolocacoes(Integer idTipolocacoes) {
		this.idTipolocacoes = idTipolocacoes;
	}
	
	public String getDsTipolocacoes() {
		return dsTipolocacoes;
	}
	
	public void setDsTipolocacoes(String dsTipolocacoes) {
		this.dsTipolocacoes = dsTipolocacoes;
	}
	
	public Double getNrValorlocacao() {
		return nrValorlocacao;
	}
	
	public void setNrValorlocacao(Double nrValorlocacao) {
		this.nrValorlocacao = nrValorlocacao;
	}
	
}