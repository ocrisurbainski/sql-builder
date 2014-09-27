package com.urbainski.sql.builder.field;

import static com.urbainski.sql.builder.reflection.TableReflectionReader.getDatabaseNameField;
import static com.urbainski.sql.builder.reflection.TableReflectionReader.getTableName;

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
		
		final Field field = new Field(
				entityClass, tableNameOrAlias, fieldName, alias);
		return field;
	}
	
}