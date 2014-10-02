package com.urbainski.test;

import com.urbainski.test.app.dao.EstadoDAO;
import com.urbainski.test.app.entidade.Estado;

public abstract class AbstractTestGenerico {

	public void popularBancoDados() {
		popularEstados();
	}

	private void popularEstados() {
		EstadoDAO dao = new EstadoDAO();
		dao.begin();
		
		try {
			Estado estadoParana = new Estado();
			estadoParana.setDsEstado("Paraná");
			estadoParana.setUfEstado("PR");
			
			Estado estadoSc = new Estado();
			estadoSc.setDsEstado("Santa Catarina");
			estadoSc.setUfEstado("SC");
			
			Estado estadoSp = new Estado();
			estadoSp.setDsEstado("São Paulo");
			estadoSp.setUfEstado("SP");
			
			Estado estadoRs = new Estado();
			estadoRs.setDsEstado("Rio Grande do Sul");
			estadoRs.setUfEstado("RS");
			
			dao.save(estadoParana);
			dao.save(estadoSc);
			dao.save(estadoSp);
			dao.save(estadoRs);
			
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			dao.rollback();
		}
	}
	
}
