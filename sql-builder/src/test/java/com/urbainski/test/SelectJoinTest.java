package com.urbainski.test;

import org.junit.Test;

import com.urbainski.entidade.Livro;
import com.urbainski.sql.builder.SQLBuilder;
import com.urbainski.sql.builder.join.JoinBuilder;
import com.urbainski.sql.builder.join.JoinType;

public class SelectJoinTest {

	@Test
	public void test() {
		SQLBuilder builder = new SQLBuilder(Livro.class);
		builder.addJoin(JoinBuilder.newJoin("teste", JoinType.INNER));
	}
	
}
