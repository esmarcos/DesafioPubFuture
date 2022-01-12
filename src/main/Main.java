
package main;

import org.hibernate.ObjectNotFoundException;

import antlr.collections.List;
import main.controler.ContaControler;
import main.controler.ReceitaControler;
import main.model.Conta;
import main.model.ContaNaoEncontrada;

public class Main {
	
	
	
	public static void main(String[] args) {
	ContaControler contaControler = new ContaControler();
	ReceitaControler receitaControler = new ReceitaControler();
	
	receitaControler.cadastrar(10.0, 2022-01-12, 2022-01-12, "SALARIO");
	}
	
	
	

}
