package com.urbainski.sql.by;

import static com.urbainski.sql.reflection.TableReflectionReader.getTableName;

import java.util.ArrayList;
import java.util.List;

import com.urbainski.sql.builder.SQL;
import com.urbainski.sql.field.Field;
import com.urbainski.sql.field.impl.AggregateField;
import com.urbainski.sql.field.impl.FieldBuilder;
import com.urbainski.sql.field.impl.SimpleField;

/**
 * Classe abstrata que fornece uma implmentação das clausulas by.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 03/10/2014
 * @version 1.0
 *
 */
public abstract class AbstractClauseBy implements SQL {
	
	/**
	 * Lista de campos.
	 */
	protected List<Field> fields;
	
	/**
	 * Entidade principal.
	 */
	protected Class<?> entityClass;
	
	/**
	 * Alias do from.
	 */
	protected String fromAlias;
	
	/**
	 * Método que recebe o alias do from e o replica para seus objetos internos.
	 * 
	 * @param fromAlias - alias do froom
	 */
	public void setFromAlias(String fromAlias) {
		this.fromAlias = fromAlias;
		
		for (Field f : fields) {
			if (f instanceof SimpleField) {
				SimpleField simpleField = (SimpleField) f;
				if (entityClass.equals(simpleField.getEntityClass())) {
					simpleField.setAlias(fromAlias);
				}
			}
 		}
	}
	
	/**
	 * Construtor padrão.
	 * 
	 * @param entityClass - classe de entidade
	 * @param fromAlias - alias do from
	 */
	public AbstractClauseBy(Class<?> entityClass, String fromAlias) {
		this.entityClass = entityClass;
		this.fromAlias = fromAlias;
		this.fields = new ArrayList<Field>();
	}
	
	/**
	 * Método que adiciona um campo na consulta.
	 * 
	 * @param f - campo
	 */
	public void addField(Field f) {
		this.fields.add(f);
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param fieldName - nome do campo
	 * 
	 * @return {@link Field}
	 */
	public Field addField(String fieldName) {
		
		String tableOrAlias = getTableName(entityClass);
		if (fromAlias != null && !(fromAlias.isEmpty())) {
			tableOrAlias = fromAlias;
		}
		return addField(entityClass, tableOrAlias, fieldName);
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param entity - classe de entidade
	 * @param fieldName - nome do campo
	 * 
	 * @return {@link Field}
	 */
	public Field addField(Class<?> entity, String fieldName) {
		
		String tableOrAlias = getTableName(entity);
		return addField(entity, tableOrAlias, fieldName);
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param entity - classe de entidade
	 * @param tableOrAlias - nome da tabela ou alias
	 * @param fieldName - nome do campo
	 * 
	 * @return {@link Field}
	 */
	public Field addField(Class<?> entity, String tableOrAlias, String fieldName) {
		
		Field field = FieldBuilder.newField(
				entity, tableOrAlias, fieldName, "", true);
		this.fields.add(field);
		return field;
	}
	
	/**
	 * Retorna o tipo da operação.
	 * 
	 * @return tipo
	 */
	public abstract String getType();

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(getType());
		sql.append(" ");
		
		for (Field f : fields) {
			if (f instanceof AggregateField) {
				AggregateField aggregateField = (AggregateField) f;
				
				if (aggregateField.getAlias() != null 
						&& !(aggregateField.getAlias().isEmpty())) {
					sql.append(aggregateField.getAlias());
				} else {
					sql.append(aggregateField.getAggregateType().getAggregateType());
					sql.append("(");
					sql.append(aggregateField.getFieldName());
					sql.append(")");
				}
			} else {
				final SimpleField simpleField = (SimpleField) f;
				
				sql.append(simpleField.getTableNameOrAlias() + ".");
				sql.append(simpleField.getFieldName());
			}

			if (fields.indexOf(f) < (fields.size() - 1)) {
				sql.append(", ");
			}
		}
		return sql.toString();
	}

}