package com.urbainski.sql.db.types;

/**
 * Enum para representar os tipos de funções de
 * agregações que podem ser aplicadas as consultas sql.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 28/09/2014
 * @version 1.0
 *
 */
public enum AggregateDBTypes {
	
	/**
	 * Função de agregação 'count'.
	 */
	COUNT("count"),
	
	/**
	 * Função de agregação 'sum'.
	 */
	SUM("sum"),
	
	/**
	 * Função de agregação 'avg'.
	 */
	AVG("avg"),
	
	/**
	 * Função de agregação 'min'.
	 */
	MIN("min"),
	
	/**
	 * Função de agregação 'max'.
	 */
	MAX("max");

	/**
	 * Propriedade que guardar o nome da propriedade.
	 */
	private String name;
	
	private AggregateDBTypes(String name) {
		this.name = name;
	}
	
	public String getAggregateType() {
		return name;
	}
}
