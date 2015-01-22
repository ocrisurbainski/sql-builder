package com.urbainski.test;


import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
import com.urbainski.entidade.Endereco;
import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SelectBuilder;
import com.urbainski.sql.condititon.impl.ConditionBuilder;
import com.urbainski.sql.db.types.ConditionDBTypes;
import com.urbainski.sql.db.types.ConstainsDBTypes;
import com.urbainski.sql.db.types.JoinDBType;
import com.urbainski.sql.join.Join;

public class SelectJoinTest {

	@Test
	public void testSimpleJoin() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, ")
		.append("livro.nr_anopublicacao, livro.autor_id, ")
		.append("autor.id, autor.ds_nome, autor.dt_nascimento, ")
		.append("autor.endereco_id, autor.editora_id from livro ")
		.append("inner join autor on ")
		.append("livro.autor_id = autor.id")
		.toString();
		
		SelectBuilder builder = new SelectBuilder(Livro.class);
		builder.addJoin(Autor.class, "autor");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testJoinComAliasnoFrom() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, ")
		.append("l0.nr_anopublicacao, l0.autor_id, ")
		.append("autor.id, autor.ds_nome, autor.dt_nascimento, ")
		.append("autor.endereco_id, autor.editora_id from livro as l0 ")
		.append("inner join autor on ")
		.append("l0.autor_id = autor.id")
		.toString();
		
		SelectBuilder builder = new SelectBuilder(Livro.class);
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
		.append("l0.nr_anopublicacao, l0.autor_id, ")
		.append("a0.id, a0.ds_nome, a0.dt_nascimento, ")
		.append("a0.endereco_id, a0.editora_id from livro as l0 ")
		.append("inner join autor a0 on ")
		.append("l0.autor_id = a0.id")
		.toString();
		
		SelectBuilder builder = new SelectBuilder(Livro.class);
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
		.append("l0.nr_anopublicacao, l0.autor_id, ")
		.append("a0.id, a0.ds_nome, a0.dt_nascimento, ")
		.append("a0.endereco_id, a0.editora_id from livro as l0 ")
		.append("left join autor a0 on ")
		.append("l0.autor_id = a0.id")
		.toString();
		
		SelectBuilder builder = new SelectBuilder(Livro.class);
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
		.append("l0.nr_anopublicacao, l0.autor_id, ")
		.append("a0.id, a0.ds_nome, a0.dt_nascimento, ")
		.append("a0.endereco_id, a0.editora_id, e0.id, e0.ds_endereco, ")
		.append("e0.nr_numero from livro as l0 ")
		.append("inner join autor a0 on ")
		.append("l0.autor_id = a0.id ")
		.append("inner join endereco e0 on ")
		.append("a0.endereco_id = e0.id")
		.toString();
		
		SelectBuilder sqlBuilder = new SelectBuilder(Livro.class);
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
		.append("l0.nr_anopublicacao, l0.autor_id, ")
		.append("a0.id, a0.ds_nome, a0.dt_nascimento, ")
		.append("a0.endereco_id, a0.editora_id, e0.id, e0.ds_endereco, ")
		.append("e0.nr_numero from livro as l0 ")
		.append("left join autor a0 on ")
		.append("l0.autor_id = a0.id ")
		.append("left join endereco e0 on ")
		.append("a0.endereco_id = e0.id")
		.toString();
		
		SelectBuilder sqlBuilder = new SelectBuilder(Livro.class);
		sqlBuilder.addJoin(Autor.class, "a0", "autor", JoinDBType.LEFT);
		sqlBuilder.addJoin(
				Autor.class, Endereco.class, "a0", "e0", "endereco", JoinDBType.LEFT);
		sqlBuilder.fromAlias("l0");
		
		String sqlGerado = sqlBuilder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testLeftJoinComAliasnoFromEnoJoinECondicaoAMainsNoJoin() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, ")
		.append("l0.nr_anopublicacao, l0.autor_id, ")
		.append("a0.id, a0.ds_nome, a0.dt_nascimento, ")
		.append("a0.endereco_id, a0.editora_id from livro as l0 ")
		.append("left join autor a0 on ")
		.append("l0.autor_id = a0.id and ")
		.append("a0.ds_nome like 'Cristian%'")
		.toString();
		
		SelectBuilder builder = new SelectBuilder(Livro.class);
		builder.fromAlias("l0");
		Join joinAutor = builder.addJoin(Autor.class, "a0", "autor", JoinDBType.LEFT);
		joinAutor.addCondition(ConditionBuilder.newCondition(
				Autor.class, "a0", ConstainsDBTypes.IN_FINISH, ConditionDBTypes.LIKE, 
				"nome", "Cristian"));
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testLeftJoinComAliasnoFromEnoJoinE2CondicaoAMainsNoJoin() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, ")
		.append("l0.nr_anopublicacao, l0.autor_id, ")
		.append("a0.id, a0.ds_nome, a0.dt_nascimento, ")
		.append("a0.endereco_id, a0.editora_id from livro as l0 ")
		.append("left join autor a0 on ")
		.append("l0.autor_id = a0.id and ")
		.append("a0.ds_nome like 'Cristian%' and ")
		.append("a0.ds_nome like '%Urbainski%'")
		.toString();
		
		SelectBuilder builder = new SelectBuilder(Livro.class);
		builder.fromAlias("l0");
		Join joinAutor = builder.addJoin(Autor.class, "a0", "autor", JoinDBType.LEFT);
		joinAutor.addCondition(ConditionBuilder.newCondition(
				Autor.class, "a0", ConstainsDBTypes.IN_FINISH, ConditionDBTypes.LIKE, 
				"nome", "Cristian"));
		joinAutor.addCondition(ConditionBuilder.newCondition(
				Autor.class, "a0", ConstainsDBTypes.ANY, ConditionDBTypes.LIKE, 
				"nome", "Urbainski"));
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
}