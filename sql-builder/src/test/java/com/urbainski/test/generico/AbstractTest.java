package com.urbainski.test.generico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Classe abstrata do teste unitário.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public abstract class AbstractTest {

	/**
	 * Fabrica de {@link EntityManager}.
	 */
	protected static EntityManagerFactory entityManagerFactory;
	
	/**
	 * Objeto para interações com a base de dados.
	 */
	protected static EntityManager entityManager;
	
	/**
	 * Método de configuração do teste unitário.
	 */
	@BeforeClass
	public static void setupClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("testePu");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	/**
	 * Método para fechar a conexão quando não é mais necessária.
	 */
	@AfterClass
	public static void closeClass() {
		if (entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManagerFactory.close();
	}
	
	/**
	 * Abrir transação.
	 */
	public void begin() {
		entityManager.getTransaction().begin();
	}
	
	/**
	 * Commitar transação.
	 */
	public void commit() {
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Rollback na transação.
	 */
	public void rollback() {
		entityManager.getTransaction().rollback();
	}
	
}