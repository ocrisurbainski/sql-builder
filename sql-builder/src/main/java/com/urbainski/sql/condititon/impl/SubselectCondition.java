package com.urbainski.sql.condititon.impl;

import static com.urbainski.sql.reflection.TableReflectionReader.getDatabaseNameField;
import static com.urbainski.sql.reflection.TableReflectionReader.getTableName;

import com.urbainski.sql.builder.SelectBuilder;
import com.urbainski.sql.db.types.ConditionDBTypes;

/**
 * Classe que representa uma condição de subselect.
 * 
 * @author Cristian Urbainski <cristian..urbainski@consisanet.com>
 * @since 27/09/2014
 * @version 1.0
 *
 */
public class SubselectCondition extends SimpleCondition {
	
	/**
	 * {@link SelectBuilder} para gerar o sql do subselect.
	 */
	protected SelectBuilder subselect;
	
	/**
	 * Construtor da classe.
	 * 
	 * @param entityClass - classe de entidade
	 * @param aliasTable - alias da tabela
	 * @param fieldName - nome do campo
	 * @param conditionType - tipo da condição
	 * @param subselect - query do subselect
	 */
	public SubselectCondition(Class<?> entityClass, String aliasTable,
			String fieldName, ConditionDBTypes conditionType, SelectBuilder subselect) {

		super(entityClass, aliasTable, conditionType, fieldName, null);
		this.subselect = subselect;
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
		sql.append(" (");
		sql.append(subselect.buildSQL());
		sql.append(")");
		
		return sql.toString();
	}

}
