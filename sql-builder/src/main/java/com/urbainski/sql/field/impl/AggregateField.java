package com.urbainski.sql.field.impl;

import com.urbainski.sql.db.types.AggregateDBTypes;

/**
 * Implementação de campos de agregação no sql.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 28/09/2014
 * @version 1.0
 *
 */
public class AggregateField extends SimpleField {
	
	/**
	 * Tipo de agregação aplicado no campo.
	 */
	protected AggregateDBTypes aggregateType;
	
	public AggregateDBTypes getAggregateType() {
		return aggregateType;
	}
	
	public void setAggregateType(AggregateDBTypes aggregateType) {
		this.aggregateType = aggregateType;
	}

	/**
	 * 
	 * @param entityClass - classe de entidade
	 * @param tableNameOrAlias - nome ou alias da tablema
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 * @param aggregateType - tipo de agregação
	 */
	public AggregateField(
			Class<?> entityClass, String tableNameOrAlias, 
			String fieldName, String alias, AggregateDBTypes aggregateType) {
		super(entityClass, tableNameOrAlias, fieldName, alias);
		this.aggregateType = aggregateType;
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(aggregateType.getAggregateType());
		sql.append("(");
		sql.append(tableNameOrAlias + ".");
		sql.append(fieldName);
		sql.append(")");
		sql.append(mountAlias());
		return sql.toString();
	}

}