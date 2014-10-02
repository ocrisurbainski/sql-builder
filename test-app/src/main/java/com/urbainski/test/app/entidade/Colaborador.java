package com.urbainski.test.app.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "colaborador")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
public class Colaborador extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_contratacao")
	private Date dtContratacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_demissao")
	private Date dtDemissao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
	private Cargo cargo;
	
	public Date getDtContratacao() {
		return dtContratacao;
	}
	
	public void setDtContratacao(Date dtContratacao) {
		this.dtContratacao = dtContratacao;
	}
	
	public Date getDtDemissao() {
		return dtDemissao;
	}
	
	public void setDtDemissao(Date dtDemissao) {
		this.dtDemissao = dtDemissao;
	}
	
	public Cargo getCargo() {
		return cargo;
	}
	
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
}
