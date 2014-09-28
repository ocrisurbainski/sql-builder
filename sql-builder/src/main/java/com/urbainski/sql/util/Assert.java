package com.urbainski.sql.util;

/**
 * Classe para conter métodos validadores.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 27/09/2014
 * @version 1.0
 *
 */
public final class Assert {

	/**
	 * Construtor privado para não permitir
	 * que a classe seja instancia.
	 */
	private Assert() {
		
	}
	
	/**
	 * Método para validar se um argumento não é nulo.
	 * 
	 * @param object - objeto a ser válidado
	 * @param message - mensagem para se o objeto ofender a validação 
	 * 		feita pelo método
	 */
	public static void parameterNotNull(Object object, String message) {
		
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Método para validar se um argumento não é nulo nem vazio.
	 * 
	 * @param string - objeto a ser válidado
	 * @param message - mensagem para se o objeto ofender a validação 
	 * 		feita pelo método
	 */
	public static void parameterNotNullAndNotEmpty(String string,
			String message) {
		
		if (string == null || string.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}
	
}