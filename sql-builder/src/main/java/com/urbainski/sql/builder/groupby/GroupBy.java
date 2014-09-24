package com.urbainski.sql.builder.groupby;

import static com.urbainski.sql.util.SQLUtils.GROUP_BY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.urbainski.sql.builder.Builder;
import com.urbainski.sql.builder.field.Field;

/**
 * Classe que representa o group by da query.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 24/09/2014
 * @version 1.0
 *
 */
public class GroupBy implements Builder {
	
	/**
	 * Lista de campos.
	 */
	protected List<Field> fields;
	
	/**
	 * Construtor padrão.
	 */
	public GroupBy() {
		this.fields = new ArrayList<Field>();
	}
	
	/**
	 * Método que adiciona um campo na consulta.
	 * 
	 * @param fields - campos
	 */
	public void addField(Field... fields) {
		this.fields.addAll(Arrays.asList(fields));
	}
	
	/**
	 * Método que adiciona um campo na consulta.
	 * 
	 * @param f - campo
	 */
	public void addField(Field f) {
		this.fields.add(f);
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(GROUP_BY);
		sql.append(" ");
		
		for (Field f : fields) {
			sql.append(f.getTableNameOrAlias() + ".");
			sql.append(f.getFieldName());
			
			if (fields.indexOf(f) < (fields.size() - 1)) {
				sql.append(", ");
			}
		}

		return sql.toString();
	}

}
