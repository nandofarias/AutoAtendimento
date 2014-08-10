package br.com.fiap.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.fiap.controller.OperacoesBancarias;
import br.com.fiap.dao.CorrentistaDao;
import br.com.fiap.model.Correntista;

/**
 * Classe responsavel pelo funcionamento do sistema
 *
 */
public class Main {
	/**
	 * Este metodo tenta realizar a conversao de uma String em Double, para verificar se o valor é um numero valido
	 * @param valor String - Valor em String
	 * @return boolean - True se a string possuir valor double, False se for apenas caracteres
	 */
	public static boolean isDouble(String valor){
		try {
			Double.parseDouble(valor);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Valor digitado não é um numero valido");
			return false;
		}
	}
	/**
	 * Metodo responsavel por receber um valor de saque ou deposito e validar esse valor
	 * @return Double - retorna o valor ja formatado como double
	 */
	public static double recebeValor() {

		InputStream is = System.in;
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader stdin = new BufferedReader(isr);
		System.out.print("Digite o valor desejado e pressione <Enter>: ");
		String valor = null;
		try {
			valor = stdin.readLine();
		} catch (IOException e1) {
			System.out
					.println("Erro ao efetuar a leitura do valor. Tente novamente");
			recebeValor();
		}

		// Substitue a virgula pelo ponto
		valor = valor.replace(",", ".");

		// verifica se ??? um valor do tipo double
		if(!isDouble(valor)) return recebeValor();


		// Verifica se o valor nao O
		if (Double.parseDouble(valor) == 0) {
			System.out.println("O valor digitado nao pode ser 0");
			return recebeValor();
		}
		// verifica se o numero de casas apos o ponto e menor que 2
		String[] valorSplit = valor.split("\\.");
		if (valorSplit.length > 1) {
			if (valor.split("\\.")[1].length() > 2) {
				System.out
						.println("O valor digitado nao pode ultrapassar 2 casas decimais apos a virgula");
				return recebeValor();
			} else {
				return Double.parseDouble(valor);
			}
		} else {
			return Double.parseDouble(valor);
		}

	}
/**
 * Metodo responsavel pelo fluxo principal do sistema, chamado de menu
 * @param correntista Correntista - Nao pode ser null
 */
	public static void menu(Correntista correntista) {
		System.out
				.println("--------------------------------------------------------------------------------------------------------");

		try {

			InputStream is = System.in;
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader stdin = new BufferedReader(isr);
			OperacoesBancarias ob = new OperacoesBancarias(correntista);
			System.out
					.println("Para selecionar uma operacao, digite o numero correspondente e aperte <Enter>:");
			System.out.println("(1)Saque\n" + "(2)Deposito\n"
					+ "(3)Consultar Saldo\n" + "(4)Consultar Extrato\n"
					+ "(0)Sair");
			System.out.print("Opcao: ");
			String opcao = stdin.readLine();
			switch (opcao) {
			case ("0"):
				System.out.println("Agradecemos sua preferencia.");
				break;
			case ("1"): {
				double valor = recebeValor();
				ob.saque(valor);
				menu(correntista);
				break;
			}
			case ("2"): {
				double valor = recebeValor();
				ob.deposito(valor);
				menu(correntista);
				break;
			}
			case ("3"):
				ob.consultaSaldo();
				menu(correntista);
				break;
			case ("4"):
				ob.consultaExtrato();
				menu(correntista);
				break;
			default:
				System.out.println("Operacao invalida");
				menu(correntista);
			}

		} catch (NumberFormatException ne) {
			System.out
					.println("O valor escolhido para saque/deposito e invalido");
			menu(correntista);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
/**
 * Metodo de inicializacao do sistema
 * A partir dele é criado ou carregado um correntista para continuar o fluxo do sistema
 * @param args
 */
	public static void main(String[] args) {
		InputStream is = System.in;
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader stdin = new BufferedReader(isr);
		System.out
				.println("--------------------------------------------------------------------------------------------------------");
		try {
			Correntista correntista;
			CorrentistaDao dao = new CorrentistaDao();
			System.out
					.print("Bem vindo ao Banco 5MOB, para melhor atende-lo por favor digite seu cpf e pressione<Enter>: ");
			String cpf = stdin.readLine();
			File file = new File(cpf);
			if (file.exists()) {
				correntista = dao.recuperarDadosDoCliente(cpf);
			} else {
				correntista = dao.cadastrarNovoCliente(cpf);
			}
			if (correntista == null) {
				System.out.println("Por favor tente novamente mais tarde");
			} else {
				menu(correntista);
			}

		} catch (IOException e) {
			System.out
					.println("Desculpe o inconveniente, ocorreu um erro no leitor, por favor contate o desenvolvedor");
		}

	}
}
