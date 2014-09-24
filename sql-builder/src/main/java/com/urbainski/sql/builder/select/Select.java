package com.urbainski.sql.builder.select;

import static com.urbainski.sql.builder.reflection.TableReflectionReader.getAllFieldsNames;
import static com.urbainski.sql.builder.reflection.TableReflectionReader.getTableName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.urbainski.sql.builder.Builder;

/**
 * Classe que representa o select da query.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public class Select implements Builder {
	
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
				this.tableOrAlias, fieldName, alias));
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
			f.setTableNameOrAlias(alias);
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
		
		str.append(" ");
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
			this.fields.add(FieldBuilder.newField(tableNameOrAlias, name));
		}
	}

}