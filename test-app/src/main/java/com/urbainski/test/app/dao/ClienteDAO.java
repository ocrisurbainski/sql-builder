package com.urbainski.test.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.urbainski.sql.builder.SQLBuilder;
import com.urbainski.sql.db.types.AggregateDBTypes;
import com.urbainski.sql.db.types.OrderByDBTypes;
import com.urbainski.sql.field.Field;
import com.urbainski.test.app.dao.generic.GenericDAO;
import com.urbainski.test.app.dao.generic.impl.GenericDAOImpl;
import com.urbainski.test.app.dto.DtoLocacaoCliente;
import com.urbainski.test.app.entidade.Cliente;
import com.urbainski.test.app.entidade.Locacao;
import com.urbainski.test.app.entidade.Pessoa;

/**
 * DAO da entidade cliente.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 02/10/2014
 * @version 1.0
 *
 */
public class ClienteDAO extends GenericDAOImpl<Integer, Cliente> 
	implements GenericDAO<Integer, Cliente> {

	@SuppressWarnings("unchecked")
	public List<DtoLocacaoCliente> getTopDezClientes() {
		SQLBuilder sqlBuilder = new SQLBuilder(Locacao.class);
		Field fieldSum = sqlBuilder.select().addField("nrTotal", "total", AggregateDBTypes.SUM);
		Field fieldNome = sqlBuilder.select().addField(Pessoa.class, "nmPessoa", "nomeCliente");
		sqlBuilder.addJoin(Cliente.class, "cliente");
		sqlBuilder.addJoin(Cliente.class, Pessoa.class, null);
		sqlBuilder.groupBy().addField(fieldNome);
		sqlBuilder.orderBy(OrderByDBTypes.DESC).addField(fieldSum);
		
		Query q = entityManager.createNativeQuery(sqlBuilder.buildSQL());
		List<Object[]> list = q.getResultList();
		
		List<DtoLocacaoCliente> listRet = new ArrayList<DtoLocacaoCliente>();
		for (Object[] object : list) {
			DtoLocacaoCliente dto = new DtoLocacaoCliente();
			dto.setValor((Double) object[0]);
			dto.setNomeCliente((String) object[1]);
			
			listRet.add(dto);
		}
		
		return listRet;
	}
	
}
