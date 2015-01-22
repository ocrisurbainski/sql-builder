package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SelectBuilder;
import com.urbainski.sql.db.types.AggregateDBTypes;

/**
 * Classe de teste unitário para as funções de agregação.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 28/09/2014
 * @version 1.0
 *
 */
public class SelectAggregateTest {

	/**
	 * Teste unitário para a função de agregação 'sum'.
	 */
	@Test
	public void testSum() {
		final String sqlCerto = new StringBuilder()
			.append("select count(livro.id), autor.ds_nome from livro ")
			.append("inner join autor on livro.autor_id = autor.id ")
			.append("group by autor.ds_nome")
			.toString();
		
		SelectBuilder sqlBuilder = new SelectBuilder(Livro.class);
		sqlBuilder.select().addField("id", AggregateDBTypes.COUNT);
		sqlBuilder.select().addField(Autor.class, "nome");
		sqlBuilder.addJoin(Autor.class, "autor");
		sqlBuilder.groupBy().addField(Autor.class, "nome");
		
		String sqlGerado = sqlBuilder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	/**
	 * Teste unitário para a função de agregação 'sum' com o uso de alias.
	 */
	@Test
	public void testSumComAlias() {
		final String sqlCerto = new StringBuilder()
			.append("select count(l0.id) as quantidade, a0.ds_nome from livro as l0 ")
			.append("inner join autor a0 on l0.autor_id = a0.id ")
			.append("group by a0.ds_nome")
			.toString();
		
		SelectBuilder sqlBuilder = new SelectBuilder(Livro.class);
		sqlBuilder.select().addField("id", "quantidade", AggregateDBTypes.COUNT);
		sqlBuilder.select().addField(Autor.class, "a0", "nome", "");
		sqlBuilder.addJoin(Autor.class, "a0", "autor");
		sqlBuilder.groupBy().addField(Autor.class, "a0", "nome");
		sqlBuilder.fromAlias("l0");
		
		String sqlGerado = sqlBuilder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
}
