package com.urbainski.sql.builder.condititon;

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
	public BetweenCondition(Class<?> entityClass, String fieldName,
			Object firstValue, Object secondValue) {
		super(entityClass, ConditionDBTypes.BETWEEN, fieldName, firstValue);
		this.secondValue = secondValue;
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(this.fieldName);
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
