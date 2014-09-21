package com.urbainski.sql.builder;

/**
 * Interface para definição de métodos comuns ao objetos builder.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 19/09/2014
 * @version 1.0
 *
 */
public interface Builder {

	/**
	 * Método responsável por construir a consulta SQL.
	 * 
	 * @return SQL gerado
	 */
	public String buildSQL();
	
}
