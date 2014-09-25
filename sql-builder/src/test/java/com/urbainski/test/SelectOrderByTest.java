package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SQLBuilder;
import com.urbainski.sql.builder.db.types.OrderByDBTypes;
import com.urbainski.sql.builder.field.Field;
import com.urbainski.sql.builder.field.FieldBuilder;
import com.urbainski.sql.builder.orderby.OrderBy;

/**
 * Classe de teste para queries com {@link OrderBy}.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 23/09/2014
 * @version 1.0
 *
 */
public class SelectOrderByTest {

	@Test
	public void testeOrderBySimpleAsc() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, ")
		.append("livro.nr_anopublicacao, livro.autor_id from livro ")
		.append("order by livro.id asc")
		.toString();
	
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.orderBy(OrderByDBTypes.ASC);
		sqlBuilder.addFieldInOrderBy("id");
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testeOrderBySimpleDesc() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, ")
		.append("livro.nr_anopublicacao, livro.autor_id from livro ")
		.append("order by livro.id desc")
		.toString();
	
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.orderBy(OrderByDBTypes.DESC);
		sqlBuilder.addFieldInOrderBy("id");
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testeOrderByCompostoAsc() {
		final String sqlCerto = new StringBuilder()
		.append("select l.id as identificadorLivro, ")
		.append("l.ds_nome as nomeLivro, ")
		.append("l.nr_anopublicacao as anoLivro from livro as l")
		.append(" order by l.id, l.ds_nome asc")
		.toString();
		
		Field fieldId 	= FieldBuilder.newField(
				Livro.class, "", "id", 	"identificadorLivro");
		Field fieldNome = FieldBuilder.newField(
				Livro.class, "", "nome", "nomeLivro");
	
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.select().addField(fieldId, fieldNome);
		sqlBuilder.select().addField("anoPublicacao", 	"anoLivro");
		sqlBuilder.fromAlias("l");
		
		sqlBuilder.orderBy().addField(fieldId, fieldNome);
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
	@Test
	public void testeOrderByCompostoDesc() {
		final String sqlCerto = new StringBuilder()
		.append("select l.id as identificadorLivro, ")
		.append("l.ds_nome as nomeLivro, ")
		.append("l.nr_anopublicacao as anoLivro from livro as l")
		.append(" order by l.id, l.ds_nome desc")
		.toString();
		
		Field fieldId 	= FieldBuilder.newField(
				Livro.class, "", "id", 	"identificadorLivro");
		Field fieldNome = FieldBuilder.newField(
				Livro.class, "", "nome", "nomeLivro");
	
		SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
		sqlBuilder.select().addField(fieldId, fieldNome);
		sqlBuilder.select().addField("anoPublicacao", 	"anoLivro");
		sqlBuilder.fromAlias("l");
		
		sqlBuilder.orderBy(OrderByDBTypes.DESC).addField(fieldId, fieldNome);
		
		final String sqlGerado = sqlBuilder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
	}
	
}