package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SQLBuilder;

/**
 * Classe de teste unitário para querys básicas.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public class SimpleSelectTest {
	
	@Test
	public void testSimpleSQLSemAlias() {
		final String sqlCerto = new StringBuilder()
			.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id from livro")
			.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testSimpleDistinctSQLSemAlias() {
		final String sqlCerto = new StringBuilder()
		.append("select distinct livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id from livro")
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
	
	@Test
	public void testeFromAlias() {
		final String sqlCerto = new StringBuilder()
		.append("select l1.id, l1.ds_nome, ")
		.append("l1.nr_anopublicacao, l1.autor_id from livro as l1")
		.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.fromAlias("l1");
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testFromAliasECamposAlias() {
		final String sqlCerto = new StringBuilder()
			.append("select l.id as identificadorLivro, ")
			.append("l.ds_nome as nomeLivro, ")
			.append("l.nr_anopublicacao as anoLivro from livro as l")
			.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.select().addField("id", 				"identificadorLivro");
		sqlBuilder.select().addField("nome", 			"nomeLivro");
		sqlBuilder.select().addField("anoPublicacao", 	"anoLivro");
		sqlBuilder.fromAlias("l");
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
}