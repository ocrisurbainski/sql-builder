package com.urbainski.sql.db.types;

/**
 * Enum com os tipos de unios.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 24/09/2014
 * @version 1.0
 *
 */
public enum UnionDBTypes {
	
	/**
	 * Union do tipo union.
	 */
	UNION("union"),
	
	/**
	 * Union do tipo all.
	 */
	UNION_ALL("union all");
	
	/**
	 * Tipo do union
	 */
	private String name;
	
	/**
	 * Construtor do enum.
	 * 
	 * @param name - tipo do union
	 */
	private UnionDBTypes(String name) {
		this.name = name;
	}
	
	/**
	 * Retorna o tipo do union.
	 * 
	 * @return tipo do union
	 */
	public String getUnionType() {
		return name;
	}

}
