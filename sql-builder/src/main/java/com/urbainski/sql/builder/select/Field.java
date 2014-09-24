package com.urbainski.sql.builder.select;

import static com.urbainski.sql.util.SQLUtils.AS;

import com.urbainski.sql.builder.Builder;

/**
 * Objeto que representa campos adicionados na consulta.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public class Field implements Builder {

	/**
	 * Nome do campo na query.
	 */
	private String fieldName;
	
	/**
	 * Alias do campo.
	 */
	private String alias;
	
	/**
	 * Nome da tabela ou alias.
	 */
	private String tableNameOrAlias;
	
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
	
	public String getTableNameOrAlias() {
		return tableNameOrAlias;
	}
	
	public void setTableNameOrAlias(String tableNameOrAlias) {
		this.tableNameOrAlias = tableNameOrAlias;
	}

	/**
	 * Construto com parametross.
	 * 
	 * @param fieldName
	 * @param alias
	 */
	public Field(String tableNameOrAlias, String fieldName, String alias) {
		super();
		this.fieldName = fieldName;
		this.alias = alias;
		this.tableNameOrAlias = tableNameOrAlias;
	}
	
	/**
	 * Construtor sem parametros.
	 */
	public Field() {
		
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(tableNameOrAlias + ".");
		sql.append(fieldName);
		
		if (!(alias.isEmpty())) {
			sql.append(" ");
			sql.append(AS);
			sql.append(" ");
			sql.append(alias);
		}
		return sql.toString();
	}
	
}
