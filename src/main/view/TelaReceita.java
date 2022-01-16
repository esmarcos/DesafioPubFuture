package main.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import main.controler.ReceitaControler;
import main.model.ObjetoNaoEncontrado;
import main.model.Receita;

public class TelaReceita {

	ReceitaControler receitaControler = new ReceitaControler();
	private Scanner sc = new Scanner(System.in);

	public void mostrarOpcoes() {

		while (true) {
			System.out.println("1 - Cadastrar receita. ");
			System.out.println("2 - Atualizar receita. ");
			System.out.println("3 - Remover receita. ");
			System.out.println("4 - buscar receita. ");
			System.out.println("5 - buscar todas receitas. ");
			System.out.println("6 - Filtrar receitas por periodo. ");
			System.out.println("7 - filtrar receitas por tipo de receita. ");
			System.out.println("8 - Voltar a tela principal. ");
			System.out.println("Digite o número da opção: ");
			int numero = sc.nextInt();

			switch (numero) {
			case 1:
				try {
					Integer idConta = this.pedirNumero("Digite o id da conta: ");
					Double valor = this.pedirValor("Digite o valor da receita: ");
					LocalDate dataRecebimento = this.pedirData("Digite a data: ");
					LocalDate dataRecebimentoEsperado = this.pedirData("Digite a data: ");
					String descricao = this.pedirInformacoes("Digite a descrição: ");
					String tipoReceita = this
							.pedirInformacoes("Digite o tipo de receita(SALARIO |PRESENTE |PREMIO |OUTROS;):");
					receitaControler.cadastrar(idConta, valor, dataRecebimento, dataRecebimentoEsperado, tipoReceita,
							descricao);
					System.out.println("Receita cadastrada com sucesso. ");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				try {
					Integer idReceita = this.pedirNumero("Digite o id da receita que será atualizada: ");
					Integer idContaAtualizada = this.pedirNumero("Digite o id da conta: ");
					Double valorAtualizado = this.pedirValor("Digite o valor da receita: ");
					LocalDate dataRecebimentoAtualizado = this.pedirData("Digite a data: ");
					LocalDate dataRecebimentoEsperadoAtualizado = this.pedirData("Digite a data: ");
					String descricaoAtualizada = this.pedirInformacoes("Digite a descrição: ");
					String tipoReceitaAtualizada = this
							.pedirInformacoes("Digite o tipo de receita(SALARIO |PRESENTE |PREMIO |OUTROS;):");
					receitaControler.atualizar(idReceita, idContaAtualizada, valorAtualizado, dataRecebimentoAtualizado,
							dataRecebimentoEsperadoAtualizado, tipoReceitaAtualizada, descricaoAtualizada);
					System.out.println("Receita atualizada com sucesso. ");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					Integer idReceitaRemover = this.pedirNumero("Digite o id da receita que será removida: ");
					receitaControler.remover(idReceitaRemover);
					System.out.println("Conta removida com sucesso. ");
				} catch (ObjetoNaoEncontrado e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					Integer idReceitaProcurada = this.pedirNumero("Digite o id da receita que procura: ");
					receitaControler.buscar(idReceitaProcurada);
					this.mostrarReceita(idReceitaProcurada);
				} catch (ObjetoNaoEncontrado e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				List<Receita> receitas = receitaControler.buscarTodos();
				this.mostrarReceitas(receitas);
				break;
			case 6:
				LocalDate dataInicial = this.pedirData("Digite a data inicial: ");
				LocalDate dataFinal = this.pedirData("Digite a data final: ");
				List<Receita> receita = receitaControler.filtrarPorPeriodo(dataInicial, dataFinal);
				this.mostrarReceitas(receita);
				break;
			case 7:
				try {
					String tipoReceita = this
							.pedirInformacoes("Digite o tipo de receita(SALARIO |PRESENTE |PREMIO |OUTROS;):");
					List<Receita> receitasPorTipo = receitaControler.filtarPorTipoReceita(tipoReceita);
					this.mostrarReceitas(receitasPorTipo);
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

	public void mostrarReceita(Integer id) {
		Receita receita = receitaControler.buscar(id);
		System.out.println(
				"Id | Data de recebimento | Data de recebimento esperado | Tipo de receita | Descrição | Valor");
		System.out.println("" + receita.getId() + "          " + receita.getDataRecebimento() + "               "
				+ receita.getDataRecebimentoEsperado() + "              " + receita.getTipoReceita() + "         "
				+ receita.getDescricao() + "       " + receita.getValor());
	}

	private void mostrarReceitas(List<Receita> receitas) {
		System.out.println("LISTA DE RECEITAS: ");
		for (Receita receita : receitas) {
			System.out.println("Id: " + receita.getId() + " \n " + "Data de recebimento: "
					+ receita.getDataRecebimento() + " \n " + "Data de recebimento esperado: "
					+ receita.getDataRecebimentoEsperado() + " \n " + "Tipo de receita: " + receita.getTipoReceita()
					+ " \n " + "Valor: " + receita.getValor() + " \n " + "Descrição: " + receita.getDescricao());
			System.out.println();
		}
	}
}
