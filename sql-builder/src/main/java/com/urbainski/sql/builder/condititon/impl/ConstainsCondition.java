package com.urbainski.sql.builder.condititon.impl;

import com.urbainski.sql.builder.db.types.ConditionDBTypes;
import com.urbainski.sql.builder.db.types.ConstainsDBTypes;
import com.urbainski.sql.builder.reflection.TableReflectionReader;

/**
 * Classe que implementar a condição de conter um determinado valor.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 21/09/2014
 * @version 1.0
 *
 */
public class ConstainsCondition extends SimpleCondition {

	/**
	 * Tipo do contains do banco de dados.
	 */
	protected ConstainsDBTypes containsType;
	
	/**
	 * Construtor padrão da classe.
	 * 
	 * @param entityClass
	 * @param conditionType
	 * @param fieldName
	 * @param value
	 */
	public ConstainsCondition(ConstainsDBTypes containsType, Class<?> entityClass,
			ConditionDBTypes conditionType, String fieldName, Object value) {
		super(entityClass, conditionType, fieldName, value);
		this.containsType = containsType;
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		try {
			sql.append(TableReflectionReader.getDatabaseNameField(entityClass, fieldName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.append(" ");
		sql.append(conditionType.getConditionType());
		sql.append(" ");
		
		if (ConstainsDBTypes.ANY.equals(containsType)) {
			sql.append("'%");
			sql.append(value.toString());
			sql.append("%'");
		} else if (ConstainsDBTypes.IN_FINISH.equals(containsType)) {
			sql.append("'");
			sql.append(value.toString());
			sql.append("%'");
		} else if (ConstainsDBTypes.IN_START.equals(containsType)) {
			sql.append("'%");
			sql.append(value.toString());
			sql.append("'");
		}
		
		return sql.toString();
	}
	
}
