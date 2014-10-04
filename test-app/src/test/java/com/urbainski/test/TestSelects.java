package com.urbainski.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.urbainski.test.app.dao.ClienteDAO;
import com.urbainski.test.app.dao.MidiaDAO;
import com.urbainski.test.app.dto.DtoMidia;
import com.urbainski.test.generic.AbstractGenericTest;

/**
 * Teste unitário de selects simples.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 02/10/2014
 * @version 1.0
 *
 */
public class TestSelects extends AbstractGenericTest {

	@Test
	public void testCountMidiaPorTipo() {
		try {
			MidiaDAO midiaDao = new MidiaDAO();
			List<DtoMidia> list = midiaDao.countMidiasPorTipo();
			
			Assert.assertNotEquals(list.size(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTopDezClientes() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.getTopDezClientes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}