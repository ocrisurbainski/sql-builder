package com.urbainski.sql.condititon.impl;

import static com.urbainski.sql.reflection.TableReflectionReader.getDatabaseNameField;
import static com.urbainski.sql.reflection.TableReflectionReader.getTableName;

import com.urbainski.sql.db.types.ConditionDBTypes;

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
		String tableNameOrAlias = getTableName(entityClass);
		if (this.aliasTable != null && !(this.aliasTable.isEmpty())) {
			tableNameOrAlias = this.aliasTable;
		}
		
		final StringBuilder sql = new StringBuilder();
		sql.append(tableNameOrAlias + ".");
		sql.append(getDatabaseNameField(entityClass, fieldName));
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
