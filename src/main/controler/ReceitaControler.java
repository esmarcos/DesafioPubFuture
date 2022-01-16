package main.controler;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import main.model.Conta;
import main.model.ObjetoNaoEncontrado;
import main.model.Receita;
import main.model.ReceitaDAO;
import main.model.TipoReceita;

public class ReceitaControler {
	
	private final ReceitaDAO receitaDAO = new ReceitaDAO();
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	ContaControler contaControler = new ContaControler();
	
	/**
	 * Cadastra uma nova receita.
	 * 
	 * @param idConta
	 * @param valor
	 * @param dataRecebimento
	 * @param dataRecebimentoEsperado
	 * @param tipoReceita
	 * @param descricao
	 */
	public void cadastrar(Integer idConta, Double valor, LocalDate dataRecebimento, LocalDate dataRecebimentoEsperado, String tipoReceita, String descricao ) {
		Receita receita = new Receita();
		Conta conta = contaControler.busacar(idConta);
		receita.setValor(valor);
		receita.setConta(conta);
		receita.setDataRecebimento(dataRecebimento);
		receita.setDataRecebimentoEsperado(dataRecebimentoEsperado);
		receita.setDescricao(descricao);
		try{
			receita.setTipoReceita(TipoReceita.valueOf(tipoReceita));
			contaControler.atualizarSaldoReceita(conta, receita);
			receitaDAO.salvar(receita);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Tipo de receita e/ou conta n�o existentes ");
		}
		
	}
	
	/**
	 * Remove uma conta j� existente.
	 * 
	 * @param id
	 */
	public void remover(Integer id) {
		try{
			receitaDAO.remover(id);
		}catch(ObjetoNaoEncontrado e) {
			throw new ObjetoNaoEncontrado("Receita n�o encontrada. ");
		}
	}
	
	/**
	 * Atualiza uma receita j� existente.
	 * 
	 * @param id
	 * @param conta
	 * @param valor
	 * @param dataRecebimento
	 * @param dataRecebimentoEsperado
	 * @param tipoReceita
	 * @param descricao
	 */
	public void atualizar(Integer id,Integer idConta, Double valor, LocalDate dataRecebimento, LocalDate dataRecebimentoEsperado, String tipoReceita, String descricao ) {
		Receita receita = new Receita();
		Conta conta = contaControler.busacar(idConta);
		receita.setId(id);
		receita.setConta(conta);
		receita.setValor(valor);
		receita.setDataRecebimento(dataRecebimento);
		receita.setDataRecebimentoEsperado(dataRecebimentoEsperado);
		receita.setDescricao(descricao);
		try{
			receita.setTipoReceita(TipoReceita.valueOf(tipoReceita.toUpperCase()));
			contaControler.atualizarSaldoReceita(conta, receita);
			receitaDAO.atualizar(receita);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Tipo de receita e/ou conta n�o existente. ");
		}
			
	}
	
	/**
	 * Usa o id para encontrar uma receita j� existente.
	 * 
	 * @param id
	 * @return Receita
	 */
	public Receita buscar(Integer id) {
		try{
			return receitaDAO.buscar(id);
		}catch(ObjetoNaoEncontrado e) {
			throw new ObjetoNaoEncontrado("Receita n�o encontrada. ");
		}
	}
	
	/**
	 * Busca todas as receitas.
	 * 
	 * @return Uma lista de receitas. 
	 */
	public List<Receita> buscarTodos(){
		return receitaDAO.buscarTodos();
	}
	
	/**
	 * Filtra de acordo com o periodo estabelecido.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return As receitas localizadas dentro do periodo.
	 */
	public List<Receita> filtrarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal){
		return receitaDAO.filtrarPorPeriodo(dataInicial, dataFinal);
		
	}
	
	/**
	 * Filtra de acordo com tipo da receita.
	 * 
	 * @param tipoReceita
	 * @return As receitas dp tipo estabelecido por par�metro.
	 */
	public List<Receita> filtarPorTipoReceita(String tipoReceita){
		try{
			return receitaDAO.filtrarPorTipoReceita(tipoReceita.toUpperCase());
		}catch (ObjetoNaoEncontrado e) {
			throw new ObjetoNaoEncontrado("Tipo de receita n�o existente. ");
		}
	
	}
	
	
	
 

		
	

}
