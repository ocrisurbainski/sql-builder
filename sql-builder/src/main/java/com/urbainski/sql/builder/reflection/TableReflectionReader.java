package com.urbainski.sql.builder.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

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
				return ((Table) a).name();
			}
		}
		return entityClass.getName().toLowerCase();
	}
	
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
				if (f.getAnnotations() != null & f.getAnnotations().length > 0) {
					nameFields.add(f.getName().toLowerCase());
				}
			}
		}
		return nameFields;
	}
	
	public static String getDatabaseNameField(Class<?> entityClass, String nameProperty)
			throws Exception {
		final Field[] fields = entityClass.getDeclaredFields();
		for (final Field f : fields) {
			if (f.getName().equals(nameProperty)) {
				
				final Column column = (Column) f.getAnnotation(Column.class);
				if (column != null && column.name() != null && !column.name().isEmpty()) {
					return column.name();
				} else {
					return f.getName().toLowerCase();
				}
			}
		}
		
		throw new Exception("Classe de entidade: " + entityClass + " não contem o campo: " + nameProperty);
	}

	public static void getJoinInformation(Class<?> clazzDe, Class<?> clazzPara) {
		// TODO Auto-generated method stub
		
	}
}