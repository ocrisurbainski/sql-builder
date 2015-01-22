package com.urbainski.test.app.dao.generic.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;

import com.urbainski.sql.builder.SelectBuilder;
import com.urbainski.sql.db.types.ConditionDBTypes;
import com.urbainski.test.app.dao.generic.GenericDAO;
import com.urbainski.test.app.util.EntityManagerUtil;

/**
 * Implementação do dao padrão.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 02/10/2014
 * @version 1.0
 * 
 * @param <PK> - classe da pk da entidade
 * @param <T> - classe de entidade
 */
@SuppressWarnings("unchecked")
public class GenericDAOImpl<PK, T> implements GenericDAO<PK, T> {
	
	/**
	 * {@link EntityManager}.
	 */
	protected EntityManager entityManager;
	
	/**
	 * Classe de entidade.
	 */
	protected Class<?> entityClass;
	
	/**
	 * Construtor padrão da classe.
	 */
	public GenericDAOImpl() {
		this.entityManager = EntityManagerUtil.getDefaultInstance().getEntityManager();
		this.entityClass = (Class<T>) 
				((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

	}

	@Override
	public T save(T obj) {
		this.entityManager.persist(obj);
		return obj;
	}

	@Override
	public T update(T obj) {
		this.entityManager.merge(obj);
		return obj;
	}

	@Override
	public void delete(PK id) {
		this.entityManager.remove(this.entityManager.getReference(this.entityClass, id));
	}

	@Override
	public List<T> findAll() {
		SelectBuilder sqlBuilder = new SelectBuilder(this.entityClass);
		
		Query q = this.entityManager.createNativeQuery(sqlBuilder.buildSQL(), this.entityClass);
		return q.getResultList();
	}

	@Override
	public List<T> findAll(int offset, int limit) {
		SelectBuilder sqlBuilder = new SelectBuilder(this.entityClass);
		sqlBuilder.offset(offset);
		sqlBuilder.limit(limit);
		
		Query q = this.entityManager.createNativeQuery(sqlBuilder.buildSQL(), this.entityClass);
		return q.getResultList();
	}

	@Override
	public T findById(PK id) {
		String propertyId = getIdOfEntity();
		if (propertyId == null) {
			throw new IllegalStateException("Entidade " + entityClass.getName() + " não possui id mapeado.");
		}
		
		SelectBuilder sqlBuilder = new SelectBuilder(this.entityClass);
		sqlBuilder.where(ConditionDBTypes.EQUALS, propertyId, id);
		
		Query q = this.entityManager.createNativeQuery(sqlBuilder.buildSQL(), this.entityClass);
		return (T) q.getSingleResult();
	}

	@Override
	public void begin() {
		this.entityManager.getTransaction().begin();
	}

	@Override
	public void commit() {
		this.entityManager.getTransaction().commit();
	}

	@Override
	public void rollback() {
		this.entityManager.getTransaction().rollback();
	}

	/**
	 * Recupera o campo que referencia o id da tabela.
	 * 
	 * @return nome do campo
	 */
	private String getIdOfEntity() {
		for (Field f : entityClass.getDeclaredFields()) {
			if (f.isAnnotationPresent(Id.class)) {
				return f.getName();
			}
		}
		return null;
	}
	
}