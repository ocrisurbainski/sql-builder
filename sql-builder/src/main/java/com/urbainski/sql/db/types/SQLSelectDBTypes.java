package com.urbainski.sql.db.types;

/**
 * Enum para os tipos comuns de clausulas dos selects.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 27/09/2014
 *
 */
public enum SQLSelectDBTypes {
	
	/**
	 * Clausula 'as' do SQL.
	 */
	AS("as"),
	
	/**
	 * Clausula 'select' do SQL.
	 */
	SELECT("select"),
	
	/**
	 * Clausula 'distinct' do SQL.
	 */
	DISTINCT("distinct"),
	
	/**
	 * Clausula 'from' do SQL.
	 */
	FROM("from"),
	
	/**
	 * Clausula 'where' do SQL.
	 */
	WHERE("where"),
	
	/**
	 * Clausula 'group by' do SQL.
	 */
	GROUP_BY("group by"), 
	
	/**
	 * Clausula 'order by' do SQL.
	 */
	ORDER_BY("order by"),
	
	/**
	 * Clausula 'offset' do SQL.
	 */
	OFFSET("offset"),
	
	/**
	 * Clausula 'limit' do SQL.
	 */
	LIMIT("limit");

	/**
	 * Nome da clausula de sql.
	 */
	private String name;
	
	/**
	 * Construtor privado do enum.
	 * 
	 * @param name - Nome da clausula de sql.
	 */
	private SQLSelectDBTypes(String name) {
		this.name = name;
	}
	
	public String getSQLSelectType() {
		return name;
	}
	
}
