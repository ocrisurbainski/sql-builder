package com.urbainski.sql.field.impl;

import com.urbainski.sql.builder.SelectBuilder;
import com.urbainski.sql.field.Field;

/**
 * Classe que represetnta o campo de subselect no corpo da query.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 28/09/2014
 * @version 1.0
 *
 */
public class SubselectField extends Field {
	
	/**
	 * Objeto para montagem do subselect.
	 */
	protected SelectBuilder subselect;
	
	public SelectBuilder getSubselect() {
		return subselect;
	}
	
	public void setSubselect(SelectBuilder subselect) {
		this.subselect = subselect;
	}
	
	/**
	 * Cosntrutor da classe.
	 * 
	 * @param subselect - objeto para montar subselect.
	 * @param alias - alias do campo
	 */
	public SubselectField(SelectBuilder subselect, String alias) {
		super(alias);
		this.subselect = subselect;
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append("(");
		sql.append(subselect.buildSQL());
		sql.append(")");
		sql.append(mountAlias());
		return sql.toString();
	}

}