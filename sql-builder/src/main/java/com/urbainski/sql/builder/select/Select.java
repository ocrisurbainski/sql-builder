package com.urbainski.sql.builder.select;

import static com.urbainski.sql.util.SQLUtils.AS;

import java.util.ArrayList;
import java.util.List;

import com.urbainski.sql.builder.Builder;
import com.urbainski.sql.builder.reflection.TableReflectionReader;

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
	private Class<?> entityClass;
	
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
		final Field field = new Field(fieldName, alias);
		this.fields.add(field);
	}
	
	/**
	 * Construtor padrão da classe.
	 * @param entityClass - entidade
	 */
	public Select(Class<?> entityClass) {
		this.entityClass = entityClass;
		this.fields = new ArrayList<Field>();
	}

	@Override
	public String buildSQL() {
		final StringBuilder str = new StringBuilder("");
		try {
			if (this.fields.isEmpty()) {
				final List<String> nameFields = TableReflectionReader.getAllFieldsNames(entityClass);
				for (String name : nameFields) {
					str.append(name);
					
					if (nameFields.indexOf(name) < (nameFields.size() - 1)) {
						str.append(", ");
					} else {
						str.append(" ");
					}
				}
			} else {
				for (Field f : fields) {
					str.append(TableReflectionReader.getDatabaseNameField(entityClass, f.getFieldName()));
					
					if (!f.getAlias().isEmpty()) {
						str.append(" ");
						str.append(AS);
						str.append(" ");
						str.append(f.getAlias());
					}
					
					if (fields.indexOf(f) < (fields.size() - 1)) {
						str.append(", ");
					} else {
						str.append(" ");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str.toString();
	}

}
