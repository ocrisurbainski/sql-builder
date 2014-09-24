package com.urbainski.test;


import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SQLBuilder;

public class SelectJoinTest {

	@Test
	public void testSimpleJoin() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, ")
		.append("livro.nr_anopublicacao from livro ")
		.append("inner join autor on ")
		.append("livro.autor_id = autor.id")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.addJoin(Autor.class, "autor");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testJoinComAliasnoFrom() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, ")
		.append("l0.nr_anopublicacao from livro as l0 ")
		.append("inner join autor on ")
		.append("l0.autor_id = autor.id")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.fromAlias("l0");
		builder.addJoin(Autor.class, "autor");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testJoinComAliasnoFromEnoJoin() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, ")
		.append("l0.nr_anopublicacao from livro as l0 ")
		.append("inner join autor a0 on ")
		.append("l0.autor_id = a0.id")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.fromAlias("l0");
		builder.addJoin(Autor.class, "a0", "autor");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
}