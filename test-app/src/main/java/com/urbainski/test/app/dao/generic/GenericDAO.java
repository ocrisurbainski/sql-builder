package com.urbainski.test.app.dao.generic;

import java.util.List;

/**
 * Interface para o dao.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 02/10/2014
 * @version 1.0
 *
 * @param <PK> - classe da pk da entidade
 * @param <T> - classe de entidade
 */
public interface GenericDAO<PK, T> {

	/**
	 * Salva no banco de dados.
	 * 
	 * @param obj - objeto a ser salvo
	 * 
	 * @return T
	 */
	T save(T obj);
	
	/**
	 * Atualiza no banco de dados.
	 * 
	 * @param obj - objeto a ser atualizado
	 * 
	 * @return T
	 */
	T update(T obj);
	
	/**
	 * Deleta no banco de dados.
	 * 
	 * @param id - identificador para excluir objeto
	 */
	void delete(PK id);

	/**
	 * Busca todos os objetos.
	 * 
	 * @return List<T>
	 */
	List<T> findAll();
	
	/**
	 * Busca todos os objetos com paginação.
	 * 
	 * @return List<T>
	 */
	List<T> findAll(int offset, int limit);
	
	/**
	 * Busca pelo id.
	 * 
	 * @param id - identificador
	 * 
	 * @return T
	 */
	T findById(PK id);
	
	/**
	 * Abre um nova transação.
	 */
	void begin();
	
	/**
	 * Comita transação atual.
	 */
	void commit();
	
	/**
	 * Descarta transação atual.
	 */
	void rollback();
}
