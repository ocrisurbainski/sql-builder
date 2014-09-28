package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SQLBuilder;
import com.urbainski.sql.db.types.ConditionDBTypes;

/**
 * Classe para testar os subselects.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 28/09/2014
 * @version 1.0
 *
 */
public class SubselectTest {

	@Test
	public void testSubselectSimpleIn() {
		final String sqlCerto = new StringBuilder()
			.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, ")
			.append("livro.autor_id from livro where livro.autor_id in (")
			.append("select autor.id from autor where autor.ds_nome = 'Vinicius de Moraes')")
			.toString();
		
		SQLBuilder subselect = new SQLBuilder(Autor.class);
		subselect.select().addField("id");
		subselect.where(ConditionDBTypes.EQUALS, "nome", "Vinicius de Moraes");
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.where(ConditionDBTypes.IN, "autor", subselect);
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testSubselectSimpleEqual() {
		final String sqlCerto = new StringBuilder()
			.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, ")
			.append("livro.autor_id from livro where livro.autor_id = (")
			.append("select autor.id from autor where autor.ds_nome = 'Vinicius de Moraes')")
			.toString();
		
		SQLBuilder subselect = new SQLBuilder(Autor.class);
		subselect.select().addField("id");
		subselect.where(ConditionDBTypes.EQUALS, "nome", "Vinicius de Moraes");
		
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.where(ConditionDBTypes.EQUALS, "autor", subselect);
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
}