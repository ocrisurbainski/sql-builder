package com.urbainski.test;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import com.urbainski.test.app.entidade.Cargo;
import com.urbainski.test.app.entidade.Cliente;
import com.urbainski.test.app.entidade.Colaborador;
import com.urbainski.test.app.entidade.Endereco;
import com.urbainski.test.app.entidade.Estado;
import com.urbainski.test.app.entidade.Genero;
import com.urbainski.test.app.entidade.Locacao;
import com.urbainski.test.app.entidade.Locacaomidia;
import com.urbainski.test.app.entidade.Midia;
import com.urbainski.test.app.entidade.Municipio;
import com.urbainski.test.app.entidade.Pessoa;
import com.urbainski.test.app.entidade.Tipolocacoes;
import com.urbainski.test.app.entidade.Tipomidia;
import com.urbainski.test.app.util.EntityManagerUtil;

/**
 * Classe par limpar o banco de dados.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 16/01/2015
 * @version 1.0
 *
 */
public class LimpaBancoDados {

	/**
	 * Método que executa a limpeza do banco de dados.
	 * 
	 * @param args - parametros
	 */
	public static void main(String args[]) {
		LimpaBancoDados principal = new LimpaBancoDados();
		principal.clearDataBase();
		
		EntityManagerUtil.getDefaultInstance().getEntityManager().close();
		EntityManagerUtil.getDefaultInstance().getEntityManagerFactory().close();
	}
	
	/**
	 * Método que executa a limpeza do banco de dados.
	 */
	public void clearDataBase() {
		EntityManager entityManager = EntityManagerUtil.getDefaultInstance().getEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		entityManager.getTransaction().begin();
		
		clearLocacaoMidia(entityManager, criteriaBuilder);
		clearMidia(entityManager, criteriaBuilder);
		clearTipoMidia(entityManager, criteriaBuilder);
		clearTipoLocacoes(entityManager, criteriaBuilder);
		clearGenero(entityManager, criteriaBuilder);
		clearLocacao(entityManager, criteriaBuilder);
		clearColaborador(entityManager, criteriaBuilder);
		clearCliente(entityManager, criteriaBuilder);
		clearCargo(entityManager, criteriaBuilder);
		clearPessoa(entityManager, criteriaBuilder);
		
		clearEndereco(entityManager, criteriaBuilder);
		clearMunicipios(entityManager, criteriaBuilder);
		clearEstados(entityManager, criteriaBuilder);
		
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Método para limpar pessoa.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearPessoa(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {
		
		CriteriaDelete<Pessoa> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Pessoa.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar cargo.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearCargo(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {
		
		CriteriaDelete<Cargo> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Cargo.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar colaborador.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearColaborador(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {
		
		CriteriaDelete<Colaborador> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Colaborador.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar cliente.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearCliente(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {
		
		CriteriaDelete<Cliente> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Cliente.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar locacao.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearLocacao(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {

		CriteriaDelete<Locacao> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Locacao.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar genero.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearGenero(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {
		
		CriteriaDelete<Genero> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Genero.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar tipo_locacoes.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearTipoLocacoes(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {

		CriteriaDelete<Tipolocacoes> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Tipolocacoes.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar tipo_midia.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearTipoMidia(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {

		CriteriaDelete<Tipomidia> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Tipomidia.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar midia.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearMidia(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {
		
		CriteriaDelete<Midia> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Midia.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar locacao_midia.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearLocacaoMidia(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {
		
		CriteriaDelete<Locacaomidia> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Locacaomidia.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar endereco.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearEndereco(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {
		
		CriteriaDelete<Endereco> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Endereco.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar municipios.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearMunicipios(EntityManager entityManager,
			CriteriaBuilder criteriaBuilder) {

		CriteriaDelete<Municipio> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Municipio.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * Método para limpar estados.
	 * 
	 * @param entityManager - objeto para fazer as consultas
	 * @param criteriaBuilder - objeto para auxiliar nas consultas
	 */
	private void clearEstados(
			EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
		
		CriteriaDelete<Estado> criteriaDelete = 
				criteriaBuilder.createCriteriaDelete(Estado.class);
		entityManager.createQuery(criteriaDelete).executeUpdate();
	}
}