package com.urbainski.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.urbainski.test.app.dao.CargoDAO;
import com.urbainski.test.app.dao.ClienteDAO;
import com.urbainski.test.app.dao.ColaboradorDAO;
import com.urbainski.test.app.dao.EstadoDAO;
import com.urbainski.test.app.dao.GeneroDAO;
import com.urbainski.test.app.dao.LocacaoDAO;
import com.urbainski.test.app.dao.MidiaDAO;
import com.urbainski.test.app.dao.MunicipioDAO;
import com.urbainski.test.app.dao.TipolocacoesDAO;
import com.urbainski.test.app.dao.TipomidiaDAO;
import com.urbainski.test.app.entidade.Cargo;
import com.urbainski.test.app.entidade.Cliente;
import com.urbainski.test.app.entidade.Colaborador;
import com.urbainski.test.app.entidade.Endereco;
import com.urbainski.test.app.entidade.Estado;
import com.urbainski.test.app.entidade.Genero;
import com.urbainski.test.app.entidade.Locacao;
import com.urbainski.test.app.entidade.Locacao.SituacaoLocacao;
import com.urbainski.test.app.entidade.Locacaomidia;
import com.urbainski.test.app.entidade.Midia;
import com.urbainski.test.app.entidade.Midia.SituacaoMidia;
import com.urbainski.test.app.entidade.Municipio;
import com.urbainski.test.app.entidade.Tipolocacoes;
import com.urbainski.test.app.entidade.Tipomidia;
import com.urbainski.test.app.util.EntityManagerUtil;

/**
 * Classe abstrata para as classes de teste.
 * 
 * @author Cristian Urbainski <cristianurbainskips@gmail.com>
 * @since 02/10/2014
 * @version 1.0
 *
 */
public class PopulaBancoDadosMain {
	
	/**
	 * Método main responsável por executar o programa.
	 * 
	 * @param args - argumentos do programa
	 */
	public static void main(String args[]) {
		PopulaBancoDadosMain main = new PopulaBancoDadosMain();
		main.popularBancoDados();
		
		EntityManagerUtil.getDefaultInstance().getEntityManager().close();
		EntityManagerUtil.getDefaultInstance().getEntityManagerFactory().close();
	}

	/**
	 * Método que popular o banco de dados.
	 */
	public void popularBancoDados() {
		EstadoDAO estadoDAO = new EstadoDAO();
		MunicipioDAO municipioDAO = new MunicipioDAO();
		CargoDAO cargoDAO = new CargoDAO();
		ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		GeneroDAO generoDAO = new GeneroDAO();
		TipolocacoesDAO tipolocacoesDAO = new TipolocacoesDAO();
		TipomidiaDAO tipomidiaDAO = new TipomidiaDAO();
		MidiaDAO midiaDAO = new MidiaDAO();
		LocacaoDAO locacaoDAO = new LocacaoDAO();
		
		popularEstados(estadoDAO);
		popularMunicipios(municipioDAO, estadoDAO);
		popularCargos(cargoDAO);
		popularColaborador(colaboradorDAO, cargoDAO, municipioDAO);
		popularCliente(clienteDAO, municipioDAO);
		popularGenero(generoDAO);
		popularTipolocacoes(tipolocacoesDAO);
		popularTipomidia(tipomidiaDAO);
		popularMidia(midiaDAO, generoDAO, tipolocacoesDAO, tipomidiaDAO);
		popularLocacao(locacaoDAO, clienteDAO, colaboradorDAO, midiaDAO);
	}

	/**
	 * Método que popular a tabela de locacao.
	 * 
	 * @param locacaoDAO - dao do locacao
	 * @param clienteDAO - dao do cliente
	 * @param colaboradorDAO - dao do colaborador
	 * @param midiaDAO - dao do midia
	 */
	private void popularLocacao(LocacaoDAO locacaoDAO, ClienteDAO clienteDAO,
			ColaboradorDAO colaboradorDAO, MidiaDAO midiaDAO) {
		
		locacaoDAO.begin();
		
		List<Midia> listMidia = midiaDAO.findAll();
		List<Cliente> listCliente = clienteDAO.findAll();
		List<Colaborador> listColaborador = colaboradorDAO.findAll();
		
		Random random = new Random();
		
		try {
			for (int i = 0; i < 50; i++) {
				Calendar c = Calendar.getInstance();
				
				Locacao locacao = new Locacao();
				locacao.setCliente(listCliente.get(random.nextInt(listCliente.size())));
				locacao.setColaborador(listColaborador.get(random.nextInt(listColaborador.size())));
				locacao.setDtRetirada(c.getTime());
				
				c.add(Calendar.DAY_OF_YEAR, 3);
				
				locacao.setDtPrevistaentrega(c.getTime());
				locacao.setStLocacao((random.nextInt(2) == 0) 
						? SituacaoLocacao.PAGAR_DEVOLUCAO : SituacaoLocacao.PAGO_LOCACAO);
				
				List<Locacaomidia> listLocacaomidia = new ArrayList<Locacaomidia>();
				int qtMidia = random.nextInt(3);
				if (qtMidia == 0) {
					qtMidia = 1;
				}
				
				double total = 0;
				
				for (int j = 0; j < qtMidia; j++) {
					Midia midia = listMidia.get(random.nextInt(listMidia.size()));
					while (!(SituacaoMidia.DISPONIVEL.equals(midia.getStMidia()))) {
						midia = listMidia.get(random.nextInt(listMidia.size()));
					}
					
					midia.setStMidia(SituacaoMidia.LOCADO);
					
					Locacaomidia locacaomidia = new Locacaomidia();
					locacaomidia.setLocacao(locacao);
					locacaomidia.setMidia(midia);
					locacaomidia.setNrValor(midia.getNrValorlocacao());
					
					listLocacaomidia.add(locacaomidia);
					
					total += midia.getNrValorlocacao() == null 
						? midia.getTipolocacoes().getNrValorlocacao() : midia.getNrValorlocacao();
				}
				
				locacao.setNrTotal(total);
				
				locacaoDAO.save(locacao);
			}
			
			locacaoDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que popular a tabela de midia.
	 * 
	 * @param midiaDAO - dao do midia
	 */
	private void popularMidia(MidiaDAO midiaDAO, GeneroDAO generoDAO,
			TipolocacoesDAO tipolocacoesDAO, TipomidiaDAO tipomidiaDAO) {
		midiaDAO.begin();
		
		List<Genero> listGenero = generoDAO.findAll();
		List<Tipolocacoes> listTipolocacoes = tipolocacoesDAO.findAll();
		Tipomidia tipomidia = tipomidiaDAO.findAll().get(0);
		
		Random random = new Random();
		
		try {
			for (int i = 0; i < 400; i++) {
				Midia midia = new Midia();
				midia.setDsMidia("Filme " + i);
				midia.setGenero(listGenero.get(random.nextInt(listGenero.size())));
				midia.setTipolocacoes(listTipolocacoes.get(random.nextInt(listTipolocacoes.size())));
				midia.setTipomidia(tipomidia);
				
				int tipo = random.nextInt(3);
				
				if (SituacaoMidia.DISPONIVEL.ordinal() == tipo) {
					midia.setStMidia(SituacaoMidia.DISPONIVEL);
				} else if (SituacaoMidia.LOCADO.ordinal() == tipo) {
					midia.setStMidia(SituacaoMidia.LOCADO);
				} else if (SituacaoMidia.DESCARTADO.ordinal() == tipo) {
					midia.setStMidia(SituacaoMidia.DESCARTADO);
				}
				
				midiaDAO.save(midia);
			}
			
			midiaDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
			midiaDAO.rollback();
		}
	}

	/**
	 * Método que popular a tabela de tipomidia.
	 * 
	 * @param tipomidiaDAO - dao do tipomidia
	 */
	private void popularTipomidia(TipomidiaDAO tipomidiaDAO) {
		tipomidiaDAO.begin();
		
		try {
			Tipomidia tipomidia = new Tipomidia();
			tipomidia.setDsTipomidia("DVD");
			tipomidiaDAO.save(tipomidia);
			
			tipomidiaDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tipomidiaDAO.rollback();
		}
	}

	/**
	 * Método que popular a tabela de tipolocacoes.
	 * 
	 * @param tipolocacoesDAO - dao do tipolocacoes
	 */
	private void popularTipolocacoes(TipolocacoesDAO tipolocacoesDAO) {
		tipolocacoesDAO.begin();
		
		List<String> listTipolocacoes = Arrays.asList("Super-Lançentos", 
				"Lançamentos", "Demais");
		List<Double> listPrecos = Arrays.asList(7.0, 5.0, 3.0);
		
		try {
			for (String s : listTipolocacoes) {
				Tipolocacoes tipolocacoes = new Tipolocacoes();
				tipolocacoes.setDsTipolocacoes(s);
				tipolocacoes.setNrValorlocacao(listPrecos.get(listTipolocacoes.indexOf(s)));
				
				tipolocacoesDAO.save(tipolocacoes);
			}
			
			tipolocacoesDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tipolocacoesDAO.rollback();
		}
	}

	/**
	 * Método que popular a tabela de genero.
	 * 
	 * @param generoDAO - dao do genero
	 */
	private void popularGenero(GeneroDAO generoDAO) {
		generoDAO.begin();
		
		List<String> listGeneros = Arrays.asList("Ação", "Aventura", "Comédia",
				"Romance", "Comédia Romântica", "Adulto", "Infantil");
		
		try {
			for (String s : listGeneros) {
				Genero genero = new Genero();
				genero.setDsGenero(s);
				
				generoDAO.save(genero);
			}
			
			generoDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
			generoDAO.rollback();
		}
	}

	/**
	 * Método que popular a tabela de clientes.
	 * 
	 * @param clienteDAO - dao do cliente 
	 * @param municipioDAO - doa do municipio
	 */
	private void popularCliente(ClienteDAO clienteDAO, MunicipioDAO municipioDAO) {
		
		clienteDAO.begin();
		
		List<String> listRuas = Arrays.asList(
				"Rua Pernanbuco", "Rua Palmas", "Rua Guanabara", "Av Catharina Seger",
				"Rua Presidente", "Rua Galvão", "Rua Fortaleza", "Rua Juiz de Fora",
				"Av Joao de Amaral", "Av Juiz de Fora", "Rua Armadão");
		List<String> listNomes = Arrays.asList("Aberlândio Felix Nascimento",
				"Adelmo de Jesus Santos", "Aderaldo  Santan", "Aldo Rudney Santos", 
				"Alexsandro de Jesus", "Anderson Santos", "Antonio Andrade",
				"Antonio Neto", "Antonio da Silva", "Daniel da Silva Santos", 
				"Danilo Santos", "Demas Freitas Santos", "Edson Cunha da Conceição", 
				"Emisson de Jesus dos Santos", "Fabio Seixas Santos", "Genilson Macedo", 
				"George dos Santos Souza", "George da Silva", "Geovane de Santana",
				"Gustavo Freitas Santos", "Jailson dos Santos", "Jilvan Angelo dos Santos",
				"Joao Cleber de Abreu", "Joao Pedro Doria", "Joelmir Gonzaga",
				"José do Nascimento", "Jose Alves", "Jose Santos Matos",
				"Jose Andre Santos", "José dos Santos");
		List<Municipio> listMunicipio = municipioDAO.findAll();
		
		Random random = new Random();
		
		try {
			for (int i = 0; i < 120; i++) {
				Cliente cliente = new Cliente();
				cliente.setNmPessoa(listNomes.get(random.nextInt(listNomes.size())));
				cliente.setNrTelefone("499156989" + i);
				
				Endereco endereco = new Endereco();
				endereco.setDsEndereco(listRuas.get(random.nextInt(listRuas.size())));
				endereco.setNrEndereco(random.nextInt(9999) + "");
				endereco.setMunicipio(listMunicipio.get(random.nextInt(listMunicipio.size())));
				
				cliente.setEndereco(endereco);
				
				clienteDAO.save(cliente);
			}
			clienteDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
			clienteDAO.rollback();
		}
	}

	/**
	 * Método que popular a tabela de colaboradores.
	 * 
	 * @param colaboradorDAO - dao  do colaborador
	 * @param cargoDAO - dao do cargo
	 * @param municipioDAO - dao do municipio
	 */
	private void popularColaborador(ColaboradorDAO colaboradorDAO, 
			CargoDAO cargoDAO, MunicipioDAO municipioDAO) {
		
		colaboradorDAO.begin();
		
		List<Cargo> listCargo = cargoDAO.findAll();
		List<Municipio> listMunicipio = municipioDAO.findAll();
		
		Random random = new Random();
		
		try {
			for (int i = 0; i < 3; i++) {
				Colaborador colaborador = new Colaborador();
				colaborador.setDtContratacao(new Date());
				colaborador.setNmPessoa("Colaborador " + (i + 1));
				colaborador.setNrTelefone("499925455" + i);
				colaborador.setCargo(listCargo.get(random.nextInt(listCargo.size())));
				
				Endereco endereco = new Endereco();
				endereco.setDsEndereco("Rua Pernambuco");
				endereco.setNrEndereco("167" + i);
				endereco.setMunicipio(listMunicipio.get(random.nextInt(listMunicipio.size())));
				
				colaborador.setEndereco(endereco);
				
				colaboradorDAO.save(colaborador);
			}
			colaboradorDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
			colaboradorDAO.rollback();
		}
	}

	/**
	 * Método que popular a tabela de cargos.
	 * 
	 * @param cargoDAO - dao do cargo
	 */
	private void popularCargos(CargoDAO cargoDAO) {
		
		List<String> list = Arrays.asList("Atendente", "Vaxineiro");
		cargoDAO.begin();
		
		try {
			for (String s : list) {
				Cargo cargo = new Cargo();
				cargo.setDsCargo(s);
				
				cargoDAO.save(cargo);
			}
			
			cargoDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
			cargoDAO.rollback();
		}
	}

	/**
	 * Método que popular a tabela de municipios.
	 * 
	 * @param municipioDAO - dao do municipio
	 * @param estadoDAO - dao do estado
	 */
	private void popularMunicipios(MunicipioDAO municipioDAO, EstadoDAO estadoDAO) {
		
		@SuppressWarnings("serial")
		List<String[]> list = new ArrayList<String[]>() {{
			add(new String[]{"Palma Sola", "SC"});
			add(new String[]{"Francisco Beltrão", "PR"});
			add(new String[]{"Anchieta", "SC"});
			add(new String[]{"Porto Alegre", "RS"});
			add(new String[]{"São Paulo", "SP"});
			add(new String[]{"Dois Visinhos", "PR"});
		}};
		
		municipioDAO.begin();
		
		try {
			for (String[] array : list) {
				Municipio municipio = new Municipio();
				municipio.setDsMunicipio(array[0]);
				municipio.setEstado(estadoDAO.findByUf(array[1]));
				
				municipioDAO.save(municipio);
			}
			
			municipioDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
			municipioDAO.rollback();
		}
	}

	/**
	 * Método que popular a tabela de estados.
	 * 
	 * @param estadoDAO - dao do estado
	 */
	private void popularEstados(EstadoDAO estadoDAO) {

		estadoDAO.begin();
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
			
			estadoDAO.save(estadoParana);
			estadoDAO.save(estadoSc);
			estadoDAO.save(estadoSp);
			estadoDAO.save(estadoRs);
			
			estadoDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
			estadoDAO.rollback();
		}
	}
	
}