package com.urbainski.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.urbainski.sql.builder.SelectBuilder;
import com.urbainski.sql.db.types.AggregateDBTypes;
import com.urbainski.test.app.dao.generic.GenericDAO;
import com.urbainski.test.app.dao.generic.impl.GenericDAOImpl;
import com.urbainski.test.app.dto.DtoMidia;
import com.urbainski.test.app.entidade.Midia;
import com.urbainski.test.app.entidade.Tipomidia;

/**
 * DAO da entidade midai.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 02/10/2014
 * @version 1.0
 *
 */
public class MidiaDAO extends GenericDAOImpl<Integer, Midia> 
	implements GenericDAO<Integer, Midia> {

	@SuppressWarnings("unchecked")
	public List<DtoMidia> countMidiasPorTipo() {
		SelectBuilder sqlBuilder = new SelectBuilder(Midia.class);
		sqlBuilder.select().addField("idMidia", "quantidade", AggregateDBTypes.COUNT);
		sqlBuilder.select().addField(Tipomidia.class, "tm", "dsTipomidia", "tipomidia");
		sqlBuilder.addJoin(Tipomidia.class, "tm", "tipomidia");
		sqlBuilder.groupBy().addField(Tipomidia.class, "tm", "dsTipomidia");
		
		Query q = entityManager.createNativeQuery(sqlBuilder.buildSQL());
		List<Object[]> list = q.getResultList();
		
		List<DtoMidia> listRet = new ArrayList<DtoMidia>();
		for (Object[] object : list) {
			DtoMidia dto = new DtoMidia();
			dto.setQuantidade((Integer) object[0]);
			dto.setTipomidia((String) object[1]);
			
			listRet.add(dto);
		}
		return listRet;
	}
	
}
	