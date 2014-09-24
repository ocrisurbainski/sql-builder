package com.urbainski.sql.util;

/**
 * Classe utilitária para a construção dos SQL.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public final class SQLUtils {

	/**
	 * Clausula select do sql.
	 */
	public static final String SELECT = "select";
	
	/**
	 * Distinct para consultas sql.
	 */
	public static final String DISTINCT = "distinct";
	
	/**
	 * From da consulta.
	 */
	public static final String FROM = "from";
	
	/**
	 * Where para indicar condições.
	 */
	public static final String WHERE = "where";
	
	/**
	 * AS para alias.
	 */
	public static final String AS = "as";
	
	/**
	 * 	Group by.
	 */
	public static final String GROUP_BY = "group by";
	
	/**
	 * Order by.
	 */
	public static final String ORDER_BY = "order by";

	/**
	 * Construtor privado.
	 */
	private SQLUtils() {
		
	}
	
}