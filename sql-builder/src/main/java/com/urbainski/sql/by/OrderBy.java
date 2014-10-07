package com.urbainski.sql.by;

import static com.urbainski.sql.db.types.SQLSelectDBTypes.ORDER_BY;

import com.urbainski.sql.db.types.OrderByDBTypes;

/**
 * Classe que representa o orderby da query.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 23/09/2014
 * @version 1.0
 *
 */
public class OrderBy extends AbstractClauseBy {
	
	/**
	 * Tipo do order by.
	 */
	protected OrderByDBTypes orderByType;
	
	public void setOrderByType(OrderByDBTypes orderByType) {
		this.orderByType = orderByType;
	}
	
	/**
	 * Construtor padrão.
	 * 
	 * @param entityClass - classe de entidade
	 * @param fromAlias - alias do from
	 * @param type - tipo do order by
	 */
	public OrderBy(Class<?> entityClass, String fromAlias, OrderByDBTypes type) {
		super(entityClass, fromAlias);
		this.orderByType = type;
	}
	
	@Override
	public String buildSQL() {
		final StringBuilder sql = new StringBuilder();
		sql.append(super.buildSQL());
		sql.append(" ");
		sql.append(orderByType.getOrderByType());
		return sql.toString();
	}

	@Override
	public String getType() {
		return ORDER_BY.getSQLSelectType();
	}

}