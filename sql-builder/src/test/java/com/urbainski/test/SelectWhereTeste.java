package com.urbainski.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.entidade.Autor;
import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SQLBuilder;
import com.urbainski.sql.builder.condititon.Condition;
import com.urbainski.sql.builder.condititon.impl.ConditionBuilder;
import com.urbainski.sql.builder.db.types.ConditionDBTypes;
import com.urbainski.sql.builder.db.types.ConstainsDBTypes;

/**
 * Classe de teste para queries com where.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
public class SelectWhereTeste {

	@Test
	public void testeEqual() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.id = 5")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConditionDBTypes.EQUALS, "id", 5);
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeDifferent() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.id <> 5")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConditionDBTypes.DIFFERENT, "id", 5);
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeEqualNome() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.ds_nome = 'Senhor dos Aneis'")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConditionDBTypes.EQUALS, "nome", "Senhor dos Aneis");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	@SuppressWarnings("serial")
	public void testeIn() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.id in (3,4,5)")
		.toString();
		
		final List<Integer> valores = new ArrayList<Integer>() {{
			
			add(3);
			add(4);
			add(5);
			
		}};
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConditionDBTypes.IN, "id", valores);
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	@SuppressWarnings("serial")
	public void testeNotIn() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.id not in (3,4,5)")
		.toString();
		
		final List<Integer> valores = new ArrayList<Integer>() {{
			
			add(3);
			add(4);
			add(5);
			
		}};
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConditionDBTypes.NOT_IN, "id", valores);
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testeIllegalArgumentException() {
		
		int[] valores = new int[]{3,4,5};
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConditionDBTypes.NOT_IN, "id", valores);
	}
	
	@Test
	public void testeWhereBetween() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.id between 1 and 10")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConditionDBTypes.BETWEEN, "id", 1, 10);
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeWhereOr() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where (livro.id = 1 or livro.id = 10)")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		
		Condition condition1 = ConditionBuilder.newCondition(Livro.class,
				ConditionDBTypes.EQUALS, "id", 1);
		Condition condition2 = ConditionBuilder.newCondition(Livro.class,
				ConditionDBTypes.EQUALS, "id", 10);
		
		Condition or = ConditionBuilder.newCondition(ConditionDBTypes.OR, 
				condition1, condition2);
		
		builder.where(or);
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeWhereAnd() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where (livro.id = 1 and livro.id = 10)")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		
		Condition condition1 = ConditionBuilder.newCondition(Livro.class,
				ConditionDBTypes.EQUALS, "id", 1);
		Condition condition2 = ConditionBuilder.newCondition(Livro.class,
				ConditionDBTypes.EQUALS, "id", 10);
		
		Condition and = ConditionBuilder.newCondition(ConditionDBTypes.AND, 
				condition1, condition2);
		
		builder.where(and);
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeWhereAndEOr() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where ((livro.id = 1 and livro.id = 10) or (livro.id = 2 and livro.id = 8))")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		
		Condition condition1 = ConditionBuilder.newCondition(Livro.class,
				ConditionDBTypes.EQUALS, "id", 1);
		Condition condition2 = ConditionBuilder.newCondition(Livro.class,
				ConditionDBTypes.EQUALS, "id", 10);
		
		Condition and1 = ConditionBuilder.newCondition(ConditionDBTypes.AND, 
				condition1, condition2);
		
		Condition condition3 = ConditionBuilder.newCondition(Livro.class,
				ConditionDBTypes.EQUALS, "id", 2);
		Condition condition4 = ConditionBuilder.newCondition(Livro.class,
				ConditionDBTypes.EQUALS, "id", 8);
		
		Condition and2 = ConditionBuilder.newCondition(ConditionDBTypes.AND, 
				condition3, condition4);
		
		Condition or = ConditionBuilder.newCondition(ConditionDBTypes.OR, 
				and1, and2);
		
		builder.where(or);
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeLikeAny() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.ds_nome like '%Senhor%'")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConstainsDBTypes.ANY, ConditionDBTypes.LIKE, "nome", "Senhor");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeIlikeAny() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.ds_nome ilike '%Senhor%'")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConstainsDBTypes.ANY, ConditionDBTypes.ILIKE, "nome", "Senhor");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeLikeStart() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.ds_nome like '%Senhor'")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConstainsDBTypes.IN_START, ConditionDBTypes.LIKE, "nome", "Senhor");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeIlikeStart() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.ds_nome ilike '%Senhor'")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConstainsDBTypes.IN_START, ConditionDBTypes.ILIKE, "nome", "Senhor");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeLikeFinish() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.ds_nome like 'Senhor%'")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConstainsDBTypes.IN_FINISH, ConditionDBTypes.LIKE, "nome", "Senhor");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeIlikeFinish() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.ds_nome ilike 'Senhor%'")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConstainsDBTypes.IN_FINISH, ConditionDBTypes.ILIKE, "nome", "Senhor");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeEqualComAlias() {
		final String sqlCerto = new StringBuilder()
		.append("select l.id, l.ds_nome, l.nr_anopublicacao, l.autor_id ")
		.append("from livro as l where l.id = 5")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.fromAlias("l");
		builder.where(ConditionDBTypes.EQUALS, "id", 5);
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}

	@Test
	public void testeEqualComAliasMudadoDepoisDoWhere() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, l0.nr_anopublicacao, l0.autor_id ")
		.append("from livro as l0 where l0.id = 10")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConditionDBTypes.EQUALS, "id", 10);
		builder.fromAlias("l0");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeBetweenComAliasMudadoDepoisDoWhere() {
		final String sqlCerto = new StringBuilder()
		.append("select l0.id, l0.ds_nome, l0.nr_anopublicacao, l0.autor_id ")
		.append("from livro as l0 where l0.id between 10 and 30")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConditionDBTypes.BETWEEN, "id", 10, 30);
		builder.fromAlias("l0");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeEqualWithIdOutherTable() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id ")
		.append("from livro where livro.autor_id = 9")
		.toString();
		
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.where(ConditionDBTypes.EQUALS, "autor", 9);
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
	@Test
	public void testeEqualInJoinField() {
		final String sqlCerto = new StringBuilder()
		.append("select livro.id, livro.ds_nome, livro.nr_anopublicacao, livro.autor_id, ")
		.append("autor.id, autor.ds_nome, autor.dt_nascimento, autor.endereco_id ")
		.append("from livro ")
		.append("inner join autor on livro.autor_id = autor.id ")
		.append("where autor.ds_nome ilike '%cristian%'")
		.toString();
	
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.addJoin(Autor.class, "autor");
		builder.where(ConstainsDBTypes.ANY, ConditionDBTypes.ILIKE, Autor.class, "nome", "cristian");
		
		String sqlGerado = builder.buildSQL();
		System.out.println(sqlGerado);
		
		Assert.assertEquals(sqlCerto, sqlGerado);
	}
	
}