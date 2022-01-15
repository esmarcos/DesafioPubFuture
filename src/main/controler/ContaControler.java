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

	public void cadastrar(String instituicaoFinanceira, String tipoDeConta) {
		Conta conta = new Conta();
		conta.setInstituicaoFinanceira(instituicaoFinanceira);
		conta.setTipoDeConta(TipoDeConta.valueOf(tipoDeConta.toUpperCase()));
		contaDAO.salvar(conta);

	}

	

	public void atualizar(Integer id, String instituicaoFinanceira, String tipoDeConta) {
		Conta conta = new Conta();
		conta.setId(id);
		conta.setInstituicaoFinanceira(instituicaoFinanceira);
		conta.setTipoDeConta(TipoDeConta.valueOf(tipoDeConta));
		contaDAO.atualizar(conta);
	}

	public Conta busacar(Integer id) {
		return contaDAO.buscar(id);
	}

	public List<Conta> buscartodos() {
		return contaDAO.buscarTodos();
	}

	public void remover(Integer id) {
		contaDAO.remover(id);
	}

	public void atualizarSaldoReceita(Conta conta, Receita receita) {
		if (conta == null) {
			throw new ObjetoNaoEncontrado("Conta não encontrada. ");
		}

		Double saldoAtualizado = conta.getSaldo() + receita.getValor();
		conta.setSaldo(saldoAtualizado);
		contaDAO.atualizar(conta);

	}

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

	public void atualizarSaldoDespesa(Conta conta, Despesa despesa) {
		if (conta == null) {
			throw new ObjetoNaoEncontrado("Conta não encontrada. ");
		}

		Double saldoAtualizado = conta.getSaldo() - despesa.getValor();
		conta.setSaldo(saldoAtualizado);
		contaDAO.atualizar(conta);

	}

	public Double consultarSaldo(Integer id) {
		Conta conta = contaDAO.buscar(id);
		Double saldo = conta.getSaldo();
		return saldo;
	}

}
