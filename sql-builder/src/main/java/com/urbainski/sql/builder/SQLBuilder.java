package com.urbainski.sql.builder;

import static com.urbainski.sql.util.SQLUtils.DISTINCT;
import static com.urbainski.sql.util.SQLUtils.FROM;
import static com.urbainski.sql.util.SQLUtils.SELECT;
import static com.urbainski.sql.util.SQLUtils.WHERE;

import java.util.ArrayList;
import java.util.List;

import com.urbainski.sql.builder.condititon.Condition;
import com.urbainski.sql.builder.condititon.ConditionBuilder;
import com.urbainski.sql.builder.condititon.ConditionDBTypes;
import com.urbainski.sql.builder.condititon.ConstainsDBTypes;
import com.urbainski.sql.builder.join.Join;
import com.urbainski.sql.builder.reflection.TableReflectionReader;
import com.urbainski.sql.builder.select.Select;

/**
 * Classe que representa um sql para consulta no banco de dados.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public class SQLBuilder implements Builder {

	/**
	 * Objeto que representa os campos do select.
	 */
	protected Select select;
	
	/**
	 * Condição where da minha query.
	 */
	protected Condition where;
	
	/**
	 * Lista de joins.
	 */
	protected List<Join<?, ?>> joins;
	
	/**
	 * Classe de entidade do banco de dados.
	 */
	protected Class<?> entityClass;
	
	/**
	 * Boolean distinct;
	 */
	protected boolean distinct;
	
	public SQLBuilder(Class<?> entityClass) {
		this.entityClass = entityClass;
		this.select = new Select(entityClass);
		this.joins = new ArrayList<Join<?,?>>();
		this.distinct = false;
	}
	
	public Select select() {
		return select;
	}
	
	public void where(ConditionDBTypes type, String fieldName, Object... value) {
		this.where = ConditionBuilder.newCondition(
				this.entityClass, type, fieldName, value);
	}
	
	public void where(ConstainsDBTypes containsType, ConditionDBTypes type, String fieldName, Object value) {
		this.where = ConditionBuilder.newCondition(
				this.entityClass, containsType, type, fieldName, value);
	}
	
	public void where(Class<?> entiyClass, ConstainsDBTypes containsType, 
			ConditionDBTypes type, String fieldName, Object value) {
		this.where = ConditionBuilder.newCondition(
				entityClass, containsType, type, fieldName, value);
	}
	
	public void where(ConditionDBTypes type, Class<?> entiyClass, 
			String fieldName, Object... value) {
		this.where = ConditionBuilder.newCondition(entiyClass, type, fieldName, value);
	}
	
	public void where(Condition condition) {
		this.where = condition;
	}
	
	public void addJoin(Join<?, ?> join) {
		this.joins.add(join);
	}
	
	public void distinct(boolean distinct) {
		this.distinct = distinct;
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(SELECT);
		sql.append(" ");
		
		if (distinct) {
			sql.append(DISTINCT);
			sql.append(" ");
		}
		
		sql.append(select.buildSQL());
		sql.append(FROM);
		sql.append(" ");
		sql.append(TableReflectionReader.getTableName(entityClass));
		
		if (!joins.isEmpty()) {
			for (Join<?, ?> j : joins) {
				sql.append(j.buildSQL());
			}
		}
		
		if (where != null) {
			sql.append(" ");
			sql.append(WHERE);
			sql.append(" ");
			sql.append(where.buildSQL());
		}
		
		return sql.toString();
	}

}