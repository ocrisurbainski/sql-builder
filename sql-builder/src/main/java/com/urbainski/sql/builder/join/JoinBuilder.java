package com.urbainski.sql.builder.join;

import com.urbainski.sql.builder.db.types.JoinDBType;

/**
 * Classe para gerar os join do queries.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
public class JoinBuilder {

	public static Join newJoin(Class<?> clazzFrom, Class<?> clazzJoined,
			String fromAlias, String joinedAlias, String property, JoinDBType joinType) {
		return new Join(clazzFrom, clazzJoined, fromAlias, joinedAlias, property, joinType);
	}
	
	public static Join newJoin(Class<?> clazzFrom, Class<?> clazzJoined, String property) {
		return new Join(clazzFrom, clazzJoined, property);
	}
	
}