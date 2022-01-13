package main.model;
import java.util.List;

import javax.persistence.EntityManager;

public class ContaDAO {
	
	
	public void salvar(Conta conta) {
		EntityManager em = Conexao.getEntityManager();
		em.persist(conta);
		em.getTransaction().commit();
		em.close();
		

	}
	
	public Conta buscar(Integer id) {
		EntityManager em = Conexao.getEntityManager();
		Conta conta = em.find(Conta.class, id);
		em.close();
		return conta;
	
	}
	
	public List<Conta> buscarTodos() {
		EntityManager em = Conexao.getEntityManager();
		List<Conta> contas = em.createQuery("SELECT c FROM " + Conta.class.getSimpleName() + " c ", Conta.class).getResultList();
		em.close();
		return contas;
	}
	
	public void remover(Integer id) {
		EntityManager em = Conexao.getEntityManager();
		int deletado = em.createQuery("DELETE FROM " + Conta.class.getSimpleName() + " WHERE " +  id + " = id").executeUpdate();
		if (deletado == 0) {
			em.close();
			throw new ObjetoNaoEncontrado("Conta não encontrada. ");
		}
			
		em.getTransaction().commit();
		em.close();
	}
	
	
	public void atualizar(Conta conta) {
		EntityManager em = Conexao.getEntityManager();
		Conta contaASerAtualizada= this.buscar(conta.getId());
		if (contaASerAtualizada == null) {
			em.close();
			throw new ObjetoNaoEncontrado("Conta não encontrada. ");
			
		}
		
		em.merge(conta);
		em.getTransaction().commit();
		em.close();
	}
	
}


