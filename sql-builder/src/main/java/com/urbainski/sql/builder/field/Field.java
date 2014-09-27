package com.urbainski.sql.builder.field;

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
	 * Classe de entidade.
	 */
	protected Class<?> entityClass;

	/**
	 * Nome do campo na query.
	 */
	protected String fieldName;
	
	/**
	 * Alias do campo.
	 */
	protected String alias;
	
	/**
	 * Nome da tabela ou alias.
	 */
	protected String tableNameOrAlias;
	
	public Class<?> getEntityClass() {
		return entityClass;
	}
	
	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	
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
	public Field(Class<?> entityClass, String tableNameOrAlias, 
			String fieldName, String alias) {
		super();
		
		this.entityClass = entityClass;
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
