package com.urbainski.test.generic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import com.urbainski.test.LimpaBancoDados;
import com.urbainski.test.PopulaBancoDados;
import com.urbainski.test.app.util.EntityManagerUtil;

/**
 * Classe abstrata para as classes de testes.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 02/10/2014
 * @version 1.0
 *
 */
public abstract class AbstractGenericTest {

	/**
	 * Método que é executado depois da classe de teste.
	 */
	@AfterClass
	public static void beforeClass() {
		beforeClassCallback();
		
		EntityManagerUtil.getDefaultInstance().getEntityManager().close();
		EntityManagerUtil.getDefaultInstance().getEntityManagerFactory().close();
	}
	
	@Before
	public void beforeMethod() {
		PopulaBancoDados.main(new String[]{});
		
		beforeMethodCallback();
	}
	
	/**
	 * Método chamado
	 */
	@After
	public void afterMethod() {
		LimpaBancoDados.main(new String[]{});
		
		afterMethodCallback();
	}
	
	/**
	 * Método para ser sobrescrito caso a classe de teste unitário
	 * precise fazer alguma coisa apos o teste unitário terminar.
	 */
	public static void beforeClassCallback() {
		
	}
	
	/**
	 * Método invocado antes a chamado do método de testes terminar.
	 */
	public void beforeMethodCallback() {
		
	}
	
	/**
	 * Método invocado após a chamado do método de testes terminar.
	 */
	public void afterMethodCallback() {
		
	}
	
}