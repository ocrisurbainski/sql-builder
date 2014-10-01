package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
import com.urbainski.sql.builder.SQLBuilder;

/**
 * Classe de teste unitário para as clausulas offset e limit do SQL.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 01/10/2014
 * @version 1.0
 *
 */
public class SelectOffsetAndLimitTest {

	@Test
	public void testLimit() {
		final String sqlCerto = new StringBuilder()
		.append("select autor.id, autor.ds_nome, autor.dt_nascimento, autor.endereco_id, ")
		.append("autor.editora_id from autor limit 10")
		.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Autor.class);
		sqlBuilder.limit(10);
	
		String sqlGerado = sqlBuilder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testOffseAndLimit() {
		final String sqlCerto = new StringBuilder()
		.append("select autor.id, autor.ds_nome, autor.dt_nascimento, autor.endereco_id, ")
		.append("autor.editora_id from autor offset 10 limit 10")
		.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Autor.class);
		sqlBuilder.offset(10);
		sqlBuilder.limit(10);
	
		String sqlGerado = sqlBuilder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
}
