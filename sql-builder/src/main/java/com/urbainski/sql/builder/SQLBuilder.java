package com.urbainski.sql.builder;

import static com.urbainski.sql.reflection.TableReflectionReader.getTableName;
import static com.urbainski.sql.db.types.SQLSelectDBTypes.AS;
import static com.urbainski.sql.db.types.SQLSelectDBTypes.DISTINCT;
import static com.urbainski.sql.db.types.SQLSelectDBTypes.FROM;
import static com.urbainski.sql.db.types.SQLSelectDBTypes.SELECT;
import static com.urbainski.sql.db.types.SQLSelectDBTypes.WHERE;

import java.util.ArrayList;
import java.util.List;

import com.urbainski.sql.condititon.Condition;
import com.urbainski.sql.condititon.impl.ConditionBuilder;
import com.urbainski.sql.db.types.ConditionDBTypes;
import com.urbainski.sql.db.types.ConstainsDBTypes;
import com.urbainski.sql.db.types.JoinDBType;
import com.urbainski.sql.db.types.OrderByDBTypes;
import com.urbainski.sql.db.types.UnionDBTypes;
import com.urbainski.sql.field.impl.FieldBuilder;
import com.urbainski.sql.groupby.GroupBy;
import com.urbainski.sql.join.Join;
import com.urbainski.sql.join.JoinBuilder;
import com.urbainski.sql.orderby.OrderBy;
import com.urbainski.sql.select.Select;

/**
 * Classe que representa um sql para consulta no banco de dados.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public class SQLBuilder implements SQL {

	/**
	 * Objeto que representa os campos do select.
	 */
	protected Select select;
	
	/**
	 * Objeto que representa a ordenação dos resultados da query.
	 */
	protected OrderBy orderBy;
	
	/**
	 * Objeto que representa o group by da query.
	 */
	protected GroupBy groupBy;
	
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
	 * Objeto que corresponde ao union.
	 */
	protected SQLBuilder union;
	
	/**
	 * Tipo do union.
	 */
	protected UnionDBTypes unionType;
	
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
		
		for (Join j : joins) {
			if (j.getClazzFrom().equals(entityClass)) {
				j.fromAlias(fromAlias);
			}
		}
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
		
		where(containsType, type, this.entityClass, this.fromAlias, fieldName, value);
	}
	
	/**
	 * Método para setar o where da query.
	 * 
	 * @param containsType - tipo do contains
	 * @param type - tipo da condição
	 * @param entityClass - classe de entidade
	 * @param fieldName - nome do campo
	 * @param value - valor
	 */
	public void where(ConstainsDBTypes containsType, ConditionDBTypes type, 
			Class<?> entityClass, String fieldName, Object value) {
		
		where(containsType, type, entityClass, "", fieldName, value);
	}
	
	/**
	 * Método para setar o where da query.
	 * 
	 * @param containsType - tipo do contains
	 * @param type - tipo da condição
	 * @param entityClass - classe de entidade
	 * @param entityAlias - alias para a entidade
	 * @param fieldName - nome do campo
	 * @param value - valor
	 */
	public void where(ConstainsDBTypes containsType, ConditionDBTypes type, 
			Class<?> entityClass, String entityAlias,
			String fieldName, Object value) {
		
		this.where = ConditionBuilder.newCondition(
				entityClass, entityAlias, containsType, type, fieldName, value);
	}
	
	/**
	 * Método para setar o where da query.
	 * 
	 * @param type - tipo da condição
	 * @param fieldName - nome do campo 
	 * @param value - valor
	 */
	public void where(ConditionDBTypes type, String fieldName, Object... value) {
		where(type, this.entityClass, this.fromAlias, fieldName, value);
	}
	
	/**
	 * Método para setar o where da query.
	 * 
	 * @param type - tipo da condição
	 * @param entiyClass - entidade
	 * @param entityAlias - alias da entidade
	 * @param fieldName - nome do campo
	 * @param value - valor
	 */
	public void where(ConditionDBTypes type, Class<?> entiyClass, 
			String entityAlias, String fieldName, Object... value) {
		
		this.where = ConditionBuilder.newCondition(
				entiyClass, entityAlias, type, fieldName, value);
	}
	
	/**
	 * Método que cria um condição para o where.
	 * 
	 * @param fieldName - nome do campo
	 * @param conditionType - tipo da condição
	 * @param subselect - query do subselect
	 */
	public void where(ConditionDBTypes conditionType, 
			String fieldName, SQLBuilder subselect) {
		
		where(conditionType, entityClass, fromAlias, fieldName, subselect);
	}
	
	/**
	 * Método que cria um condição para o where.
	 * 
	 * @param entityClass - classe de entidade
	 * @param aliasTable - alias da tabela
	 * @param fieldName - nome do campo
	 * @param conditionType - tipo da condição
	 * @param subselect - query do subselect
	 */
	public void where(ConditionDBTypes conditionType, Class<?> entityClass, 
			String aliasTable,String fieldName, SQLBuilder subselect) {
		
		this.where = ConditionBuilder.newSubselectCondition(
				entityClass, aliasTable, fieldName, conditionType, subselect);
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
	 * @param clazzJoined - classe que será unido
	 * @param property - propriedade para fazer o join
	 */
	public Join addJoin(Class<?> clazzJoined, String property) {
		Join join = JoinBuilder.newJoin(entityClass, clazzJoined, fromAlias, "", property);
		this.joins.add(join);
		return join;
	}
	
	/**
	 * Método para adicionar um join na consulta.
	 * 
	 * @param clazzJoined - classe que será unido
	 * @param property - propriedade para fazer o join
	 * @param joinType - tipo do join
	 */
	public Join addJoin(Class<?> clazzJoined, String property, JoinDBType joinType) {
		String tableJoinedAlias = getTableName(clazzJoined);
		Join join = JoinBuilder.newJoin(entityClass, clazzJoined, fromAlias, tableJoinedAlias, property, joinType);
		this.joins.add(join);
		return join;
	}
	
	/**
	 * Método para adicionar um join na consulta.
	 * 
	 * @param clazzJoined - classe que será unido
	 * @param joinedAlias - alias do join
	 * @param property - propriedade para fazer o join
	 * @param joinType - tipo do join
	 */
	public Join addJoin(Class<?> clazzJoined, String joinedAlias, String property, JoinDBType joinType) {
		Join join = JoinBuilder.newJoin(entityClass, clazzJoined, fromAlias, joinedAlias, property, joinType);
		this.joins.add(join);
		return join;
	}
	
	/**
	 * Método para adicionar um join na consulta.
	 * 
	 * @param clazzJoined - classe que será unido
	 * @param joinedAlias - alias do join
	 * @param property - propriedade para fazer o join
	 */
	public Join addJoin(Class<?> clazzJoined, String joinedAlias, String property) {
		Join join = JoinBuilder.newJoin(entityClass, clazzJoined, fromAlias, joinedAlias, property);
		this.joins.add(join);
		return join;
	}
	
	/**
	 * Método para adicionar um join na consulta.
	 * 
	 * @param clazzFrom - classe base
	 * @param clazzJoined - classe que será unido
	 * @param property - propriedade para fazer o join
	 */
	public Join addJoin(Class<?> clazzFrom, Class<?> clazzJoined, String property) {
		String fromTableAlias = getTableName(clazzJoined);
		String tableJoinedAlias = getTableName(clazzJoined);
		Join join = JoinBuilder.newJoin(clazzFrom, clazzJoined, fromTableAlias, tableJoinedAlias, property);
		this.joins.add(join);
		return join;
	}
	
	/**
	 * Método para adicionar um join na consulta.
	 * 
	 * @param clazzFrom - classe base
	 * @param clazzJoined - classe que será unido
	 * @param property - propriedade para fazer o join
	 * @param joinType - tipo do join
	 */
	public Join addJoin(Class<?> clazzFrom, Class<?> clazzJoined, String property, JoinDBType joinType) {
		
		String fromTableAlias = getTableName(clazzJoined);
		String tableJoinedAlias = getTableName(clazzJoined);
		Join join = JoinBuilder.newJoin(
				clazzFrom, clazzJoined, fromTableAlias, tableJoinedAlias, property, joinType);
		this.joins.add(join);
		return join;
	}
	
	/**
	 * Método para adicionar um join na consulta.
	 * 
	 * @param clazzFrom - classe base
	 * @param clazzJoined - classe que será unido
	 * @param joinedAlias - alias da classe do join
	 * @param property - propriedade para fazer o join
	 */
	public Join addJoin(Class<?> clazzFrom, 
			Class<?> clazzJoined, String joinedAlias, String property) {
		
		String fromTableAlias = getTableName(clazzJoined);
		Join join = JoinBuilder.newJoin(
				clazzFrom, clazzJoined, fromTableAlias, joinedAlias, property, JoinDBType.INNER);
		this.joins.add(join);
		
		return join;
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
	public Join addJoin(Class<?> clazzFrom, Class<?> clazzJoined,
			String joinedAlias, String property, JoinDBType joinType) {
		String fromTableAlias = getTableName(clazzJoined);
		Join join = JoinBuilder.newJoin(
				clazzFrom, clazzJoined, fromTableAlias, joinedAlias, property, joinType);
		this.joins.add(join);
		
		return join;
	}
	
	/**
	 * Método para adicionar um join na consulta.
	 * 
	 * @param clazzFrom - classe base
	 * @param clazzJoined - classe que será unido
	 * @param fromAlias - alias do from
	 * @param joinedAlias - alias da classe do join
	 * @param property - propriedade para fazer o join
	 */
	public Join addJoin(Class<?> clazzFrom, Class<?> clazzJoined, String fromAlias,
			String joinedAlias, String property) {
		
		Join join = JoinBuilder.newJoin(
				clazzFrom, clazzJoined, fromAlias, joinedAlias, property, JoinDBType.INNER);
		this.joins.add(join);
		
		return join;
	}
	
	/**
	 * Método para adicionar um join na consulta.
	 * 
	 * @param clazzFrom - classe base
	 * @param clazzJoined - classe que será unido
	 * @param fromAlias - alias do from
	 * @param joinedAlias - alias da classe do join
	 * @param joinType - tipo do join
	 * @param property - propriedade para fazer o join
	 */
	public Join addJoin(Class<?> clazzFrom, Class<?> clazzJoined, String fromAlias,
			String joinedAlias, String property, JoinDBType joinType) {
		
		Join join = JoinBuilder.newJoin(
				clazzFrom, clazzJoined, fromAlias, joinedAlias, property, joinType);
		this.joins.add(join);
		
		return join;
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
	
		addFieldInOrderBy(entityClass, tableOrAlias, fieldName);
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param entity - classe de entidade
	 * @param fieldName - nome do campo
	 */
	public void addFieldInOrderBy(Class<?> entity, String fieldName) {
		String tableOrAlias = getTableName(entity);
		addFieldInOrderBy(entity, tableOrAlias, fieldName);
	}
	
	/**
	 * Método para adicionar um campo na consulta apenas pelo nome.
	 * 
	 * @param entity - classe de entidade
	 * @param tableOrAlias - nome da tabela ou alias
	 * @param fieldName - nome do campo
	 */
	public void addFieldInOrderBy(Class<?> entity, String tableOrAlias, String fieldName) {
		// chama o método orderBy para inicializar o objeto caso seja nulo
		this.orderBy();
		this.orderBy.addField(FieldBuilder.newField(
				entity, tableOrAlias, fieldName, "", true));
	}
	
	/**
	 * Método que retorna o {@link GroupBy} da query.
	 * 
	 * @return {@link GroupBy}
	 */
	public GroupBy groupBy() {
		if (groupBy == null) {
			groupBy = new GroupBy();
		}
		return groupBy;
	}
	
	/**
	 * Método para adicionar um campo no group by da query.
	 * 
	 * @param fieldName - nome do campo
	 */
	public void addFieldInGroupBy(String fieldName) {
		String tableOrAlias = getTableName(entityClass);
		if (fromAlias != null && !(fromAlias.isEmpty())) {
			tableOrAlias = fromAlias;
		}
		
		addFieldInGroupBy(entityClass, tableOrAlias, fieldName);
	}
	
	/**
	 * Método para adicionar um campo no group by da query.
	 * 
	 * @param entity - classe de entidade
	 * @param fieldName - nome do campo
	 */
	public void addFieldInGroupBy(Class<?> entity, String fieldName) {
		String tableOrAlias = getTableName(entity);
		addFieldInGroupBy(entityClass, tableOrAlias, fieldName);
	}
	
	/**
	 * Método para adicionar um campo no group by da query.
	 * 
	 * @param entity - entidade do banco de dados
	 * @param tableOrAlias - nome da tabela ou alias
	 * @param fieldName - nome do campo
	 */
	public void addFieldInGroupBy(Class<?> entity, String tableOrAlias, String fieldName) {
		// chama o método groupBy para inicializar o objeto caso seja nulo
		this.groupBy();
		this.groupBy.addField(FieldBuilder.newField(
				entity, tableOrAlias, fieldName, "", true));
	}
	
	/**
	 * Método que recebe um objeto para o union.
	 * 
	 * @param union - objeto union
	 * @param unionType - tipo do union
	 */
	public void union(SQLBuilder union, UnionDBTypes unionType) {
		this.union = union;
		this.unionType = unionType;
	}
	
	/**
	 * Método que recebe um objeto para o union.
	 * 
	 * @param union - objeto union
	 */
	public void union(SQLBuilder union) {
		this.union = union;
		this.unionType = UnionDBTypes.UNION;
	}

	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(SELECT.getSQLSelectType());
		sql.append(" ");
		
		if (distinct) {
			sql.append(DISTINCT.getSQLSelectType());
			sql.append(" ");
		}
		
		boolean readFieldsOfJoins = false;
		if (select.getFields().isEmpty()){
			readFieldsOfJoins = true;
		}
		
		sql.append(select.buildSQL());
		
		if (readFieldsOfJoins) {
			for (Join j : joins) {
				sql.append(", ");
				
				Select selectJoin = j.builSelect();
				sql.append(selectJoin.buildSQL());
			}
		}
		
		sql.append(" ");
		sql.append(FROM.getSQLSelectType());
		sql.append(" ");
		sql.append(getTableName(entityClass));
		
		if (fromAlias != null && !(fromAlias.isEmpty())) {
			sql.append(" " + AS.getSQLSelectType() + " " + fromAlias);
		}
		
		if (!joins.isEmpty()) {
			for (Join j : joins) {
				sql.append(" ");
				sql.append(j.buildSQL());
			}
		}
		
		if (where != null) {
			sql.append(" ");
			sql.append(WHERE.getSQLSelectType());
			sql.append(" ");
			sql.append(where.buildSQL());
		}
		
		if (groupBy != null) {
			sql.append(" ");
			sql.append(groupBy.buildSQL());
		}
		
		if (orderBy != null) {
			sql.append(" ");
			sql.append(orderBy.buildSQL());
		}
		
		if (union != null) {
			sql.append(" ");
			sql.append(unionType.getUnionType());
			sql.append(" ");
			sql.append(union.buildSQL());
		}
		
		return sql.toString();
	}

}