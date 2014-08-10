package br.com.fiap.dao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Esta classe por manipular o extrato do correntista nos arquivos de texto 
 *
 */

public class ExtratoDao {
	/**
	 * Metodo responsavel por salvar uma transacao no arquivo de extrato 
	 * @param cpf String - Sem pontuacao
	 * @param texto String - Informa a natureza da transacao juntamente com o valor
	 */
	public void salvar(String cpf, String texto){
		try{
			GregorianCalendar gc = new GregorianCalendar();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
			dateFormat.setCalendar(gc); 
			hourFormat.setCalendar(gc);
			String data =  dateFormat.format(gc.getTime());
			String hora = hourFormat.format(gc.getTime());
		FileWriter fw = new FileWriter(cpf + "Extrato", true);
		PrintWriter out = new PrintWriter(fw);
		out.println();
		out.print(texto+" as "+hora+" do dia "+data);
		out.close();
		fw.close();
		}catch(IOException e){
			System.out.println("Nao foi possivel realizar esta operacao");
		}
	}
	/**
	 * Metodo responsavel por recuperar o extrato a partir do arquivo
	 * @param cpf String - Sem pontuacao
	 */
	public void getExtrato(String cpf) throws Exception{
		try{
		FileReader fr = new FileReader(cpf+"Extrato");
		BufferedReader br = new BufferedReader(fr);
		while(br.ready()){
			System.out.println(br.readLine());
		}
		br.close();
		fr.close();
		}catch(IOException e){
			System.out.println("Erro ao consultar extrato do usuario");
		}
	}

}
