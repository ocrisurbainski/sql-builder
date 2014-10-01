package com.urbainski.sql.condititon.impl;

import static com.urbainski.sql.reflection.TableReflectionReader.getDatabaseNameField;
import static com.urbainski.sql.reflection.TableReflectionReader.getTableName;

import com.urbainski.sql.db.types.ConditionDBTypes;
import com.urbainski.sql.db.types.ConstainsDBTypes;

/**
 * Classe que implementar a condição de conter um determinado valor.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
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
	public ConstainsCondition(ConstainsDBTypes containsType, String tableFromAlias,
			Class<?> entityClass, ConditionDBTypes conditionType, 
			String fieldName, Object value) {
		super(entityClass, tableFromAlias, conditionType, fieldName, value);
		this.containsType = containsType;
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
