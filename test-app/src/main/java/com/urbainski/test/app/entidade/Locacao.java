package com.urbainski.test.app.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "locacao")
public class Locacao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_locacao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLocacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_retirada")
	private Date dtRetirada;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_previstaentrega")
	private Date dtPrevistaentrega;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_entrega")
	private Date dtEntrega;
	
	@Column(name = "nr_total")
	private Double nrTotal;
	
	@Column(name = "nr_multaatraso")
	private Double nrMultaatraso;
	
	@Enumerated
	@Column(name = "st_locacao")
	private SituacaoLocacao stLocacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_colaborador", referencedColumnName = "id_colaborador")
	private Colaborador colaborador;
	
	@OneToMany(mappedBy = "locacao")
	private List<Locacaomidia> listLocacaomidia;
	
	public Integer getIdLocacao() {
		return idLocacao;
	}
	
	public void setIdLocacao(Integer idLocacao) {
		this.idLocacao = idLocacao;
	}
	
	public Date getDtRetirada() {
		return dtRetirada;
	}
	
	public void setDtRetirada(Date dtRetirada) {
		this.dtRetirada = dtRetirada;
	}
	
	public Date getDtPrevistaentrega() {
		return dtPrevistaentrega;
	}
	
	public void setDtPrevistaentrega(Date dtPrevistaentrega) {
		this.dtPrevistaentrega = dtPrevistaentrega;
	}
	
	public Date getDtEntrega() {
		return dtEntrega;
	}
	
	public void setDtEntrega(Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}
	
	public Double getNrTotal() {
		return nrTotal;
	}
	
	public void setNrTotal(Double nrTotal) {
		this.nrTotal = nrTotal;
	}
	
	public Double getNrMultaatraso() {
		return nrMultaatraso;
	}
	
	public void setNrMultaatraso(Double nrMultaatraso) {
		this.nrMultaatraso = nrMultaatraso;
	}
	
	public SituacaoLocacao getStLocacao() {
		return stLocacao;
	}
	
	public void setStLocacao(SituacaoLocacao stLocacao) {
		this.stLocacao = stLocacao;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Colaborador getColaborador() {
		return colaborador;
	}
	
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Locacaomidia> getListLocacaomidia() {
		return listLocacaomidia;
	}
	
	public void setListLocacaomidia(List<Locacaomidia> listLocacaomidia) {
		this.listLocacaomidia = listLocacaomidia;
	}
	
	public enum SituacaoLocacao {
		
		PAGO_LOCACAO,
		
		PAGAR_DEVOLUCAO;
		
	}
}
