package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SelectBuilder;
import com.urbainski.sql.db.types.ConditionDBTypes;

/**
 * Classe para testar os subselects.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 28/09/2014
 * @version 1.0
 *
 */
public class SubselectTest {
	
	@Test
	public void testSubselectFieldSemAlias() {
		final String sqlCerto = new StringBuilder()
			.append("select ")
			.append("(select autor.ds_nome from autor where autor.id = 9) ")
			.append("from livro")
			.toString();
	
		SelectBuilder subselect = new SelectBuilder(Autor.class);
		subselect.select().addField("nome");
		subselect.where(ConditionDBTypes.EQUALS, "id", 9);
		
		SelectBuilder sqlBuilder = new SelectBuilder(Livro.class);
		sqlBuilder.select().addField(subselect);
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testSubselectFieldComAlias() {
		final String sqlCerto = new StringBuilder()
			.append("select ")
			.append("(select a0.ds_nome from autor as a0 where a0.id = 9) ")
			.append("from livro as l0")
			.toString();
	
		SelectBuilder subselect = new SelectBuilder(Autor.class);
		subselect.select().addField("nome");
		subselect.where(ConditionDBTypes.EQUALS, "id", 9);
		subselect.fromAlias("a0");
		
		SelectBuilder sqlBuilder = new SelectBuilder(Livro.class);
		sqlBuilder.select().addField(subselect);
		sqlBuilder.fromAlias("l0");
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}

	@Test
	public void testSubselectWhereIn() {
		final String sqlCerto = new StringBuilder()
			.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, ")
			.append("livro.autor_id from livro where livro.autor_id in (")
			.append("select autor.id from autor where autor.ds_nome = 'Vinicius de Moraes')")
			.toString();
		
		SelectBuilder subselect = new SelectBuilder(Autor.class);
		subselect.select().addField("id");
		subselect.where(ConditionDBTypes.EQUALS, "nome", "Vinicius de Moraes");
		
		SelectBuilder sqlBuilder = new SelectBuilder(Livro.class);
		sqlBuilder.where(ConditionDBTypes.IN, "autor", subselect);
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testSubselectWhereEqual() {
		final String sqlCerto = new StringBuilder()
			.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, ")
			.append("livro.autor_id from livro where livro.autor_id = (")
			.append("select autor.id from autor where autor.ds_nome = 'Vinicius de Moraes')")
			.toString();
		
		SelectBuilder subselect = new SelectBuilder(Autor.class);
		subselect.select().addField("id");
		subselect.where(ConditionDBTypes.EQUALS, "nome", "Vinicius de Moraes");
		
		SelectBuilder sqlBuilder = new SelectBuilder(Livro.class);
		sqlBuilder.where(ConditionDBTypes.EQUALS, "autor", subselect);
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
}