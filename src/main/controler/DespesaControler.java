package main.controler;

import java.time.LocalDate;
import java.util.List;

import main.model.Conta;
import main.model.Despesa;
import main.model.DespesaDAO;
import main.model.ObjetoNaoEncontrado;
import main.model.TipoDespesa;

public class DespesaControler {
	
	ContaControler contaControler = new ContaControler();
	private final DespesaDAO despesaDAO = new DespesaDAO();
	
	/**
	 * Cadastra uma nova despesa.
	 * 
	 * @param idConta
	 * @param valor
	 * @param dataPagamento
	 * @param dataPagamentoEsperado
	 * @param tipoDespesa
	 */
	public void cadastrar(Integer idConta, Double valor, LocalDate dataPagamento, LocalDate dataPagamentoEsperado, String tipoDespesa) {
		try{
			Despesa despesa = new Despesa();
			Conta conta = contaControler.busacar(idConta);
			despesa.setConta(conta);
			despesa.setValor(valor);
			despesa.setDataPagamento(dataPagamentoEsperado);
			despesa.setDataPagamentoEsperado(dataPagamentoEsperado);
			despesa.setTipoDespesa(TipoDespesa.valueOf(tipoDespesa.toUpperCase()));
			contaControler.atualizarSaldoDespesa(conta, despesa);
			despesaDAO.salvar(despesa);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Tipo de despesa não existente. ");
		}
	}
	
	/**
	 * Atualiza uma despesa já existente.
	 * 
	 * @param id
	 * @param conta
	 * @param valor
	 * @param dataPagamento
	 * @param dataPagamentoEsperado
	 * @param tipoDespesa
	 */
	public void atualizar(Integer id,Integer idConta, Double valor, LocalDate dataPagamento, LocalDate dataPagamentoEsperado, String tipoDespesa) {
		try{
			Despesa despesa = new Despesa();
			Conta conta = contaControler.busacar(idConta);
			despesa.setId(id);
			despesa.setConta(conta);
			despesa.setValor(valor);
			despesa.setDataPagamento(dataPagamentoEsperado);
			despesa.setDataPagamentoEsperado(dataPagamentoEsperado);
			despesa.setTipoDespesa(TipoDespesa.valueOf(tipoDespesa.toUpperCase()));
			despesaDAO.atualizar(despesa);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Tipo de conta e/ou conta não existente. ");
		}
	}
	
	/**
	 *Remove uma conta já existente. 
	 * 
	 * @param id
	 */
	public void remover(Integer id) {
		try{
			despesaDAO.remover(id);
		}catch (ObjetoNaoEncontrado e) {
			throw new ObjetoNaoEncontrado("Despesa não encontrada. ");
		}
	}
	
	/**
	 * Usa o id para encontrar uma conta já existente.
	 * 
	 * @param id
	 * @return Conta.
	 */
	public Despesa buscar(Integer id) {
		try{
			return despesaDAO.buscar(id);
		}catch (ObjetoNaoEncontrado e) {
			throw new ObjetoNaoEncontrado("Despesa não encontrada. ");
		}
	}
	
	/**
	 * Busca todas as despesas.
	 * 
	 * @return Uma lista com todas as despesas.
	 */
	public List<Despesa> buscarTodas() {
		return despesaDAO.buscarTodos();
	}
	
	/**
	 * Filtra de acordo com o periodo estabelecido.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return As despesas encontradas dentro do periodo.
	 */
	public List<Despesa> filtrarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal){
		return despesaDAO.filtrarPorPeriodo(dataInicial, dataFinal);
	}
	
	/**
	 * Filtra de acordo com o tipo estabelecido.
	 * 
	 * @param tipodeDespesa
	 * @return As despesas do tipo estabelecido por paramentro.
	 */
	public List<Despesa> filtrarPorTipoDespesa(String tipodeDespesa){
		try{
			return despesaDAO.filtrarPorTipoDespesa(tipodeDespesa.toUpperCase());
		}catch (ObjetoNaoEncontrado e) {
			throw new ObjetoNaoEncontrado("Tipo de receita não existente. ");
		}
	}
	

}
