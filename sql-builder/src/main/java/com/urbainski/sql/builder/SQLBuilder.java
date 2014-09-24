package com.urbainski.sql.builder;

import static com.urbainski.sql.builder.reflection.TableReflectionReader.getDatabaseNameField;
import static com.urbainski.sql.builder.reflection.TableReflectionReader.getTableName;
import static com.urbainski.sql.util.SQLUtils.AS;
import static com.urbainski.sql.util.SQLUtils.DISTINCT;
import static com.urbainski.sql.util.SQLUtils.FROM;
import static com.urbainski.sql.util.SQLUtils.SELECT;
import static com.urbainski.sql.util.SQLUtils.WHERE;

import java.util.ArrayList;
import java.util.List;

import com.urbainski.sql.builder.condititon.Condition;
import com.urbainski.sql.builder.condititon.impl.ConditionBuilder;
import com.urbainski.sql.builder.db.types.ConditionDBTypes;
import com.urbainski.sql.builder.db.types.ConstainsDBTypes;
import com.urbainski.sql.builder.db.types.JoinDBType;
import com.urbainski.sql.builder.db.types.OrderByDBTypes;
import com.urbainski.sql.builder.field.FieldBuilder;
import com.urbainski.sql.builder.join.Join;
import com.urbainski.sql.builder.join.JoinBuilder;
import com.urbainski.sql.builder.orderby.OrderBy;
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
	 * Objeto que representa a ordenação dos resultados da query.
	 */
	protected OrderBy orderBy;
	
	/**
	 * Alias do from da query.
	 */
	protected String fromAlias;
	
	/**
	 * Condição where da minha query.
	 */
	protected Condition where;
	
	/**
	 * Lista de joins.
	 */
	protected List<Join> joins;
	
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
		this.joins = new ArrayList<Join>();
		this.distinct = false;
	}
	
	/**
	 * Retorna a intância de {@link Select}.
	 * 
	 * @return {@link Select}
	 */
	public Select select() {
		return select;
	}
	
	/**
	 * Método para setar o alias do from da consulta.
	 * 
	 * @param fromAlias - alias do from
	 */
	public void fromAlias(String fromAlias) {
		this.fromAlias = fromAlias;
		this.select.alias(fromAlias);
		ConditionBuilder.updateAliasForCondition(this.where, entityClass, fromAlias);
	}
	
	/**
	 * Método para setar o where da query.
	 * 
	 * @param type - tipo da condição
	 * @param fieldName - nome do campo 
	 * @param value - valor
	 */
	public void where(ConditionDBTypes type, String fieldName, Object... value) {
		this.where = ConditionBuilder.newCondition(
				this.entityClass, this.fromAlias, type, fieldName, value);
	}
	
	/**
	 * Método para setar o where da query.
	 * 
	 * @param containsType - tipo do contains
	 * @param type - tipo da condição
	 * @param fieldName - nome do campo
	 * @param value - valor
	 */
	public void where(ConstainsDBTypes containsType, ConditionDBTypes type, String fieldName, Object value) {
		this.where = ConditionBuilder.newCondition(
				this.entityClass, this.fromAlias, containsType, type, fieldName, value);
	}
	
	/**
	 * Método para setar o where da query.
	 * 
	 * @param entityClass - entidade
	 * @param containsType - tipo do contains 
	 * @param type - tipo da condição
	 * @param fieldName - nome do campo
	 * @param value - valor
	 */
	public void where(Class<?> entityClass, ConstainsDBTypes containsType, 
			ConditionDBTypes type, String fieldName, Object value) {
		this.where = ConditionBuilder.newCondition(
				entityClass, this.fromAlias, containsType, type, fieldName, value);
	}
	
	/**
	 * Método para setar o where da query.
	 * 
	 * @param type - tipo da condição
	 * @param entiyClass - entidade
	 * @param fieldName - nome do campo
	 * @param value - valor
	 */
	public void where(ConditionDBTypes type, Class<?> entiyClass, 
			String fieldName, Object... value) {
		this.where = ConditionBuilder.newCondition(
				entiyClass, this.fromAlias, type, fieldName, value);
	}
	
	/**
	 * Método para setar o where da query.
	 * 
	 * @param condition - condição
	 */
	public void where(Condition condition) {
		this.where = condition;
	}
	
	/**
	 * Método para adicionar um join na consulta.
	 * 
	 * @param join - join
	 */
	public void addJoin(Join join) {
		this.joins.add(join);
	}
	
	/**
	 * Método para adicionar um join na consulta.
	 * 
	 * @param clazzFrom - classe base
	 * @param clazzJoined - classe que será unido
	 * @param property - propriedade para fazer o join
	 */
	public void addJoin(Class<?> clazzFrom, Class<?> clazzJoined, String property) {
		this.joins.add(JoinBuilder.newJoin(clazzFrom, clazzJoined, property));
	}
	
	/**
	 * Método para adicionar um join na consulta.
	 * 
	 * @param clazzFrom - classe base
	 * @param clazzJoined - classe que será unido
	 * @param joinedAlias - alias da classe do join
	 * @param joinType - tipo do join
	 * @param property - propriedade para fazer o join
	 */
	public void addJoin(Class<?> clazzFrom, Class<?> clazzJoined,
			String joinedAlias, String property, JoinDBType joinType) {
		this.joins.add(JoinBuilder.newJoin(
				clazzFrom, clazzJoined, fromAlias, joinedAlias, property, joinType));
	}
	
	/**
	 * Setar se a consulta é do tipo distinct.
	 * 
	 * @param distinct - distinct na consulta
	 */
	public void distinct(boolean distinct) {
		this.distinct = distinct;
	}
	
	/**
	 * Método responsável por pegar o {@link OrderBy} da query.
	 * 
	 * @param orderByType - tipo do {@link OrderBy}
	 * 
	 * @return {@link OrderBy}
	 */
	public OrderBy orderBy(OrderByDBTypes orderByType) {
		if (this.orderBy == null) {
			this.orderBy = new OrderBy(orderByType);
		} else {
			this.orderBy.setOrderByType(orderByType);
		}
		return this.orderBy;
	}
	
	/**
	 * Método responsável por pegar o {@link OrderBy} da query.
	 * 
	 * @return {@link OrderBy}
	 */
	public OrderBy orderBy() {
		if (this.orderBy == null) {
			this.orderBy = new OrderBy(OrderByDBTypes.ASC);
		}
		return this.orderBy;
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param fieldName - nome do campo
	 */
	public void addFieldInOrderBy(String fieldName) {
		String tableOrAlias = getTableName(entityClass);
		if (fromAlias != null && !(fromAlias.isEmpty())) {
			tableOrAlias = fromAlias;
		}
		
		this.orderBy.addField(FieldBuilder.newField(
				tableOrAlias, getDatabaseNameField(entityClass, fieldName), ""));
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
		
		if (fromAlias != null && !(fromAlias.isEmpty())) {
			sql.append(" " + AS + " " + fromAlias);
		}
		
		if (!joins.isEmpty()) {
			for (Join j : joins) {
				sql.append(j.buildSQL());
			}
		}
		
		if (where != null) {
			sql.append(" ");
			sql.append(WHERE);
			sql.append(" ");
			sql.append(where.buildSQL());
		}
		
		if (orderBy != null) {
			sql.append(" ");
			sql.append(orderBy.buildSQL());
		}
		
		return sql.toString();
	}

}