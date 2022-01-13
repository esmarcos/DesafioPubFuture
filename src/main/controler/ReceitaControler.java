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
	
	public void cadastrar(Conta conta, Double valor, LocalDate dataRecebimento, LocalDate dataRecebimentoEsperado, String tipoReceita, String descricao ) {
		Receita receita = new Receita();
		receita.setConta(conta);
		receita.setValor(valor);
		receita.setDataRecebimento(dataRecebimento);
		receita.setDataRecebimentoEsperado(dataRecebimentoEsperado);
		receita.setTipoReceita(TipoReceita.valueOf(tipoReceita));
		receita.setDescricao(descricao);
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
 

		
	

}
