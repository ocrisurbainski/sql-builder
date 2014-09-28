package com.urbainski.sql.field.impl;

import com.urbainski.sql.field.Field;


/**
 * Classe que representa um campo da query.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 28/09/2014
 * @version 1.0
 *
 */
public class SimpleField extends Field {
	
	/**
	 * Classe de entidade.
	 */
	protected Class<?> entityClass;

	/**
	 * Nome do campo na query.
	 */
	protected String fieldName;
	
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
	
	public String getTableNameOrAlias() {
		return tableNameOrAlias;
	}
	
	public void setTableNameOrAlias(String tableNameOrAlias) {
		this.tableNameOrAlias = tableNameOrAlias;
	}

	/**
	 * Construto com parametross.
	 * 
	 * @param entityClass - classe de entidade
	 * @param tableNameOrAlias - nome ou alias da tablema
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 */
	public SimpleField(Class<?> entityClass, String tableNameOrAlias, 
			String fieldName, String alias) {
		super(alias);
		
		this.entityClass = entityClass;
		this.fieldName = fieldName;
		this.tableNameOrAlias = tableNameOrAlias;
	}
	
	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(tableNameOrAlias + ".");
		sql.append(fieldName);
		sql.append(mountAlias());
		return sql.toString();
	}

}