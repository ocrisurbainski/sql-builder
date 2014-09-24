package com.urbainski.sql.builder.join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.urbainski.sql.builder.Builder;
import com.urbainski.sql.builder.condititon.Condition;
import com.urbainski.sql.builder.condititon.impl.ConditionBuilder;
import com.urbainski.sql.builder.condititon.impl.JoinCondition;
import com.urbainski.sql.builder.db.types.ConditionDBTypes;
import com.urbainski.sql.builder.db.types.JoinDBType;
import com.urbainski.sql.builder.reflection.TableReflectionReader;

/**
 * Classe que representa o join nas queries.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
public class Join implements Builder {

	/**
	 * Lista de condições do join.
	 */
	protected List<Condition> conditions;
	
	/**
	 * Tipo do join.
	 */
	protected JoinDBType joinType;
	
	/**
	 * De onde o join esta sendo feito.
	 */
	protected Class<?> clazzFrom;
	
	/**
	 * Alias da classe do from.
	 */
	protected String fromAlias;
	
	/**
	 * Alias da classe que foi feito o join.
	 */
	protected String joinedAlias;
	
	/**
	 * Com quem o join esta sendo unido.
	 */
	protected Class<?> clazzJoined;
	
	/**
	 * Nome da propriedade.
	 */
	protected String property;
	
	/**
	 * Construtor da classe.
	 * 
	 * @param clazzDe
	 * @param clazzPara
	 * @param property - nome da propriedade
	 */
	public Join(Class<?> clazzFrom, Class<?> clazzJoined, 
			String fromAlias, String joinedAlias, String property) {
		this(clazzFrom, clazzJoined, fromAlias, joinedAlias, property, JoinDBType.INNER);
	}
	
	/**
	 * Construtor da classe.
	 * 
	 * @param clazzFrom
	 * @param clazzJoined
	 * @param fromAlias
	 * @param joinedAlias
	 * @param joinType - tipo do join
	 * @param property - nome da propriedade
	 */
	public Join(Class<?> clazzFrom, Class<?> clazzJoined, 
			String fromAlias, String joinedAlias, String property, JoinDBType joinType) {
		this.clazzFrom = clazzFrom;
		this.clazzJoined = clazzJoined;
		this.joinType = joinType;
		this.conditions = new ArrayList<Condition>();
	}
	
	/**
	 * Método para adicionar uma nova condição.
	 * 
	 * @param condition - condição
	 */
	public void addCondition(Condition condition) {
		this.conditions.add(condition);
	}
	
	/**
	 * Método para adicionar uma série de condições.
	 * 
	 * @param condition - condições 
	 */
	public void addCondition(Condition... condition) {
		this.conditions.addAll(Arrays.asList(condition));
	}
	
	/**
	 * Método para criar um condição 'on' no join.
	 * 
	 * @param conditionType - tipo da condição
	 * @param prop1 - nome da propriedade 1 
	 * @param prop2 - nome da propriedade 2 
	 */
	public void addOn(ConditionDBTypes conditionType, String prop1, String prop2) {
		final JoinCondition joinCondition = ConditionBuilder.newJoinCondition(
				clazzFrom, fromAlias, clazzJoined, joinedAlias, conditionType, prop1, prop2);
		
		final List<Condition> newConditions = new ArrayList<Condition>();
		for (Condition c : this.conditions) {
			boolean jaAdicionouNovaCondicao = false;
			if (c instanceof JoinCondition) {
				newConditions.add(c);
			} else {
				if (jaAdicionouNovaCondicao) {
					jaAdicionouNovaCondicao = true;
					
					newConditions.add(joinCondition);
				}
				newConditions.add(c);
			}
		}
	}
	
	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(joinType.getJoinName());
		sql.append(" ");
		sql.append(TableReflectionReader.getTableName(clazzJoined));
		sql.append(" on ");
		
		if (conditions.isEmpty()) {
			TableReflectionReader.getJoinInformation(clazzFrom, clazzJoined);
		} else {
			for (Condition c : conditions) {
				if (conditions.indexOf(c) > 0) {
					sql.append(ConditionDBTypes.AND.getConditionType());
					sql.append(" ");
				}

				sql.append(c.buildSQL());
			}
		}
		
		return sql.toString();
	}

}
