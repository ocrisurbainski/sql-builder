package com.urbainski.test.app.dao;

import javax.persistence.Query;

import com.urbainski.sql.builder.SQLBuilder;
import com.urbainski.sql.db.types.ConditionDBTypes;
import com.urbainski.test.app.dao.generic.GenericDAO;
import com.urbainski.test.app.dao.generic.impl.GenericDAOImpl;
import com.urbainski.test.app.entidade.Estado;

/**
 * DAO da entidade estado.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 02/10/2014
 * @version 1.0
 *
 */
public class EstadoDAO extends GenericDAOImpl<Integer, Estado> implements
		GenericDAO<Integer, Estado> {

	public Estado findByUf(String uf) {
		SQLBuilder sqlBuilder = new SQLBuilder(this.entityClass);
		sqlBuilder.where(ConditionDBTypes.EQUALS, "ufEstado", uf);
		
		Query query = entityManager.createNativeQuery(sqlBuilder.buildSQL(), entityClass);
		return (Estado) query.getSingleResult();
	}
	
}
