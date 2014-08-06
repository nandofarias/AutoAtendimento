package br.com.fiap.trabalho;
public class OperacoesBancarias {

	private Correntista correntista;
	
	public OperacoesBancarias(Correntista correntista) {
		this.correntista = correntista;
	}

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
				throw new Exception("Erro ao realizar o saque, por favor contate o desenvolvedor");
			} 
		}
	}

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
				throw new Exception("Erro ao realizar o saque, por favor contate o desenvolvedor");
			}
		}

	}

	public void consultaSaldo() {
		System.out.println("Voce possui o saldo no valor de R$ "+String.format("%.2f", correntista.getSaldo()));
	}

	public void consultaExtrato() throws Exception {
		
		System.out.println("Aguarde enquanto processamos seu pedido...");
		System.out.println("--------------------------------------------------------------------------------------------------------");

		try{
			ExtratoDao extratoDao = new ExtratoDao();
			extratoDao.getExtrato(correntista.getCpf());
			consultaSaldo();
		}catch(Exception e){
			throw new Exception("Erro ao consultar extrato, por favor contate o desenvolvedor");
		}
	}
	
}
