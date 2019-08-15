/** 
 * T1_2019_1_Etapa04
 * Criado em Primeiro de Junho de 2019
 * @author Marco Antonio Milaneze Oliari <marcoaoliari@gmail.com>
 */


import java.util.Scanner;

import utilitarios.LeitorCSV;
import utilitarios.ProcessadorDeDados;

public class T1_2019_1_Etapa04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProcessadorDeDados processador = new ProcessadorDeDados();
		String endereco = sc.nextLine();
		
		try {
			for(int i=0; i<7; i++) {
				String arquivo = sc.nextLine();
				LeitorCSV leitor = new LeitorCSV(endereco+arquivo);
				leitor.leCSV(processador);
			}
			String comando = sc.next().trim();
			
			if(comando.equals("ies")) {
				String aux = sc.next().trim();
				processador.preencheInstituicoes();
				processador.imprimeIes(aux);
			}
			else if(comando.equals("rede")) {
				processador.imprimeRede();
			}
			else if(comando.equals("ppg")) {
				String aux = sc.next().trim();
				processador.imprimePpg(aux);
			}
			else if(comando.equals("csv")) {
				String aux = sc.next().trim();
				String tipo = sc.nextLine().trim();
				processador.imprimeCsv(aux, tipo);
			}
			else {
				System.out.println("Comando desconhecido.");
			}
			//processador.imprimeEstatisticas();
		}catch(Exception ioe) {
			System.out.println("Erro de I/O" /*+ ioe.getMessage()*/);
			//ioe.printStackTrace();
		}
		
		sc.close();
		
	}
}