package com.urbainski.sql.builder.condititon.impl;

import com.urbainski.sql.builder.condititon.Condition;
import com.urbainski.sql.builder.db.types.ConditionDBTypes;
import com.urbainski.sql.builder.reflection.TableReflectionReader;

/**
 * Implementação de condições para o join.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 22/09/2014
 * @version 1.0
 *
 */
public class JoinCondition implements Condition {
	
	/**
	 * Classe que saiu a união.
	 */
	protected Class<?> entityFrom;
	
	/**
	 * Classe do que foi feito o join.
	 */
	protected Class<?> joinedClass;
	
	/**
	 * Alias da classe do from.
	 */
	protected String fromAlias;
	
	/**
	 * Alias da classe que foi feito o join.
	 */
	protected String joinedAlias;
	
	/**
	 * Propriedade 1.
	 */
	protected String prop1;
	
	/**
	 * Propriedade 2.
	 */
	protected String prop2;
	
	/**
	 * Tipo da condição.
	 */
	protected ConditionDBTypes conditionType;
	
	/**
	 * Construtor padrão.
	 * 
	 * @param entityFrom
	 * @param fromAlias
	 * @param joinedClass
	 * @param joinedAlias
	 * @param conditionType
	 * @param prop1
	 * @param prop2
	 */
	public JoinCondition(
			Class<?> entityFrom, String fromAlias,
			Class<?> joinedClass, String joinedAlias,
			ConditionDBTypes conditionType, String prop1, String prop2) {
		this.entityFrom = entityFrom;
		this.fromAlias = fromAlias;
		this.joinedClass = joinedClass;
		this.joinedAlias = joinedAlias;
		this.conditionType = conditionType;
		this.prop1 = prop1;
		this.prop2 = prop2;
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(((fromAlias == null || fromAlias.isEmpty())
				? TableReflectionReader.getTableName(entityFrom) : fromAlias) + ".");
		sql.append(TableReflectionReader.getDatabaseNameField(entityFrom, prop1));
		sql.append(" ");
		sql.append(conditionType.getConditionType());
		sql.append(" ");
		sql.append(((joinedAlias == null || joinedAlias.isEmpty())
				? TableReflectionReader.getTableName(joinedClass) : joinedAlias) + ".");
		sql.append(TableReflectionReader.getDatabaseNameField(joinedClass, prop2));
		return sql.toString();
	}

}
