package com.urbainski.sql.builder.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.urbainski.sql.builder.condititon.impl.ConditionBuilder;
import com.urbainski.sql.builder.condititon.impl.JoinCondition;
import com.urbainski.sql.builder.db.types.ConditionDBTypes;

/**
 * Classe que le uma classe por reflection para pegar as informações
 * provinientes das anotações da jpa.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public class TableReflectionReader {
	
	/**
	 * Método que retorna o nome da tabela.
	 *  
	 * @return nome da tabela
	 */
	public static String getTableName(Class<?> entityClass) {
		final Annotation[] annotations = entityClass.getAnnotations();
		for (Annotation a : annotations) {
			if (a instanceof Table) {
				final Table table = (Table) a;
				if (table.name() == null || table.name().isEmpty()) {
					return entityClass.getName().toLowerCase();
				} else {
					return ((Table) a).name();
				}
			}
		}
		return entityClass.getName().toLowerCase();
	}
	
	/**
	 * Método responsável por ler a entidade e pegar o nome de todos os atributos do banco de dados.
	 * 
	 * @param entityClass - classe de entidade do banco de dados
	 * 
	 * @return {@link List} de {@link String} que são os nomes dos atributos da tabela no banco de dados
	 */
	public static List<String> getAllFieldsNames(Class<?> entityClass) {
		final List<String> nameFields = new ArrayList<String>();
		final Field[] fields = entityClass.getDeclaredFields();
		for (final Field f : fields) {
			if (f.getAnnotation(Transient.class) != null) {
				continue;
			}
			
			final Column column = (Column) f.getAnnotation(Column.class);
			if (column != null && column.name() != null && !column.name().isEmpty()) {
				nameFields.add(column.name());
			} else {
				
				final JoinColumn joinColumn = (JoinColumn) f.getAnnotation(JoinColumn.class);
				if (joinColumn != null) {
					continue;
				}
				
				if (f.getAnnotations() != null & f.getAnnotations().length > 0) {
					nameFields.add(f.getName().toLowerCase());
				}
			}
		}
		return nameFields;
	}
	
	/**
	 * Método que busca o nome do atributo em especifico no banco de dados.
	 * 
	 * @param entityClass - classe de entidade do banco de dados
	 * @param nameProperty - nome da propriedade na classe de entidade
	 * 
	 * @return nome da propriedade no banco de dados
	 */
	public static String getDatabaseNameField(Class<?> entityClass, String nameProperty) {
		final Field field = getField(entityClass, nameProperty);

		if (field != null) {
			final Column column = (Column) field.getAnnotation(Column.class);
			if (column != null && column.name() != null && !column.name().isEmpty()) {
				return column.name();
			} else {
				return field.getName().toLowerCase();
			}
		}
		
		throw new IllegalStateException("Classe de entidade: " + entityClass + " não contem o campo: " + nameProperty);
	}

	/**
	 * Método que le classe de entidade e monta condição de join com base nas anotações do jpa.
	 * 
	 * @param entityClass - classe de entidade
	 * @param nameProperty - nome da propriedade
	 * 
	 * @return {@link JoinCondition}
	 */
	public static JoinCondition getJoinInformation(Class<?> entityClass, String aliasFrom, String aliasJoined, String nameProperty) {
		final Field field = getField(entityClass, nameProperty);
		
		if (field != null) {
			final JoinColumn joinColumn = (JoinColumn) field.getAnnotation(JoinColumn.class);
			if (joinColumn != null) {
				return ConditionBuilder.newJoinCondition(
						entityClass, aliasFrom, field.getType(), aliasJoined, ConditionDBTypes.EQUALS, 
						joinColumn.name(), joinColumn.referencedColumnName());
			}
			
			throw new IllegalStateException("Propriedade: " + nameProperty + " na classe: " 
					+ entityClass + " não possui informações do join");
		}
		
		throw new IllegalStateException("Classe de entidade: " + entityClass + " não contem o campo: " + nameProperty);
	}
	
	/**
	 * Método que localiza um campo dentro da entidade.
	 * 
	 * @param entityClass - classe 
	 * @param nameProperty - nome da propriedade
	 * 
	 * @return {@link Field}
	 */
	private static Field getField(Class<?> entityClass, String nameProperty) {
		final Field[] fields = entityClass.getDeclaredFields();
		for (final Field f : fields) {
			if (f.getName().equals(nameProperty)) {
				return f;
			}
		}
		return null;
	}
}