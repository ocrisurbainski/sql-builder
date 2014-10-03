package com.urbainski.test.app.dto;

/**
 * Classe que representa o dto para uma consulta nas midia.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 03/10/2014
 * @version 1.0
 *
 *
 */
public class DtoMidia {

	private Integer quantidade;
	
	private String tipomidia;
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getTipomidia() {
		return tipomidia;
	}
	
	public void setTipomidia(String tipomidia) {
		this.tipomidia = tipomidia;
	}
	
}
