package main.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

public class DespesaDAO {

	public void salvar(Despesa despesa) {
		EntityManager em = Conexao.getEntityManager();
		em.persist(despesa);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public Despesa buscar(Integer id) {
		EntityManager em = Conexao.getEntityManager();
		Despesa despesa = em.find(Despesa.class, id);
		em.close();
		return despesa;
	}
	
	public List<Despesa> buscarTodos() {
		EntityManager em = Conexao.getEntityManager();
		List<Despesa> despesas = em.createQuery("SELECT d FROM " + Despesa.class.getSimpleName() + " d ", Despesa.class).getResultList();
		em.close();
		return despesas;
	}
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
	
	public List<Despesa> buscarPorData(LocalDate dataInicial, LocalDate dataFinal){
		EntityManager em = Conexao.getEntityManager();
		List<Despesa> despesa = em.createQuery("SELECT d FROM " + Despesa.class.getSimpleName() + " WHERE  BETWEEN " + dataInicial + " AND " + dataFinal, Despesa.class).getResultList();
		if (despesa.size() == 0);{
			em.close();
			throw new ObjetoNaoEncontrado("Despesa não encontrada. ");
		}
		
		em.close();
		return despesa;
		
	}
	
	

}
