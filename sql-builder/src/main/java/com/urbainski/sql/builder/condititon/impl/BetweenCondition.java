package com.urbainski.sql.builder.condititon.impl;

import com.urbainski.sql.builder.db.types.ConditionDBTypes;
import com.urbainski.sql.builder.reflection.TableReflectionReader;

/**
 * Classe que representa a condição de between.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisnaet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
public class BetweenCondition extends SimpleCondition {

	/**
	 * Segundo valor da consulta.
	 */
	private Object secondValue;
	
	/**
	 * Construtor padrão.
	 * 
	 * @param entityClass
	 * @param conditionType
	 * @param fieldName
	 * @param firstValue
	 * @param secondValue
	 */
	public BetweenCondition(Class<?> entityClass, String tableFromAlias, 
			String fieldName, Object firstValue, Object secondValue) {
		super(entityClass, tableFromAlias, ConditionDBTypes.BETWEEN, fieldName, firstValue);
		this.secondValue = secondValue;
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		try {
			sql.append(((this.aliasTable == null || this.aliasTable.isEmpty())
					? TableReflectionReader.getTableName(entityClass) : this.aliasTable) + ".");
			sql.append(TableReflectionReader.getDatabaseNameField(entityClass, fieldName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.append(" ");
		sql.append(conditionType.getConditionType());
		sql.append(" ");
		sql.append(value.toString());
		sql.append(" ");
		sql.append(ConditionDBTypes.AND.getConditionType());
		sql.append(" ");
		sql.append(secondValue.toString());
		
		return sql.toString();
	}

}
