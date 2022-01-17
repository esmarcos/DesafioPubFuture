package main.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import main.controler.DespesaControler;
import main.model.Despesa;
import main.model.ObjetoNaoEncontrado;

public class TelaDespesa {

	DespesaControler despesaControler = new DespesaControler();
	private Scanner sc = new Scanner(System.in);

	public void mostrarOpcoes() {

		while (true) {
			System.out.println("1 - Cadastrar despesa. ");
			System.out.println("2 - Atualizar despesa. ");
			System.out.println("3 - Remover despesa. ");
			System.out.println("4 - buscar despesa. ");
			System.out.println("5 - buscar todas despesas. ");
			System.out.println("6 - Filtrar despesas por periodo. ");
			System.out.println("7 - filtrar despesas por tipo de despesa. ");
			System.out.println("8 - Voltar a tela principal. ");
			System.out.println("Digite o número da opção: ");
			int numero = sc.nextInt();

			switch (numero) {
			case 1:
				try {
					Integer idConta = this.pedirNumero("Digite o id da conta: ");
					Double valor = this.pedirValor("Digite o valor da despesa: ");
					LocalDate dataPagamento = this.pedirData("Digite a data: ");
					LocalDate dataPagamentoEsperado = this.pedirData("Digite a data: ");
					String tipoDespesa = this.pedirInformacoes(
							"Digite o tipo de despesa(ALIMENTACAO |EDUCACAO |LAZER |MORADIA |ROUPA |SAUDE |TRANSPORTE |OUTROS;):");
					despesaControler.cadastrar(idConta, valor, dataPagamento, dataPagamentoEsperado, tipoDespesa);
					System.out.println("Despesa cadastrada com sucesso. ");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					Integer idDespesa = this.pedirNumero("Digite o id da despesa que será atualizada: ");
					Integer idContaAtualizada = this.pedirNumero("Digite o id da conta: ");
					Double valorAtualizado = this.pedirValor("Digite o valor da depesa: ");
					LocalDate dataPagamentoAtualizado = this.pedirData("Digite a data de pagamento: ");
					LocalDate dataPagamentoEsperadoAtualizado = this.pedirData("Digite a data de pagamento esperado: ");
					String tipoDespesaAtualizada = this.pedirInformacoes(
							"Digite o tipo de despesa(ALIMENTACAO |EDUCACAO |LAZER |MORADIA |ROUPA |SAUDE |TRANSPORTE |OUTROS;):");
					despesaControler.atualizar(idDespesa, idContaAtualizada, valorAtualizado, dataPagamentoAtualizado,
							dataPagamentoEsperadoAtualizado, tipoDespesaAtualizada);
					System.out.println("Despesa atualizada com sucesso. ");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					Integer idDespesaRemover = this.pedirNumero("Digite o id da despesa que será removida: ");
					despesaControler.remover(idDespesaRemover);
					System.out.println("Despesa removida com sucesso. ");
				} catch (ObjetoNaoEncontrado e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					Integer idDespesaProcurada = this.pedirNumero("Digite o id da despesa que procura: ");
					despesaControler.buscar(idDespesaProcurada);
					this.mostrarDespesa(idDespesaProcurada);
				} catch (ObjetoNaoEncontrado e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				List<Despesa> despesas = despesaControler.buscarTodas();
				this.mostrarDespesas(despesas);
				break;
			case 6:
				LocalDate dataInicial = this.pedirData("Digite a data inicial: ");
				LocalDate dataFinal = this.pedirData("Digite a data final: ");
				List<Despesa> despesaPeriodo = despesaControler.filtrarPorPeriodo(dataInicial, dataFinal);
				this.mostrarDespesas(despesaPeriodo);
				break;
			case 7:
				try {
					String tipoDespesa = this.pedirInformacoes(
							"Digite o tipo de Despesa(ALIMENTACAO |EDUCACAO |LAZER |MORADIA |ROUPA |SAUDE |TRANSPORTE |OUTROS;):");
					List<Despesa> despesasPorTipo = despesaControler.filtrarPorTipoDespesa(tipoDespesa);
					this.mostrarDespesas(despesasPorTipo);
				} catch (ObjetoNaoEncontrado e) {
					System.out.println(e.getMessage());
				}

			}
			if (numero == 8) {
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

	private Double pedirValor(String pergunta) {
		System.out.println(pergunta);
		Double valor = sc.nextDouble();
		return valor;
	}

	public LocalDate pedirData(String pergunta) {
		System.out.println(pergunta);
		System.out.print("dia: ");
		int dia = sc.nextInt();
		System.out.print("mês: ");
		int mes = sc.nextInt();
		System.out.print("ano: ");
		int ano = sc.nextInt();
		LocalDate data = LocalDate.of(ano, mes, dia);
		return data;

	}

	public void mostrarDespesa(Integer id) {
		Despesa despesa = despesaControler.buscar(id);
		System.out.println("Id | Data de pagamento | Data de pagamento esperado | Tipo de despesa | Valor");
		System.out.println("" + despesa.getId() + "        " + despesa.getDataPagamento() + "             "
				+ despesa.getDataPagamentoEsperado() + "              " + despesa.getTipoDespesa() + "         "
				+ despesa.getValor());
		System.out.println();
	}

	private void mostrarDespesas(List<Despesa> despesas) {
		System.out.println("LISTA DE DESPESAS: ");
		for (Despesa despesa : despesas) {
			System.out.println("Id: " + despesa.getId() + " \n " + "Data de pagamento: " + despesa.getDataPagamento()
					+ " \n " + "Data de pagamento esperado: " + despesa.getDataPagamentoEsperado() + " \n "
					+ "Tipo de despesa: " + despesa.getTipoDespesa() + " \n " + "Valor: " + despesa.getValor());
			System.out.println();
		}
	}
}
