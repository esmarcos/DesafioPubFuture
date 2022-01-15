package main.controler;

import java.time.LocalDate;
import java.util.List;

import main.model.Conta;
import main.model.Despesa;
import main.model.DespesaDAO;
import main.model.TipoDespesa;

public class DespesaControler {
	
	ContaControler contaControler = new ContaControler();
	private final DespesaDAO despesaDAO = new DespesaDAO();
	
	public void cadastrar(Integer idConta, Double valor, LocalDate dataPagamento, LocalDate dataPagamentoEsperado, String tipoDespesa) {
		Despesa despesa = new Despesa();
		Conta conta = contaControler.busacar(idConta);
		despesa.setConta(conta);
		despesa.setValor(valor);
		despesa.setDataPagamento(dataPagamentoEsperado);
		despesa.setDataPagamentoEsperado(dataPagamentoEsperado);
		despesa.setTipoDespesa(TipoDespesa.valueOf(tipoDespesa));
		contaControler.atualizarSaldoDespesa(conta, despesa);
		despesaDAO.salvar(despesa);
	}
	
	public void atualizar(Integer id,Conta conta, Double valor, LocalDate dataPagamento, LocalDate dataPagamentoEsperado, String tipoDespesa) {
		Despesa despesa = new Despesa();
		despesa.setId(id);
		despesa.setConta(conta);
		despesa.setValor(valor);
		despesa.setDataPagamento(dataPagamentoEsperado);
		despesa.setDataPagamentoEsperado(dataPagamentoEsperado);
		despesa.setTipoDespesa(TipoDespesa.valueOf(tipoDespesa));
		
		despesaDAO.atualizar(despesa);
	}
	
	public void remover(Integer id) {
		despesaDAO.remover(id);
	}
	
	public Despesa buscar(Integer id) {
		return despesaDAO.buscar(id);
	}
	
	public List<Despesa> buscarTodas() {
		return despesaDAO.buscarTodos();
	}
	
	public List<Despesa> filtrarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal){
		return despesaDAO.filtrarPorPeriodo(dataInicial, dataFinal);
	}
	
	

}
