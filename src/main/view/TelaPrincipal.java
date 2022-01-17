package main.view;

import java.util.Scanner;

public class TelaPrincipal {

	TelaConta telaConta = new TelaConta();
	TelaReceita telaReceita = new TelaReceita();
	TelaDespesa telaDespesa = new TelaDespesa();
	private Scanner sc = new Scanner(System.in);

	public void mostrarOpcoes() {

		while (true) {

			System.out.println("1 - Mostrar opções da Conta. ");
			System.out.println("2 - Mostrar opções da Receita. ");
			System.out.println("3 - Mostrar opções da Despesa. ");
			System.out.println("Digite o número da opção: ");
			int numero = sc.nextInt();

			switch (numero) {
			case 1:
				telaConta.mostrarOpcoes();
				break;
			case 2:
				telaReceita.mostrarOpcoes();
				break;
			case 3:
				telaDespesa.mostrarOpcoes();
				break;

			}
		}

	}

}
