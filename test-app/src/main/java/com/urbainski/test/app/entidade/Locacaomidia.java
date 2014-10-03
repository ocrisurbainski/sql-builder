package com.urbainski.test.app.entidade;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "locacao_midia")
public class Locacaomidia implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_locacao_midia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLocacaomidia;
	
	@Column(name = "nr_valor")
	private Double nrValor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_locacao", referencedColumnName = "id_locacao")
	private Locacao locacao;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_midia", referencedColumnName = "id_midia")
	private Midia midia;
	
	public Integer getIdLocacaomidia() {
		return idLocacaomidia;
	}
	
	public void setIdLocacaomidia(Integer idLocacaomidia) {
		this.idLocacaomidia = idLocacaomidia;
	}
	
	public Double getNrValor() {
		return nrValor;
	}
	
	public void setNrValor(Double nrValor) {
		this.nrValor = nrValor;
	}
	
	public Locacao getLocacao() {
		return locacao;
	}
	
	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}
	
	public Midia getMidia() {
		return midia;
	}
	
	public void setMidia(Midia midia) {
		this.midia = midia;
	}
	
}
