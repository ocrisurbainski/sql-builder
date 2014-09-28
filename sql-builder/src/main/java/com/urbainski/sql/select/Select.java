package com.urbainski.sql.select;

import static com.urbainski.sql.reflection.TableReflectionReader.getAllFieldsNames;
import static com.urbainski.sql.reflection.TableReflectionReader.getTableName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.urbainski.sql.builder.SQL;
import com.urbainski.sql.builder.SQLBuilder;
import com.urbainski.sql.field.Field;
import com.urbainski.sql.field.FieldBuilder;
import com.urbainski.sql.field.SimpleField;
import com.urbainski.sql.field.SubselectField;

/**
 * Classe que representa o select da query.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
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
	 */
	public void addField(String fieldName) {
		addField(fieldName, "");
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 */
	public void addField(String fieldName, String alias) {
		this.fields.add(FieldBuilder.newField(entityClass,
				this.tableOrAlias, fieldName, alias, true));
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param entityClass - classe de entidade
	 * @param fieldName - nome do campo
	 */
	public void addField(Class<?> entityClass, String fieldName) {
		this.fields.add(FieldBuilder.newField(entityClass,
				getTableName(entityClass), fieldName, "", true));
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param entityClass - classe de entidade
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 */
	public void addField(Class<?> entityClass, String fieldName, String alias) {
		this.fields.add(FieldBuilder.newField(entityClass,
				fieldName, alias));
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param entityClass - classe de entidade
	 * @param aliasEntity - alias da entidade
	 * @param fieldName - nome do campo
	 * @param alias - alias do campo
	 */
	public void addField(
			Class<?> entityClass, String aliasEntity, String fieldName, String alias) {
		this.fields.add(FieldBuilder.newField(entityClass,
				aliasEntity, fieldName, alias, true));
	}
	
	/**
	 * Adiciona um {@link SubselectField} aos fields da query.
	 * 
	 * @param subselect - objeto que representa a subconsulta
	 */
	public void addField(SQLBuilder subselect) {
		this.fields.add(FieldBuilder.newField(subselect));
	}
	
	/**
	 * Adiciona um {@link SubselectField} aos fields da query.
	 * 
	 * @param subselect - objeto que representa a subconsulta
	 * @param alias - alias da subconsulta
	 */
	public void addField(SQLBuilder subselect, String alias) {
		this.fields.add(FieldBuilder.newField(subselect, alias));
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
	 * @return
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