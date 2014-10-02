package com.urbainski.test.app.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "midia")
public class Midia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_midia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMidia;
	
	@Column(name = "ds_midia")
	private String dsMidia;
	
	@Enumerated
	@Column(name = "st_midia")
	private SituacaoMidia stMidia;
	
	@Column(name = "nr_valorlocacao")
	private Double nrValorlocacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipomidia", referencedColumnName = "id_tipomidia")
	private Tipomidia tipomidia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipolocacoes", referencedColumnName = "id_tipolocacoes")
	private Tipolocacoes tipolocacoes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
	private Genero genero;
	
	public Integer getIdMidia() {
		return idMidia;
	}
	
	public void setIdMidia(Integer idMidia) {
		this.idMidia = idMidia;
	}
	
	public String getDsMidia() {
		return dsMidia;
	}
	
	public void setDsMidia(String dsMidia) {
		this.dsMidia = dsMidia;
	}
	
	public SituacaoMidia getStMidia() {
		return stMidia;
	}
	
	public void setStMidia(SituacaoMidia stMidia) {
		this.stMidia = stMidia;
	}
	
	public Double getNrValorlocacao() {
		return nrValorlocacao;
	}
	
	public void setNrValorlocacao(Double nrValorlocacao) {
		this.nrValorlocacao = nrValorlocacao;
	}
	
	public Tipomidia getTipomidia() {
		return tipomidia;
	}
	
	public void setTipomidia(Tipomidia tipomidia) {
		this.tipomidia = tipomidia;
	}
	
	public Tipolocacoes getTipolocacoes() {
		return tipolocacoes;
	}
	
	public void setTipolocacoes(Tipolocacoes tipolocacoes) {
		this.tipolocacoes = tipolocacoes;
	}
	
	public Genero getGenero() {
		return genero;
	}
	
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public enum SituacaoMidia {
		
		DISPONIVEL,
		
		LOCADO,
		
		DESCARTADO;
		
	}

}
