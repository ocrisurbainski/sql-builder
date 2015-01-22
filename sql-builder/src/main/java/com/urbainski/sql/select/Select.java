package com.urbainski.sql.select;

import static com.urbainski.sql.reflection.TableReflectionReader.getAllFieldsNames;
import static com.urbainski.sql.reflection.TableReflectionReader.getTableName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.urbainski.sql.builder.SQL;
import com.urbainski.sql.builder.SelectBuilder;
import com.urbainski.sql.db.types.AggregateDBTypes;
import com.urbainski.sql.field.Field;
import com.urbainski.sql.field.impl.AggregateField;
import com.urbainski.sql.field.impl.FieldBuilder;
import com.urbainski.sql.field.impl.SimpleField;
import com.urbainski.sql.field.impl.SubselectField;

/**
 * Classe que representa o select da query.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public class Select implements SQL {
	
	/**
	 * Lista de campos da query.
	 */
	protected List<Field> fields;
	
	/**
	 * Classe de entidade.
	 */
	protected Class<?> entityClass;
	
	/**
	 * Alias da tabela.
	 */
	protected String alias;
	
	/**
	 * Nome da tabela ou seu alias.
	 */
	private String tableOrAlias;
	
	public List<Field> getFields() {
		return fields;
	}
	
	/**
	 * Construtor padrão da classe.
	 * @param entityClass - entidade
	 */
	public Select(Class<?> entityClass) {
		this.entityClass = entityClass;
		this.fields = new ArrayList<Field>();
		this.tableOrAlias = getTableNameOrTableAlias();
	}

	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param fields - campos
	 */
	public void addField(Field... fields) {
		this.fields.addAll(Arrays.asList(fields));
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param field - campo
	 */
	public void addField(Field field) {
		this.fields.add(field);
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param fieldName - nome do campo
	 * 
	 * @return {@link Field}
	 */
	public Field addField(String fieldName) {
		
		return addField(fieldName, "");
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 * 
	 * @return {@link Field}
	 */
	public Field addField(String fieldName, String alias) {
		
		Field field = FieldBuilder.newField(entityClass,
				this.tableOrAlias, fieldName, alias, true);
		this.fields.add(field);
		return field;
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param entityClass - classe de entidade
	 * @param fieldName - nome do campo
	 * 
	 * @return {@link Field}
	 */
	public Field addField(Class<?> entityClass, String fieldName) {
		
		Field field = FieldBuilder.newField(entityClass,
				getTableName(entityClass), fieldName, "", true);
		this.fields.add(field);
		return field;
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param entityClass - classe de entidade
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 * 
	 * @return {@link Field}
	 */
	public Field addField(Class<?> entityClass, String fieldName, String alias) {
		
		Field field = FieldBuilder.newField(entityClass,
				fieldName, alias);
		this.fields.add(field);
		return field;
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param entityClass - classe de entidade
	 * @param aliasEntity - alias da entidade
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 * 
	 * @return {@link Field}
	 */
	public Field addField(
			Class<?> entityClass, String aliasEntity, String fieldName, String alias) {
		
		Field field = FieldBuilder.newField(entityClass,
				aliasEntity, fieldName, alias, true);
		this.fields.add(field);
		return field;
	}
	
	/**
	 * Adiciona um {@link SubselectField} aos fields da query.
	 * 
	 * @param subselect - objeto que representa a subconsulta
	 * 
	 * @return {@link Field}
	 */
	public Field addField(SelectBuilder subselect) {
		
		Field field = FieldBuilder.newField(subselect);
		this.fields.add(field);
		return field;
	}
	
	/**
	 * Adiciona um {@link SubselectField} aos fields da query.
	 * 
	 * @param subselect - objeto que representa a subconsulta
	 * @param alias - alias da subconsulta
	 * 
	 * @return {@link Field}
	 */
	public Field addField(SelectBuilder subselect, String alias) {
		
		Field field = FieldBuilder.newField(subselect, alias);
		this.fields.add(field);
		return field;
	}
	
	/**
	 * Adiciona um {@link AggregateField} na consulta sql.
	 * 
	 * @param fieldName - nome do campo
	 * @param aggregateType - tipo de agregação
	 * 
	 * @return {@link Field}
	 */
	public Field addField(String fieldName, AggregateDBTypes aggregateType) {
		
		return addField(this.entityClass, this.tableOrAlias, fieldName, aggregateType);
	}
	
	/**
	 * Adiciona um {@link AggregateField} na consulta sql.
	 * 
	 * @param fieldName - nome do campo
	 * @param alias - apelido do campo
	 * @param aggregateType - tipo de agregação
	 * 
	 * @return {@link Field}
	 */
	public Field addField(String fieldName, String alias, AggregateDBTypes aggregateType) {
		
		return addField(this.entityClass, this.tableOrAlias, fieldName, alias, aggregateType);
	}
	
	/**
	 * Adiciona um {@link AggregateField} na consulta sql.
	 * 
	 * @param entityClass - classe de entidade
	 * @param fieldName - classe de entidade
	 * @param aggregateType - tipo de agregação
	 * 
	 * @return {@link Field}
	 */
	public Field addField(Class<?> entityClass, String fieldName, 
			AggregateDBTypes aggregateType) {
		
		return addField(entityClass, getTableName(entityClass), fieldName, aggregateType);
	}
	
	/**
	 * Adiciona um {@link AggregateField} na consulta sql.
	 * 
	 * @param entityClass - classe de entidade
	 * @param tableNameOrAlias - alias da entidade
	 * @param fieldName - classe de entidade
	 * @param aggregateType - tipo de agregação
	 * 
	 * @return {@link Field}
	 */
	public Field addField(Class<?> entityClass, String tableNameOrAlias, 
			String fieldName, AggregateDBTypes aggregateType) {
		
		return addField(entityClass, tableNameOrAlias, fieldName, "", aggregateType);
	}
	
	/**
	 * Adiciona um {@link AggregateField} na consulta sql.
	 * 
	 * @param entityClass - classe de entidade
	 * @param tableNameOrAlias - alias da entidade
	 * @param fieldName - classe de entidade
	 * @param alias - alias do campo
	 * @param aggregateType - tipo de agregação
	 * 
	 * @return {@link Field}
	 */
	public Field addField(Class<?> entityClass, String tableNameOrAlias, 
			String fieldName, String alias, AggregateDBTypes aggregateType) {
	
		Field field = FieldBuilder.newField(
				entityClass, tableNameOrAlias, fieldName, alias, aggregateType);
		this.fields.add(field);
		return field;
	}
	
	/**
	 * Método para setar o nome do alias.
	 * 
	 * @param alias - alias do from na query
	 */
	public void alias(String alias) {
		this.alias = alias;
		this.tableOrAlias = getTableNameOrTableAlias();

		for (Field f : fields) {
			if (f instanceof SimpleField) {
				
				final SimpleField simpleField = (SimpleField) f;
				if (this.entityClass.equals(simpleField.getEntityClass())) {
					simpleField.setTableNameOrAlias(alias);
				}
			}
		}
	}
	
	@Override
	public String buildSQL() {
		String tableNameOrAlias = getTableNameOrTableAlias();
		
		final StringBuilder str = new StringBuilder("");
		if (this.fields.isEmpty()) {
			final List<String> nameFields = getAllFieldsNames(entityClass);
			populateFields(tableNameOrAlias, nameFields);
		} 
		
		for (Field f : fields) {
			str.append(f.buildSQL());
			if (fields.indexOf(f) < (fields.size() - 1)) {
				str.append(", ");
			} 
		}
		
		return str.toString();
	}

	/**
	 * Método responsável por pegar o nome da tabela ou de seu alias.
	 * 
	 * @return nome da tabela ou seu alias
	 */
	private String getTableNameOrTableAlias() {
		String tableNameOrAlias = getTableName(entityClass);
		if (alias != null && !(alias.isEmpty())) {
			tableNameOrAlias = alias;
		}
		return tableNameOrAlias;
	}

	/**
	 * Método responsável por popular a lista de campos.
	 * 
	 * @param tableNameOrAlias - nome da tabela ou seu alias
	 * @param nameFields - lista de nome dos campos.
	 */
	private void populateFields(String tableNameOrAlias, List<String> nameFields) {
		for (String name : nameFields) {
			this.fields.add(FieldBuilder.newField(entityClass, tableNameOrAlias, name, false));
		}
	}

}