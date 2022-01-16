package main.controler;

import java.util.List;

import main.model.Conta;
import main.model.ContaDAO;
import main.model.Despesa;
import main.model.ObjetoNaoEncontrado;
import main.model.Receita;
import main.model.TipoDeConta;

public class ContaControler {

	private final ContaDAO contaDAO = new ContaDAO();

	/**
	 * Cadastra uma nova conta.
	 * 
	 * @param instituicaoFinanceira
	 * @param tipoDeConta
	 */
	public void cadastrar(String instituicaoFinanceira, String tipoDeConta) {
		Conta conta = new Conta();
		conta.setInstituicaoFinanceira(instituicaoFinanceira);
		try {
			conta.setTipoDeConta(TipoDeConta.valueOf(tipoDeConta.toUpperCase()));
			contaDAO.salvar(conta);
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Tipo de conta não existente. ");
		}

	}

	
	/**
	 * Usa o id para alterar dados de uma conta já existente.
	 * 
	 * @param id
	 * @param instituicaoFinanceira
	 * @param tipoDeConta
	 * @throws IllegalAccessException
	 */
	public void atualizar(Integer id, String instituicaoFinanceira, String tipoDeConta) {
		Double saldo = consultarSaldo(id);
		Conta conta = new Conta();
		try {
			conta.setId(id);
			conta.setInstituicaoFinanceira(instituicaoFinanceira);
			conta.setTipoDeConta(TipoDeConta.valueOf(tipoDeConta.toUpperCase()));
			conta.setSaldo(saldo);
			contaDAO.atualizar(conta);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Tipo de conta inexistentes. ");
		}
	}
	
	/**
	 * Usa o id para encontrar no banco de dados uma conta especifica.
	 * 
	 * @param id
	 * @return Conta.
	 */
	public Conta busacar(Integer id) {
		try{
			return contaDAO.buscar(id);
		}catch(NullPointerException e) {
			throw new NullPointerException("Conta não encontrada. ");
		}
	}

	/**
	 * Busca no banco de dados todas as contas.
	 * 
	 * @return Uma lista com todas contas.
	 */
	public List<Conta> buscartodos() {
		return contaDAO.buscarTodos();
	}

	/**
	 * Usa o id para encontrar uma conta e remove-la.
	 * 
	 * @param id
	 */
	public void remover(Integer id) {
		contaDAO.remover(id);
	}
	
	/**
	 * Adiciona o valor da receita ao saldo.
	 * 
	 * @param conta
	 * @param receita
	 */
	public void atualizarSaldoReceita(Conta conta, Receita receita) {
		if (conta == null) {
			throw new ObjetoNaoEncontrado("Conta não encontrada. ");
		}

		Double saldoAtualizado = conta.getSaldo() + receita.getValor();
		conta.setSaldo(saldoAtualizado);
		contaDAO.atualizar(conta);

	}
	
	/**
	 * Realiza a transferencia de saldo entre as contas sendo a Conta1 a que recebe e a Conta 2 transfere.
	 * 
	 * @param idConta1
	 * @param idConta2
	 * @param valor
	 */
	public void trasnferenciaSaldo(Integer idConta1, Integer idConta2, Double valor) {
		Conta conta1 = this.busacar(idConta1);
		Conta conta2 = this.busacar(idConta2);
		if (conta1 == null || conta2 == null) {
			throw new ObjetoNaoEncontrado("Conta não encontrada. ");
		}
		Double saldoConta1Atualizado = conta1.getSaldo() + valor;
		Double saldoConta2Atualizado = conta2.getSaldo() - valor;
		conta1.setSaldo(saldoConta1Atualizado);
		conta2.setSaldo(saldoConta2Atualizado);
		contaDAO.atualizar(conta1);
		contaDAO.atualizar(conta2);

	}
	
	/**
	 * Subtrai do saldo o valor da despesa.
	 * 
	 * @param conta
	 * @param despesa
	 */
	public void atualizarSaldoDespesa(Conta conta, Despesa despesa) {
		if (conta == null) {
			throw new ObjetoNaoEncontrado("Conta não encontrada. ");
		}

		Double saldoAtualizado = conta.getSaldo() - despesa.getValor();
		conta.setSaldo(saldoAtualizado);
		contaDAO.atualizar(conta);

	}
	
	
	/**
	 * Mostra o saldo da conta.
	 * 
	 * @param id
	 * @return saldo.
	 */
	public Double consultarSaldo(Integer id) {
		Conta conta = contaDAO.buscar(id);
		Double saldo = conta.getSaldo();
		return saldo;
	}
	
	

}
