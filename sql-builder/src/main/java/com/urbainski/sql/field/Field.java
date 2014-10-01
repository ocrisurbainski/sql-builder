package com.urbainski.sql.field;

import static com.urbainski.sql.db.types.SQLSelectDBTypes.AS;

import com.urbainski.sql.builder.SQL;

/**
 * Interface que representa um campo de um select.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public abstract class Field implements SQL {
	
	/**
	 * Alias do campo.
	 */
	protected String alias;
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	/**
	 * Construtor padrão da classe.
	 * 
	 * @param alias - alias do campo na consulta.
	 */
	public Field(String alias) {
		this.alias = alias;
	}
	
	/**
	 * Método que retorna se o campo tem alias.
	 * 
	 * @return <code>true</code> se o campo tem alias 
	 * 		caso contrário <code>false</code>
	 */
	protected boolean hasAlias() {
		return alias != null && !(alias.isEmpty());
	}
	
	/**
	 * Método que monta o sql do alias do campo.
	 * 
	 * @return sql do alias
	 */
	protected String mountAlias() {
		if (!hasAlias()) {
			return "";
		}
		
		final StringBuilder sql = new StringBuilder();
		sql.append(" ");
		sql.append(AS.getSQLSelectType());
		sql.append(" ");
		sql.append(alias);
		return sql.toString();
	}
}