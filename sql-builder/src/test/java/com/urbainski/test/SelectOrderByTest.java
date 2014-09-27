package com.urbainski.test;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
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
				Livro.class, "id", 	"identificadorLivro");
		Field fieldNome = FieldBuilder.newField(
				Livro.class, "nome", "nomeLivro");
	
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
	
	@Test
	public void testOrderByFieldAtJoin() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id, ")
		.append("autor.id, autor.ds_nome, autor.dt_nascimento, autor.endereco_id ")
		.append("from livro inner join autor on livro.autor_id = autor.id ")
		.append("order by autor.ds_nome asc")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.addJoin(Autor.class, "autor");
		builder.orderBy(OrderByDBTypes.ASC);
		builder.addFieldInOrderBy(Autor.class, "nome");
		
		final String sqlGerado = builder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
		
	}
	
	@Test
	public void testOrderByFieldAtJoinWithAlias() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, l0.nr_anopublicacao, l0.autor_id, ")
		.append("a0.id, a0.ds_nome, a0.dt_nascimento, a0.endereco_id ")
		.append("from livro as l0 inner join autor a0 on l0.autor_id = a0.id ")
		.append("order by a0.ds_nome asc")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.fromAlias("l0");
		builder.addJoin(Autor.class, "a0", "autor");
		builder.orderBy(OrderByDBTypes.ASC);
		builder.addFieldInOrderBy(Autor.class, "a0", "nome");
		
		final String sqlGerado = builder.buildSQL();
		
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlGerado, sqlCerto);
		
	}
	
}