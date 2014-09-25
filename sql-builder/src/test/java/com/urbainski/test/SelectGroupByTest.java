package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SQLBuilder;

/**
 * Classe de teste para queries com groupby.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 24/09/2014
 * @version 1.0
 *
 */
public class SelectGroupByTest {

	@Test
	public void testeGroupBy1() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, ")
		.append("livro.nr_anopublicacao, livro.autor_id from livro ")
		.append("group by livro.id, livro.ds_nome, livro.nr_anopublicacao")
		.toString();
	
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.addFieldInGroupBy("id");
		sqlBuilder.addFieldInGroupBy("nome");
		sqlBuilder.addFieldInGroupBy("anoPublicacao");
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testeGroupByAndOrderBy() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, ")
		.append("livro.nr_anopublicacao, livro.autor_id from livro ")
		.append("group by livro.id, livro.ds_nome, livro.nr_anopublicacao ")
		.append("order by livro.id asc")
		.toString();
	
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.addFieldInOrderBy("id");
		sqlBuilder.addFieldInGroupBy("id");
		sqlBuilder.addFieldInGroupBy("nome");
		sqlBuilder.addFieldInGroupBy("anoPublicacao");
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
}
