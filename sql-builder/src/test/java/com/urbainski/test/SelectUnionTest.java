package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SelectBuilder;
import com.urbainski.sql.db.types.UnionDBTypes;

/**
 * Classe de teste para queries com unions.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 24/09/2014
 * @version 1.0
 /*
 */
public class SelectUnionTest {

	@Test
	public void testeUnion() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id as id, l0.ds_nome as nome ")
		.append("from livro as l0 union ")
		.append("select a0.id as id, a0.ds_nome as nome ")
		.append("from autor as a0")
		.toString();
		
		SelectBuilder sqlBuilderAutor = new SelectBuilder(Autor.class);
		sqlBuilderAutor.select().addField("id", "id");
		sqlBuilderAutor.select().addField("nome", "nome");
		sqlBuilderAutor.fromAlias("a0");
		
		SelectBuilder sqlBuilderLivro = new SelectBuilder(Livro.class);
		sqlBuilderLivro.select().addField("id", "id");
		sqlBuilderLivro.select().addField("nome", "nome");
		sqlBuilderLivro.union(sqlBuilderAutor, UnionDBTypes.UNION);
		sqlBuilderLivro.fromAlias("l0");
		
		String sqlGerado = sqlBuilderLivro.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeUnionAll() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id as id, l0.ds_nome as nome ")
		.append("from livro as l0 union all ")
		.append("select a0.id as id, a0.ds_nome as nome ")
		.append("from autor as a0")
		.toString();
		
		SelectBuilder sqlBuilderAutor = new SelectBuilder(Autor.class);
		sqlBuilderAutor.select().addField("id", "id");
		sqlBuilderAutor.select().addField("nome", "nome");
		sqlBuilderAutor.fromAlias("a0");
		
		SelectBuilder sqlBuilderLivro = new SelectBuilder(Livro.class);
		sqlBuilderLivro.select().addField("id", "id");
		sqlBuilderLivro.select().addField("nome", "nome");
		sqlBuilderLivro.union(sqlBuilderAutor, UnionDBTypes.UNION_ALL);
		sqlBuilderLivro.fromAlias("l0");
		
		String sqlGerado = sqlBuilderLivro.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
}
