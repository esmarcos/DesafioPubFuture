package main.controler;



import java.util.List;

import main.model.Conta;
import main.model.ContaDAO;
import main.model.TipoDeConta;


public class ContaControler {
	
	private final ContaDAO contaDAO = new ContaDAO();
	
	
	
	public void cadastrar(String instituicaoFinanceira, String tipoDeConta ) {
		Conta conta = new Conta();
		conta.setInstituicaoFinanceira(instituicaoFinanceira);
		conta.setTipoDeConta(TipoDeConta.valueOf(tipoDeConta));
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
	
	
	
}
