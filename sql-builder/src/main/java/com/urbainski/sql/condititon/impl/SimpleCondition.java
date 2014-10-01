package com.urbainski.sql.condititon.impl;

import static com.urbainski.sql.reflection.TableReflectionReader.getDatabaseNameField;
import static com.urbainski.sql.reflection.TableReflectionReader.getTableName;

import java.util.List;

import com.urbainski.sql.condititon.Condition;
import com.urbainski.sql.db.types.ConditionDBTypes;

/**
 * Implementação básica das condições do where.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
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
	 * Alias da tabela.
	 */
	protected String aliasTable;
	
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
	 * @param tableFromAlias
	 * @param conditionType
	 * @param fieldName
	 * @param value
	 */
	public SimpleCondition(Class<?> entityClass, String tableFromAlias,
			ConditionDBTypes conditionType, String fieldName, Object value) {
		this.entityClass = entityClass;
		this.aliasTable = tableFromAlias;
		this.conditionType = conditionType;
		this.fieldName = fieldName;
		this.value = value;
	}
	
	/**
	 * Método para setar o alias na tabela.
	 * 
	 * @param alias - alias da tabela
	 */
	public void aliasTable(String alias) {
		this.aliasTable = alias;
	}

	@Override
	public String buildSQL() {
		String tableNameOrAlias = getTableName(entityClass);
		if (this.aliasTable != null && !(this.aliasTable.isEmpty())) {
			tableNameOrAlias = this.aliasTable;
		}
		
		final StringBuilder sql = new StringBuilder();
		sql.append(tableNameOrAlias + ".");
		sql.append(getDatabaseNameField(entityClass, this.fieldName));
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