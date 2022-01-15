package main.controler;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import main.model.Conta;
import main.model.Receita;
import main.model.ReceitaDAO;
import main.model.TipoReceita;

public class ReceitaControler {
	
	private final ReceitaDAO receitaDAO = new ReceitaDAO();
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	ContaControler contaControler = new ContaControler();
	
	public void cadastrar(Integer idConta, Double valor, LocalDate dataRecebimento, LocalDate dataRecebimentoEsperado, String tipoReceita, String descricao ) {
		Receita receita = new Receita();
		Conta conta = contaControler.busacar(idConta);
		receita.setValor(valor);
		receita.setConta(conta);
		receita.setDataRecebimento(dataRecebimento);
		receita.setDataRecebimentoEsperado(dataRecebimentoEsperado);
		receita.setTipoReceita(TipoReceita.valueOf(tipoReceita));
		receita.setDescricao(descricao);
		contaControler.atualizarSaldoReceita(conta, receita);
		receitaDAO.salvar(receita);
		
	}
	
	public void remover(Integer id) {
		receitaDAO.remover(id);
	}
	
	
	public void atualizar(Integer id,Conta conta, Double valor, LocalDate dataRecebimento, LocalDate dataRecebimentoEsperado, String tipoReceita, String descricao ) {
		Receita receita = new Receita();
		receita.setId(id);
		receita.setConta(conta);
		receita.setValor(valor);
		receita.setDataRecebimento(dataRecebimento);
		receita.setDataRecebimentoEsperado(dataRecebimentoEsperado);
		receita.setTipoReceita(TipoReceita.valueOf(tipoReceita));
		receita.setDescricao(descricao);
		receitaDAO.atualizar(receita);
			
	}
	
	public Receita buscar(Integer id) {
		return receitaDAO.buscar(id);
	}
	
	public List<Receita> buscarTodos(){
		return receitaDAO.buscarTodos();
	}
	
	public List<Receita> filtrarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal){
		return receitaDAO.filtrarPorPeriodo(dataInicial, dataFinal);
		
	}
	
	public List<Receita> filtarPorTipoReceita(String tipoReceita){
		return receitaDAO.filtrarPorTipoReceita(tipoReceita);
	
	}
	
	
	
 

		
	

}
