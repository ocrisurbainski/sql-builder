package com.urbainski.sql.condititon.impl;

import java.util.Arrays;
import java.util.List;

import com.urbainski.sql.condititon.Condition;
import com.urbainski.sql.db.types.ConditionDBTypes;

/**
 * Classe que representa as condições boleanas 'AND' e 'OR'.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 20/09/2014
 *
 */
public class BooleanCondition implements Condition {
	
	/**
	 * Lista de condições.
	 */
	protected List<Condition> listConditions;
	
	/**
	 * Lista de condições.
	 */
	protected ConditionDBTypes conditionType;
	
	/**
	 * Construtor das condições booleanas.
	 * 
	 * @param conditionType - tipo da condição boolean
	 * @param conditions - todas as condições aplicadas
	 */
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

	/**
	 * Método para atualizar o alias das condições.
	 * 
	 * @param entity - entidade que teve o alias atualizado
	 * @param newAlias - novo alias
	 */
	public void updateFromAlias(Class<?> entity, String newAlias) {
		for (Condition c : listConditions) {
			ConditionBuilder.updateAliasForCondition(c, entity, newAlias);
		}
	}

}