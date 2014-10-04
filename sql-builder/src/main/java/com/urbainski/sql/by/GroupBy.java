package com.urbainski.sql.by;

import static com.urbainski.sql.db.types.SQLSelectDBTypes.GROUP_BY;

/**
 * Classe que representa o group by da query.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 24/09/2014
 * @version 1.0
 *
 */
public class GroupBy extends AbstractClauseBy {
	
	/**
	 * Construtor padrão.
	 * 
	 * @param entityClass - classe de entidade
	 * @param fromAlias - alias do from
	 */
	public GroupBy(Class<?> entityClass, String fromAlias) {
		super(entityClass, fromAlias);
	}

	@Override
	public String getType() {
		return GROUP_BY.getSQLSelectType();
	}
	
}