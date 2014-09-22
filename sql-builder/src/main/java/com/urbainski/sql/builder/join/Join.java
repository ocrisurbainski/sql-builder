package com.urbainski.sql.builder.join;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.urbainski.sql.builder.Builder;
import com.urbainski.sql.builder.condititon.Condition;
import com.urbainski.sql.builder.condititon.ConditionDBTypes;
import com.urbainski.sql.builder.reflection.TableReflectionReader;

/**
 * Classe que representa o join nas queries.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
public class Join<T, F> implements Builder {

	/**
	 * Lista de condições do join.
	 */
	protected List<Condition> conditions;
	
	/**
	 * Clausula on da join.
	 */
	protected Condition on;
	
	/**
	 * Tipo do join.
	 */
	protected JoinType joinType;
	
	/**
	 * De onde o join esta sendo feito.
	 */
	protected Class<?> clazzDe;
	
	/**
	 * Com quem o join esta sendo unido.
	 */
	protected Class<?> clazzPara;
	
	/**
	 * Nome da propriedade.
	 */
	protected String property;
	
	/**
	 * Construtor padrão da classe.
	 */
	public Join(String property) {
		this(property, JoinType.INNER);
	}
	
	/**
	 * Construtor da classe.
	 * 
	 * @param joinType - tipo do join
	 * @param property - nome da propriedade
	 */
	public Join(String property, JoinType joinType) {
		this.joinType = joinType;
		this.conditions = new ArrayList<Condition>();
		
		final ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.clazzDe = (Class<?>) genericSuperclass.getActualTypeArguments()[0];
		this.clazzPara = (Class<?>) genericSuperclass.getActualTypeArguments()[1];
	}
	
	public void on(Condition condition) {
		this.on = condition;
	}
	
	public void addCondition(Condition condition) {
		this.conditions.add(condition);
	}
	
	public void addCondition(Condition... condition) {
		this.conditions.addAll(Arrays.asList(condition));
	}
	
	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(joinType.getJoinName());
		sql.append(" ");
		sql.append(TableReflectionReader.getTableName(clazzPara));
		sql.append(" on ");
		
		if (on != null) {
			sql.append(on.buildSQL());
		} else {
			TableReflectionReader.getJoinInformation(clazzDe, clazzPara);
		}
		
		if (!conditions.isEmpty()) {
			for (Condition c : conditions) {
				sql.append(ConditionDBTypes.AND.getConditionType());
				sql.append(" ");
				sql.append(c.buildSQL());
			}
		}
		
		return sql.toString();
	}

}
