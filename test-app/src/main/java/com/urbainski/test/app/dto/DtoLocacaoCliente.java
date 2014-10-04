package com.urbainski.test.app.dto;

/**
 * Classe que representa o dto para uma consulta nas locacoes dos clientes.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 03/10/2014
 * @version 1.0
 *
 */
public class DtoLocacaoCliente {

	private Double valor;
	
	private String nomeCliente;
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
}
