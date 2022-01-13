package main.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("banco");

	public static EntityManager getEntityManager() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		return em;
	}

}
