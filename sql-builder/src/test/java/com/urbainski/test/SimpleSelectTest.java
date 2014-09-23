package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SQLBuilder;

/**
 * Classe de teste unitário para querys básicas.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public class SimpleSelectTest {
	
	@Test
	public void testSimpleSQLSemAlias() {
		final String sqlCerto = new StringBuilder()
			.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao from livro")
			.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testSimpleDistinctSQLSemAlias() {
		final String sqlCerto = new StringBuilder()
		.append("select distinct livro.id, livro.ds_nome, livro.nr_anopublicacao from livro")
			.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.distinct(true);

		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}

	@Test
	public void testSimpleSQLComAlias() {
		final String sqlCerto = new StringBuilder()
			.append("select livro.id as identificadorLivro, livro.ds_nome as nomeLivro, ")
			.append("livro.nr_anopublicacao as anoLivro from livro")
			.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.select().addField("id", 				"identificadorLivro");
		sqlBuilder.select().addField("nome", 			"nomeLivro");
		sqlBuilder.select().addField("anoPublicacao", 	"anoLivro");
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testSimpleDistinctSQLComAlias() {
		final String sqlCerto = new StringBuilder()
			.append("select distinct livro.id as identificadorLivro, ")
			.append("livro.ds_nome as nomeLivro, ")
			.append("livro.nr_anopublicacao as anoLivro from livro")
			.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.distinct(true);
		sqlBuilder.select().addField("id", 				"identificadorLivro");
		sqlBuilder.select().addField("nome", 			"nomeLivro");
		sqlBuilder.select().addField("anoPublicacao", 	"anoLivro");
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
}