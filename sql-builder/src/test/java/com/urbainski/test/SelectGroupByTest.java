package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
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
	
	@Test
	public void testGroupByFieldAtJoin() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id, ")
		.append("autor.id, autor.ds_nome, autor.dt_nascimento, autor.endereco_id ")
		.append("from livro inner join autor on livro.autor_id = autor.id ")
		.append("group by autor.ds_nome")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.addJoin(Autor.class, "autor");
		builder.addFieldInGroupBy(Autor.class, "nome");
		
		final String sqlGerado = builder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testGroupByFieldAtJoinWithAlias() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, l0.nr_anopublicacao, l0.autor_id, ")
		.append("a0.id, a0.ds_nome, a0.dt_nascimento, a0.endereco_id ")
		.append("from livro as l0 inner join autor a0 on l0.autor_id = a0.id ")
		.append("group by a0.ds_nome")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.addJoin(Autor.class, "a0", "autor");
		builder.addFieldInGroupBy(Autor.class, "a0", "nome");
		builder.fromAlias("l0");
		
		final String sqlGerado = builder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
}