package main.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

public class ReceitaDAO {

	/**
	 * Salva as alteracões da receita no banco de dados.
	 * 
	 * @param receita
	 */
	public void salvar(Receita receita) {
		EntityManager em = Conexao.getEntityManager();
		em.persist(receita);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Busca uma receita no banco de dados.
	 * 
	 * @param id
	 * @return Receita.
	 */
	public Receita buscar(Integer id) {
		EntityManager em = Conexao.getEntityManager();
		Receita receita = em.find(Receita.class, id);
		em.close();
		return receita;

	}

	/**
	 * Busca todas as receitas do banco de dados
	 * 
	 * @return Uma lista de receitas
	 */
	public List<Receita> buscarTodos() {
		EntityManager em = Conexao.getEntityManager();
		List<Receita> receitas = em.createQuery("SELECT r FROM " + Receita.class.getSimpleName() + " r ", Receita.class)
				.getResultList();
		em.close();
		return receitas;
	}

	/**
	 * Remove uma conta do banco de dados
	 * 
	 * @param id
	 */
	public void remover(Integer id) {
		EntityManager em = Conexao.getEntityManager();
		int deletado = em.createQuery("DELETE FROM " + Receita.class.getSimpleName() + " WHERE " + id + " = id")
				.executeUpdate();
		if (deletado == 0) {
			em.close();
			throw new ObjetoNaoEncontrado("Receita não encontrada. ");
		}

		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Atualiza a receita no banco de dados.
	 * 
	 * @param receita
	 */
	public void atualizar(Receita receita) {
		EntityManager em = Conexao.getEntityManager();
		Receita receitaASerAtualizada = this.buscar(receita.getId());
		if (receitaASerAtualizada == null) {
			em.close();
			throw new ObjetoNaoEncontrado("Receita não encontrada. ");

		}

		em.merge(receita);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Confere as receitas do banco de dados buscando as que se encaixam no periodo
	 * estabelecido por parâmetro.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return Uma lista de receitas.
	 */
	public List<Receita> filtrarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		EntityManager em = Conexao.getEntityManager();
		List<Receita> receita = em.createQuery("SELECT r FROM " + Receita.class.getSimpleName() + " r WHERE dataRecebimento BETWEEN '"
				+ dataInicial + "' AND '" + dataFinal + "'", Receita.class).getResultList();
		em.close();
		return receita;

	}

	/**
	 * Confere as receitas do banco de dados buscando as que se encaixam no tipo estabelecido por parâmetro.
	 *
	 * @param tipoReceita
	 * @return Uma lista de receitas. 
	 */
	public List<Receita> filtrarPorTipoReceita(String tipoReceita) {
		EntityManager em = Conexao.getEntityManager();
		List<Receita> receita = em.createQuery(
				"SELECT r FROM " + Receita.class.getSimpleName() + " r WHERE '" + tipoReceita + "' = tipoReceita ",
				Receita.class).getResultList();
		em.close();
		return receita;

	}

}
