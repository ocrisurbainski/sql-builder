package com.urbainski.test;

import org.junit.Test;

import com.urbainski.test.app.util.EntityManagerUtil;
import com.urbainski.test.generic.AbstractTestGenerico;

/**
 * Teste unitário de selects simples.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 02/10/2014
 * @version 1.0
 *
 */
public class TestSelectsSimples extends AbstractTestGenerico {

	@Test
	public void test1() {
		popularBancoDados();
		
		EntityManagerUtil.getDefaultInstance().getEntityManager().close();
		EntityManagerUtil.getDefaultInstance().getEntityManagerFactory().close();
	}
	
}