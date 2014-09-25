package com.urbainski.test;


import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
import com.urbainski.entidade.Endereco;
import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SQLBuilder;
import com.urbainski.sql.builder.db.types.JoinDBType;

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
	
	@Test
	public void testLeftJoinComAliasnoFromEnoJoin() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, ")
		.append("l0.nr_anopublicacao from livro as l0 ")
		.append("left join autor a0 on ")
		.append("l0.autor_id = a0.id")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.fromAlias("l0");
		builder.addJoin(Autor.class, "a0", "autor", JoinDBType.LEFT);
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeJoinDoJoinTypeInner() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, ")
		.append("l0.nr_anopublicacao from livro as l0 ")
		.append("inner join autor a0 on ")
		.append("l0.autor_id = a0.id ")
		.append("inner join endereco e0 on ")
		.append("a0.endereco_id = e0.id")
		.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.addJoin(Autor.class, "a0", "autor");
		sqlBuilder.addJoin(Autor.class, Endereco.class, "a0", "e0", "endereco");
		sqlBuilder.fromAlias("l0");
		
		String sqlGerado = sqlBuilder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeJoinDoJoinTypeLeft() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, ")
		.append("l0.nr_anopublicacao from livro as l0 ")
		.append("left join autor a0 on ")
		.append("l0.autor_id = a0.id ")
		.append("left join endereco e0 on ")
		.append("a0.endereco_id = e0.id")
		.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.addJoin(Autor.class, "a0", "autor", JoinDBType.LEFT);
		sqlBuilder.addJoin(
				Autor.class, Endereco.class, "a0", "e0", "endereco", JoinDBType.LEFT);
		sqlBuilder.fromAlias("l0");
		
		String sqlGerado = sqlBuilder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
}