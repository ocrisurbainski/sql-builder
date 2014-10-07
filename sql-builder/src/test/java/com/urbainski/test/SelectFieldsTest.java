package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SQLBuilder;

/**
 * Classe de teste unitários para testar o select de apenas 
 * alguns campos na query.
 * 
 * @author Cristian Urbainski<cristianurbainskips@gmail.com>
 * @since 27/09/2014
 * @version 1.0
 *
 */
public class SelectFieldsTest {
	
	/**
	 * Teste unitário para selecionar apenas alguns campos de uma tabela unida com 'join'.
	 */
	@Test
	public void testeSelectFieldsOfJoinSemAlias() {
		final String sqlCerto = new StringBuilder()
		.append("select ")
		.append("autor.id, autor.ds_nome, autor.dt_nascimento ")
		.append("from livro as l0 ")
		.append("inner join autor a0 on ")
		.append("l0.autor_id = a0.id")
		.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.select().addField(Autor.class, "id");
		sqlBuilder.select().addField(Autor.class, "nome");
		sqlBuilder.select().addField(Autor.class, "dataNascimento");
		sqlBuilder.fromAlias("l0");
		sqlBuilder.addJoin(Autor.class, "a0", "autor");
		
		String sqlGerado = sqlBuilder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}

	/**
	 * Teste unitário para selecionar apenas alguns campos de uma tabela unida com 'join'
	 * usando alias nos campos.
	 */
	@Test
	public void testeSelectFieldsOfJoinComAlias() {
		final String sqlCerto = new StringBuilder()
		.append("select ")
		.append("a0.id, a0.ds_nome, a0.dt_nascimento ")
		.append("from livro as l0 ")
		.append("inner join autor a0 on ")
		.append("l0.autor_id = a0.id")
		.toString();
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.select().addField(Autor.class, "a0", "id", "");
		sqlBuilder.select().addField(Autor.class, "a0", "nome", "");
		sqlBuilder.select().addField(Autor.class, "a0", "dataNascimento", "");
		sqlBuilder.fromAlias("l0");
		sqlBuilder.addJoin(Autor.class, "a0", "autor");
		
		String sqlGerado = sqlBuilder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
}
