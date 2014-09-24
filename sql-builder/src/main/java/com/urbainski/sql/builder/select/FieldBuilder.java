package com.urbainski.sql.builder.select;

import static com.urbainski.sql.builder.reflection.TableReflectionReader.getDatabaseNameField;

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
	 * @param tableNameOrAlias - nome ou alias da tabela
	 * @param fieldName
	 * 
	 * @return {@link Field}
	 */
	public static Field newField(Class<?> entityClass, String tableNameOrAlias, String fieldName) {
		return newField(entityClass, tableNameOrAlias, fieldName, "");
	}
	
	/**
	 * Método que constroi uma instância de {@link Field}.
	 * 
	 * @param tableNameOrAlias - nome ou alias da tabela
	 * @param fieldName
	 * 
	 * @return {@link Field}
	 */
	public static Field newField(String tableNameOrAlias, String fieldName) {
		return newField(tableNameOrAlias, fieldName, "");
	}
	
	/**
	 * Método que constroi uma instância de {@link Field}.
	 * 
	 * @param entityClass - classe de entidade
	 * @param tableNameOrAlias - nome ou alias da tabela
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 * 
	 * @return {@link Field}
	 */
	public static Field newField(
			Class<?> entityClass, String tableNameOrAlias, String fieldName, String alias) {
		final Field field = new Field(
				tableNameOrAlias, getDatabaseNameField(entityClass, fieldName), alias);
		return field;
	}
	
	/**
	 * Método que constroi uma instância de {@link Field}.
	 * 
	 * @param tableNameOrAlias - nome ou alias da tabela
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 * 
	 * @return {@link Field}
	 */
	public static Field newField(
			String tableNameOrAlias, String fieldName, String alias) {
		final Field field = new Field(tableNameOrAlias, fieldName, alias);
		return field;
	}
	
}