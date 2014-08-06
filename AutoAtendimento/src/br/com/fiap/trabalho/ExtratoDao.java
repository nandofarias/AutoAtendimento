package br.com.fiap.trabalho;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class ExtratoDao {
	public void salvar(String cpf, String texto) throws Exception{
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
		out.print(texto+"as "+hora+" do dia "+data);
		out.close();
		fw.close();
		}catch(IOException e){
			throw new Exception("N??o foi possivel realizar esta opera????o");
		}
	}
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
			throw new Exception("Erro ao consultar extrato do usuario");
		}
	}

}
