package main.view;

import java.util.List;
import java.util.Scanner;

import main.controler.ContaControler;
import main.model.Conta;
import main.model.ObjetoNaoEncontrado;

public class TelaConta {

	ContaControler contaControler = new ContaControler();
	private Scanner sc = new Scanner(System.in);

	public void mostrarOpcoes() {

		while (true) {

			System.out.println("1 - Cadastrar conta. ");
			System.out.println("2 - Atualizar conta. ");
			System.out.println("3 - Remover conta. ");
			System.out.println("4 - Buscar conta. ");
			System.out.println("5 - Buscar todas contas. ");
			System.out.println("6 - Transferir saldo. ");
			System.out.println("7 - Consultar Saldo");
			System.out.println("Digite o número da opção: ");
			int numero = sc.nextInt();

			switch (numero) {
			case 1:
				String instituicaoFinanceira = this.pedirInformacoes("Digite a instituição financeira: ");
				String tipoConta = this.pedirInformacoes("Digite o tipo de conta: ");
				try {
					contaControler.cadastrar(instituicaoFinanceira, tipoConta);
					System.out.println("Conta cadastrada com sucesso. ");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				System.out.println();
				break;
			case 2:
				Integer idContaAtualizada = this.pedirNumero("Digete o id da Conta a ser atualizada. ");
				String instituicaoFinanceiraAtualizada = this.pedirInformacoes("Digite a instituição financeira: ");
				String tipoContaAtualizada = this.pedirInformacoes("Digite o tipo de conta: ");
				try {
					contaControler.atualizar(idContaAtualizada, instituicaoFinanceiraAtualizada, tipoContaAtualizada);
					System.out.println("Conta atualizada. ");
				} catch (ObjetoNaoEncontrado e) {
					System.out.println("Dados incorretos. ");
				}
				System.out.println();
				break;
			case 3:
				Integer idContaASerRemovida = this.pedirNumero("Digite o id da conta: ");
				try {
					contaControler.remover(idContaASerRemovida);
					System.out.println("Conta removida. ");

				} catch (ObjetoNaoEncontrado e) {
					System.out.println("Conta não encontrada. ");
				}
				System.out.println();
				break;
			case 4:
				Integer idContaProcurada = this.pedirNumero("Digite o id da conta que prcura: ");
				contaControler.busacar(idContaProcurada);
				this.mostrarConta(idContaProcurada);
				System.out.println();
				break;
			case 5:
				List<Conta> contas = contaControler.buscartodos();
				this.mostrarContas(contas);
				break;
			case 6:
				Integer idConta1 = this.pedirNumero("Digite o id da conta que receberá a tranferência: ");
				Integer idConta2 = this.pedirNumero("Digite o id da conta que fará a tranferência: ");
				Double valor = this.pedirValor("Digite o valor da transferência: ");
				contaControler.trasnferenciaSaldo(idConta1, idConta2, valor);
				System.out.println();
				break;
			case 7:
				Integer idContaConsultada = this.pedirNumero("Digite o id da conta: ");
				Double saldo = contaControler.consultarSaldo(idContaConsultada);
				System.out.println(saldo);
				break;
			}

		}

	}

	private Integer pedirNumero(String pergunta) {
		System.out.println(pergunta);
		Integer numeroDaOpcao = sc.nextInt();
		return numeroDaOpcao;

	}

	private String pedirInformacoes(String pergunta) {
		System.out.println(pergunta);
		String informacao = sc.next();
		return informacao;
	}

	private void mostrarConta(Integer id) {
		Conta conta = contaControler.busacar(id);
		System.out.println("Id | Instituição financeira | Saldo | Tipo de Conta");
		System.out.println("" + conta.getId() + "           " + conta.getInstituicaoFinanceira() + "             "
				+ conta.getSaldo() + "     " + conta.getTipoDeConta());
	}

	private Double pedirValor(String pergunta) {
		System.out.println(pergunta);
		Double valor = sc.nextDouble();
		return valor;
	}

	private void mostrarContas(List<Conta> contas) {
		System.out.println("LISTA DE CONTAS: ");
		for (Conta conta : contas) {
			System.out.println("Id: " + conta.getId() + " \n " + "Instituição financeira: "
					+ conta.getInstituicaoFinanceira() + " \n " + "Saldo: " + conta.getSaldo() + " \n "
					+ "Tipo de conta: " + conta.getTipoDeConta());
			System.out.println();
		}

	}

}
