package com.urbainski.test.generic;

import org.junit.AfterClass;

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
	
	/**
	 * Método para ser sobrescrito caso a classe de teste unitário
	 * precise fazer alguma coisa apos o teste unitário terminar.
	 */
	public static void beforeClassCallback() {
		
	}
	
}