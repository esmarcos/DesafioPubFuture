package main.model;

import java.util.List;

import javax.persistence.EntityManager;

public class ReceitaDAO {
	
	public void salvar(Receita receita) {
		EntityManager em = Conexao.getEntityManager();
		em.persist(receita);
		em.getTransaction().commit();
		em.close();
	}
	
	public Receita buscar(Integer id) {
		EntityManager em = Conexao.getEntityManager();
		Receita receita = em.find(Receita.class, id);
		em.close();
		return receita;

	}
	
	public List<Receita> buscarTodos() {
		EntityManager em = Conexao.getEntityManager();
		List<Receita> receitas = em.createQuery("SELECT r FROM " + Receita.class.getSimpleName() + " r ", Receita.class).getResultList();
		em.close();
		return receitas;
	}
	public void remover(Integer id) {
		EntityManager em = Conexao.getEntityManager();
		int deletado = em.createQuery("DELETE FROM " + Receita.class.getSimpleName() + " WHERE " +  id + " = id").executeUpdate();
		if (deletado == 0) {
			em.close();
			throw new ObjetoNaoEncontrado("Receita não encontrada. ");
		}
			
		em.getTransaction().commit();
		em.close();
	}
	
	public void atualizar(Receita receita) {
		EntityManager em = Conexao.getEntityManager();
		Receita receitaASerAtualizada  = this.buscar(receita.getId());
		if (receitaASerAtualizada == null) {
			em.close();
			throw new ObjetoNaoEncontrado("Receita não encontrada. ");
			
		}
		
		em.merge(receita);
		em.getTransaction().commit();
	}
	
	
	
	
}
