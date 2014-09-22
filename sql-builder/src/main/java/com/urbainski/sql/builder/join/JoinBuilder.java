package com.urbainski.sql.builder.join;

import com.urbainski.sql.builder.db.types.JoinType;

/**
 * Classe para gerar os join do queries.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
public class JoinBuilder<X, Y> {

	public Join<X, Y> newJoin(String property, JoinType joinType) {
		return new Join<X, Y>(property, joinType);
	}
	
}