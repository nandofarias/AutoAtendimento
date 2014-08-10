package br.com.fiap.controller;

import br.com.fiap.dao.CorrentistaDao;
import br.com.fiap.dao.ExtratoDao;
import br.com.fiap.model.Correntista;

/**
 * Classe responsavel por realizar as 4 operacoes principais: Saque, Deposito, Consulta Saldo e Consulta Extrato. Faz parte da camada de controle
 *
 */
public class OperacoesBancarias {

	private Correntista correntista;
	/**
	 * Construtor principal e unico
	 * @param correntista Correntista - É necessario passar a referencia a este objeto para que os metodos funcionem corretamente
	 */
	public OperacoesBancarias(Correntista correntista) {
		this.correntista = correntista;
	}
	/**
	 * Metodo responsavel por efetuar o saque
	 * @param valor Double - Valor precisa ser maior que 0 e menor que o saldo
	 * @throws Exception - Caso o valor não seja valido
	 */
	public void saque(double valor) throws Exception {
		if (valor <= 0) {
			throw new Exception(
					"O valor digitado nao corresponde a uma entrada valida.\nPor favor, certifique-se de digitar valores positivos");
		} else if (correntista.getSaldo() < valor) {
			throw new Exception(
					"Voce nao possui saldo suficiente para efetuar esta operacao");
		} else {
			System.out.println("Aguarde... Estamos processando seu pedido");
			try {
				
				correntista.setSaldo(correntista.getSaldo() - valor);
				CorrentistaDao correntistaDao = new CorrentistaDao();
				correntistaDao.atualizarSaldoCliente(correntista);
				
				ExtratoDao dao = new ExtratoDao();
				dao.salvar(correntista.getCpf() ,"Saque no valor de R$ " + String.format("%.2f", valor));
				
				
				System.out.println("Operacao realizada com sucesso!");
			} catch (Exception e) {
				System.out.println("Erro ao realizar o saque, por favor contate o desenvolvedor");
			} 
		}
	}
	/**
	 * Metodo responsavel por efetuar o deposito
	 * @param valor Double - Valor precisa ser maior que 0
	 * @throws Exception - Caso o valor não seja valido
	 */
	public void deposito(double valor) throws Exception {
		if (valor <= 0) {
			throw new Exception(
					"O valor digitado nao corresponde a uma entrada valida.\nPor favor, certifique-se de digitar valores positivos");
		}  else {
			System.out.println("Aguarde... Estamos processando seu pedido");
			try {
				correntista.setSaldo(correntista.getSaldo() + valor);
				CorrentistaDao correntistaDao = new CorrentistaDao();
				correntistaDao.atualizarSaldoCliente(correntista);
				
				
				ExtratoDao extratoDao = new ExtratoDao();
				extratoDao.salvar(correntista.getCpf(),"Deposito no valor de R$ " + String.format("%.2f", valor));
				
							
				System.out.println("Operacao realizada com sucesso!");
			} catch (Exception e) {
				System.out.println("Erro ao realizar o saque, por favor contate o desenvolvedor");
			}
		}

	}
	/**
	 * Metodo que imprime o valor do saldo
	 */
	public void consultaSaldo() {
		System.out.println("Voce possui o saldo no valor de R$ "+String.format("%.2f", correntista.getSaldo()));
	}
	/**
	 * Metodo responsavel por controlar a chamada do extrato
	 */
	public void consultaExtrato() {
		
		System.out.println("Aguarde enquanto processamos seu pedido...");
		System.out.println("--------------------------------------------------------------------------------------------------------");

		try{
			ExtratoDao extratoDao = new ExtratoDao();
			extratoDao.getExtrato(correntista.getCpf());
			consultaSaldo();
		}catch(Exception e){
			System.out.println("Erro ao consultar extrato, por favor contate o desenvolvedor");
		}
	}
	
}
