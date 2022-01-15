
package main;

import java.time.LocalDate;
import java.util.List;

import main.controler.ContaControler;
import main.controler.DespesaControler;
import main.controler.ReceitaControler;
import main.model.Receita;
import main.view.TelaConta;

public class Main {

	public static void main(String[] args) {
		ContaControler contaControler = new ContaControler();
		ReceitaControler receitaControler = new ReceitaControler();
		DespesaControler despesaControler = new DespesaControler();
		TelaConta telaConta = new TelaConta();
		
//		telaConta.mostrarOpcoes();
//		
		
	contaControler.cadastrar("Itau", "poupanca");

	}

}
