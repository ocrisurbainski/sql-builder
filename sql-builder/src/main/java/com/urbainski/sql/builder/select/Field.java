package com.urbainski.sql.builder.select;

/**
 * Objeto que representa campos adicionados na consulta.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public class Field {

	/**
	 * Nome do campo na query.
	 */
	private String fieldName;
	
	/**
	 * Alias do campo.
	 */
	private String alias;
	
	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Construto com parametross.
	 * 
	 * @param fieldName
	 * @param alias
	 */
	public Field(String fieldName, String alias) {
		super();
		this.fieldName = fieldName;
		this.alias = alias;
	}
	
	/**
	 * Construtor sem parametros.
	 */
	public Field() {
		
	}
	
}
