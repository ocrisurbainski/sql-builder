package com.urbainski.sql.field.impl;

import static com.urbainski.sql.reflection.TableReflectionReader.getDatabaseNameField;
import static com.urbainski.sql.reflection.TableReflectionReader.getTableName;

import com.urbainski.sql.builder.SQLBuilder;
import com.urbainski.sql.db.types.AggregateDBTypes;
import com.urbainski.sql.field.Field;

/**
 * Classe responsável por construir um objeto {@link Field}.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 23/09/2014
 * @version 1.0
 *
 */
public final class FieldBuilder {

	/**
	 * Construtor padrão da classe.
	 */
	private FieldBuilder() {
		
	}
	
	/**
	 * Método que constroi uma instância de {@link Field}.
	 * 
	 * @param entityClass - classe de entidade
	 * @param fieldName - nome do campo
	 * @param fieldAlias - alias do campo
	 * 
	 * @return {@link Field}
	 */
	public static Field newField(
			Class<?> entityClass, String fieldName, String fieldAlias) {
		
		return newField(entityClass, getTableName(entityClass), fieldName, 
				fieldAlias, true);
	}
	
	/**
	 * Método que constroi uma instância de {@link Field}.
	 * 
	 * @param entityClass - classe de entidade
	 * @param tableNameOrAlias - nome ou alias da tabela
	 * @param fieldName - nome do campo
	 * @param fieldAlias - alias do campo
	 * 
	 * @return {@link Field}
	 */
	public static Field newField(
			Class<?> entityClass, String tableNameOrAlias, 
			String fieldName, String fieldAlias) {
		
		return newField(entityClass, tableNameOrAlias, fieldName, fieldAlias, true);
	}
	
	/**
	 * Método que constroi uma instância de {@link Field}.
	 * 
	 * @param entityClass - classe de entidade
	 * @param tableNameOrAlias - nome ou alias da tabela
	 * @param fieldName - nome do campo
	 * @param readFielOfEntity - se true le a propriedade de dentro de entidade
	 * 		caso contrário apenas a adicona no campo
	 * 
	 * @return {@link Field}
	 */
	public static Field newField(
			Class<?> entityClass, String tableNameOrAlias, 
			String fieldName, boolean readFielOfEntity) {
		
		return newField(entityClass, tableNameOrAlias, fieldName, "", readFielOfEntity);
	}
	
	/**
	 * Método que constroi uma instância de {@link Field}.
	 * 
	 * @param entityClass - classe de entidade
	 * @param tableNameOrAlias - nome ou alias da tabela
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 * @param readFielOfEntity - se true le a propriedade de dentro de entidade
	 * 		caso contrário apenas a adicona no campo
	 * 
	 * @return {@link Field}
	 */
	public static Field newField(
			Class<?> entityClass, String tableNameOrAlias, String fieldName, 
			String alias, boolean readFielOfEntity) {
		
		if (readFielOfEntity) {
			fieldName = getDatabaseNameField(entityClass, fieldName);
		}
		
		final Field field = new SimpleField(
				entityClass, tableNameOrAlias, fieldName, alias);
		return field;
	}
	
	/**
	 * Método que monta um {@link Field} do tipo {@link SubselectField}.
	 * 
	 * @param subselect - subselect
	 * 
	 * @return {@link SubselectField}
	 */
	public static Field newField(SQLBuilder subselect) {
		
		return newField(subselect, "");
	}
	
	/**
	 * Método que monta um {@link Field} do tipo {@link SubselectField}.
	 * 
	 * @param subselect - subselect
	 * @param alias - alias do subselect
	 * 
	 * @return {@link SubselectField}
	 */
	public static Field newField(SQLBuilder subselect, String alias) {
		
		return new SubselectField(subselect, alias);
	}
	
	/**
	 * Método que constroi um {@link AggregateField}.
	 * 
	 * @param entityClass - classe de entidade
	 * @param tableNameOrAlias - alias da tabela
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 * @param aggregateType - tipo de agregação
	 * @return
	 */
	public static Field newField(Class<?> entityClass, String tableNameOrAlias, 
			String fieldName, String alias, AggregateDBTypes aggregateType) {
		
		return new AggregateField(
				entityClass, tableNameOrAlias, 
				getDatabaseNameField(entityClass, fieldName), alias, aggregateType);
	}
	
}