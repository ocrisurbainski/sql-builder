package com.urbainski.sql.builder.condititon.impl;

import java.util.List;

import com.urbainski.sql.builder.condititon.Condition;
import com.urbainski.sql.builder.db.types.ConditionDBTypes;
import com.urbainski.sql.builder.reflection.TableReflectionReader;

/**
 * Implementação básica das condições do where.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
public class SimpleCondition implements Condition {

	/**
	 * Classe aonde é aplicado o where.
	 */
	protected Class<?> entityClass;
	
	/**
	 * Campo do where.
	 */
	protected String fieldName;
	
	/**
	 * Valores das condições.
	 */
	protected Object value;
	
	/**
	 * Tipo da condição a seraplicada.
	 */
	protected ConditionDBTypes conditionType;
	
	/**
	 * Construtor padrão.
	 * 
	 * @param entityClass
	 * @param conditionType
	 * @param fieldName
	 * @param value
	 */
	public SimpleCondition(Class<?> entityClass,
			ConditionDBTypes conditionType, String fieldName, Object value) {
		this.entityClass = entityClass;
		this.conditionType = conditionType;
		this.fieldName = fieldName;
		this.value = value;
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		try {
			sql.append(TableReflectionReader.getTableName(entityClass) + ".");
			sql.append(TableReflectionReader.getDatabaseNameField(
					entityClass, this.fieldName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.append(" ");
		sql.append(conditionType.getConditionType());
		sql.append(" ");
		
		if (ConditionDBTypes.IN.equals(conditionType)
				|| ConditionDBTypes.NOT_IN.equals(conditionType)) {
			sql.append("(");
			
			final List<?> list = (List<?>) value;
			for (Object o : list) {
				sql.append(getParameterValue(o));
				
				if (list.indexOf(o) < (list.size() - 1)) {
					sql.append(",");
				}
			}
			
			sql.append(")");
		} else {
			sql.append(getParameterValue(value));
		}
		return sql.toString();
	}

	public String getParameterValue(Object object) {
		if (object instanceof String) {
			return "'" + object.toString() + "'";
		} else {
			return object.toString();
		}
	}
}