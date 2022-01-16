package main.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

public class DespesaDAO {

	/**
	 * Salva as alterações da despesa no banco de dados.
	 * 
	 * @param despesa
	 */
	public void salvar(Despesa despesa) {
		EntityManager em = Conexao.getEntityManager();
		em.persist(despesa);
		em.getTransaction().commit();
		em.close();
		
	}
	
	/**
	 * Busca uma despesa no banco de dados.
	 * 
	 * @param id
	 * @return Despesa.
	 */
	public Despesa buscar(Integer id) {
		EntityManager em = Conexao.getEntityManager();
		Despesa despesa = em.find(Despesa.class, id);
		em.close();
		return despesa;
	}
	
	/**
	 * Busca todas as despesas no banco de dados.
	 * 
	 * @return Uma lista de despesas.
	 */
	public List<Despesa> buscarTodos() {
		EntityManager em = Conexao.getEntityManager();
		List<Despesa> despesas = em.createQuery("SELECT d FROM " + Despesa.class.getSimpleName() + " d ", Despesa.class).getResultList();
		em.close();
		return despesas;
	}
	
	/**
	 * Remove uma conta do despesa de dados.
	 * 
	 * @param id
	 */
	public void remover(Integer id) {
		EntityManager em = Conexao.getEntityManager();
		int deletado = em.createQuery("DELETE FROM " + Despesa.class.getSimpleName() + " WHERE " +  id + " = id").executeUpdate();
		if (deletado == 0) {
			em.close();
			throw new ObjetoNaoEncontrado("Despesa não encontrada. ");
		}
			
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Atuliaza alterações na despesa no banco de dados.
	 * 
	 * @param despesa
	 */
	public void atualizar(Despesa despesa ) {
		EntityManager em = Conexao.getEntityManager();
		Despesa despesaASerAtualizada= this.buscar(despesa.getId());
		if (despesaASerAtualizada == null) {
			em.close();
			throw new ObjetoNaoEncontrado("Despesa não encontrada. ");
			
		}
		
		em.merge(despesa);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Confere todas as despesas pra ver se estão no periodo estabelecido por parâmetro.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return Uma lista de despesas.
	 */
	public List<Despesa> filtrarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal){
		EntityManager em = Conexao.getEntityManager();
		List<Despesa> despesa = em.createQuery("SELECT d FROM " + Despesa.class.getSimpleName() + " d WHERE dataPagamento BETWEEN '" + dataInicial + "' AND '" + dataFinal + "'", Despesa.class).getResultList();
		em.close();
		return despesa;
		
	}
	
	/**
	 * Confere todas as despesas pra ver se estão no tipo estabelecido por parâmetro.
	 * 
	 * @param tipoDespesa
	 * @return Uma lista de despesas.
	 */
	public List<Despesa> filtrarPorTipoDespesa(String tipoDespesa){
		EntityManager em = Conexao.getEntityManager();
		List<Despesa> despesa = em.createQuery("SELECT d FROM " + Despesa.class.getSimpleName() + " d WHERE '" + tipoDespesa +  "' = tipoDespesa ", Despesa.class).getResultList();
		em.close();
		return despesa;
	
	}
	
	

}
