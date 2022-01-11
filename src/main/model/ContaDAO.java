package main.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;

public class ContaDAO {
	
	private List<Conta> contas = new ArrayList<>();
	
	
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
		return contas;
	}

}
