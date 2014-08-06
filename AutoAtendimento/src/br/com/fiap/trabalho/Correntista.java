package br.com.fiap.trabalho;

/**
 * Esta classe possui os atributos basicos de um correntista, sendo eles: cpf, nome, senha, saldo.
 * 
 * Bem como os metodos Getters e Setters
 */

public class Correntista {
	private String cpf;
	private String nome;
	private String senha;
	private double saldo;
	
	/**
	* Construtor completo, geralmente utilizado nas instancias onde o objeto é recuperado do seu arquivo
	* @param cpf String
	* @param nome String
	* @param senha String
	* @param saldo double
	*/
	public Correntista(String cpf, String nome, String senha, double saldo) {
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
		this.saldo = saldo;
	}
	/**
	* Construtor incompleto, geralmente utilizado na criação de um novo correntista, com o valor do saldo inicial = 10000
	* @param cpf String
	* @param nome String
	* @param senha String
	*/
	public Correntista(String cpf, String nome, String senha) {
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
		this.saldo = 10000.00;
	}
	/**
	 * @return String - Cpf do correntista, somente numeros
	 */
	public String getCpf() {
		return cpf;
	}
	/**
	 * @param cpf Somente numeros
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * 
	 * @return Double - Saldo com apenas 2 casa decimais
	 */
	public double getSaldo() {
		return saldo;
	}
	/**
	 * 
	 * @param saldo Apenas 2 casas decimais
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

}
