package com.urbainski.sql.builder.condititon.impl;

import java.util.Arrays;
import java.util.List;

import com.urbainski.sql.builder.condititon.Condition;
import com.urbainski.sql.builder.db.types.ConditionDBTypes;

public class BooleanCondition implements Condition {
	
	/**
	 * Lista de condições.
	 */
	protected List<Condition> listConditions;
	
	/**
	 * Lista de condições.
	 */
	protected ConditionDBTypes conditionType;
	
	public BooleanCondition(ConditionDBTypes conditionType, Condition... conditions) {
		this.conditionType = conditionType;
		this.listConditions = Arrays.asList(conditions);
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append("(");
		
		for (Condition c : listConditions) {
			sql.append(c.buildSQL());
			
			if (listConditions.indexOf(c) < (listConditions.size() - 1)) {
				sql.append(" ");
				sql.append(conditionType.getConditionType());
				sql.append(" ");
			}
		}
		
		sql.append(")");
		return sql.toString();
	}

}