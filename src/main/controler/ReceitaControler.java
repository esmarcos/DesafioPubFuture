package main.controler;

import java.time.LocalDate;

import main.model.Receita;
import main.model.ReceitaDAO;
import main.model.TipoReceita;

public class ReceitaControler {
	
	private final ReceitaDAO receitaDAO = new ReceitaDAO();
	
	public void cadastrar(Double valor, LocalDate dataRecebimento, LocalDate dataRecebimentoEsperado, String tipoReceita ) {
		Receita receita = new Receita();
		receita.setValor(valor);
		receita.setDataRecebimento(dataRecebimento);
		receita.setDataRecebimentoEsperado(dataRecebimentoEsperado);
		receita.setTipoReceita(TipoReceita.valueOf(tipoReceita));
		receitaDAO.salvar(receita);
		
	}

}
