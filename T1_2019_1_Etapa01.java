/** 
 * T1_2019_1_Etapa01
 * Criado em Primeiro de Junho de 2019
 * @author Marco Antonio Milaneze Oliari <marcoaoliari@gmail.com>
 */

import java.util.Scanner;

import utilitarios.LeitorCSV;
import utilitarios.ProcessadorDeDados;

public class T1_2019_1_Etapa01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String endereco = sc.nextLine();
		String arquivo = sc.nextLine();
		ProcessadorDeDados processador = new ProcessadorDeDados();
		
		try {
			LeitorCSV leitor = new LeitorCSV(endereco+arquivo);
			leitor.leCSV(processador);
			processador.imprimeEstatisticas();
		}catch(Exception ioe) {
			System.out.println("Erro de I/O"/* + ioe.getMessage()*/);
			//ioe.printStackTrace();
		}
		sc.close();
	}
}
