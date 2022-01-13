package main.controler;

import java.time.LocalDate;
import java.util.List;

import main.model.Conta;
import main.model.Despesa;
import main.model.DespesaDAO;
import main.model.TipoDespesa;

public class DespesaControler {
	
	private final DespesaDAO despesaDAO = new DespesaDAO();
	
	public void cadastrar(Conta conta, Double valor, LocalDate dataPagamento, LocalDate dataPagamentoEsperado, String tipoDespesa) {
		Despesa despesa = new Despesa();
		despesa.setConta(conta);
		despesa.setValor(valor);
		despesa.setDataPagamento(dataPagamentoEsperado);
		despesa.setDataPagamentoEsperado(dataPagamentoEsperado);
		despesa.setTipoDespesa(TipoDespesa.valueOf(tipoDespesa));
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
	
	
	

}
