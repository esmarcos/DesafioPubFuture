package main.model;

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
	
	
	
}
