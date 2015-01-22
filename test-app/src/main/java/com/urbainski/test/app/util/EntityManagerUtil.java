package com.urbainski.test.app.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe utilitária para criação de um {@link EntityManager} único.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 02/10/2014
 * @version 1.0
 *
 */
public final class EntityManagerUtil {

	/**
	 * Instância da própria classe.
	 */
	private static EntityManagerUtil instance;
	
	/**
	 * Fábrica de sessões do {@link EntityManager}.
	 */
	private EntityManagerFactory entityManagerFactory;
	
	/**
	 * {@link EntityManager}.
	 */
	private EntityManager entityManager;
	
	/**
	 * Construtor padrão privado.
	 */
	private EntityManagerUtil() {
		createEntityManager();
	}
	
	/**
	 * Método responsável por criar uma nova instância de {@link EntityManagerFactory}.
	 */
	private void createEntityManagerFactory() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("testePu");
	}

	/**
	 * Método responsável por criar uma nova instância de {@link EntityManager}.
	 */
	private void createEntityManager() {
		if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
			createEntityManagerFactory();
		}
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
	
	/**
	 * Retorna a instância única desta classe.
	 * 
	 * @return {@link EntityManagerUtil}
	 */
	public static EntityManagerUtil getDefaultInstance() {
		if (instance == null) {
			instance = new EntityManagerUtil();
		}
		return instance;
	}
	
	public EntityManager getEntityManager() {
		if (!entityManager.isOpen()) {
			createEntityManager();
		}
		return entityManager;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
}
