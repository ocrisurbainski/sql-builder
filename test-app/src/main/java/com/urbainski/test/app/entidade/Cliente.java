package com.urbainski.test.app.entidade;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
public class Cliente extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
