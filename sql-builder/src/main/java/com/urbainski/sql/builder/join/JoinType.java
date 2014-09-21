package com.urbainski.sql.builder.join;

/**
 * Tipo de join posiveis.
 * 
 * @author Cristian Urbainski <cristian.urbainski@consisanet.com>
 * @since 20/09/2014
 * @version 1.0
 *
 */
public enum JoinType {

	/**
	 * Join do tipo inner.
	 */
	INNER("inner join"),
	
	/**
	 * Join do tipo left.
	 */
	LEFT("left join"),
	
	/**
	 * Join do tipo rigth.
	 */
	RIGHT("right join");
	
	private String name;
	
	private JoinType(String name) {
		this.name = name;
	}
	
	public String getJoinName() {
		return name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
