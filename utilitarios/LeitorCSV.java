package utilitarios;
import java.io.BufferedReader;
import java.io.FileReader;

import producoes.Anal;
import producoes.ArtigoJornalRevista;
import producoes.ArtigoPeriodico;
import producoes.Livro;
import producoes.OutraProducao;
import producoes.PartituraMusical;
import producoes.Producao;
import producoes.Traducao;

public class LeitorCSV{
	private BufferedReader scanner;
	private String[] linha;
	
	
	/**
	 * Construtor da classe LeitorCSV, intuitivamente utilizada para ler os arquivos de entrada em formato csv
	 * @param arquivo - concatenacao do endereco e no nome do arquivo fornecido pelo usuario
	 * @throws Exception evita o acesso indevido na memoria de um arquivo nao existente
	 */
	public LeitorCSV(String arquivo) throws Exception{
		if(arquivo == null)	throw new Exception();
		this.scanner = new BufferedReader(new FileReader(arquivo));
	}
	
	/**
	 * Metodo utilizado para retornar a posicao do campo especificado pelo usuario, para o encapsulamento correto dos atributos
	 * @param cabecalho - cabecalho do arquivo csv
	 * @param campo - nome do campo correspondente a coluna desejada
	 * @return Inteiro correspondente a posicao do vetor onde pode ser encontrado o campo desejado
	 */
	private int leitorCabecalho(String[] cabecalho, String campo) {
		for(int i=0; i<cabecalho.length; i++) {
			if(cabecalho[i].equals(campo)) {
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * Metodo utilizado para realizar a leitura do arquivo csv fornecido pelo usuario, responsavel por encapsular os dados e levantar estatisticas.<br><br>
	 * Primeiramente o cabecalho eh lido e armazenado, para ser utilizado posteriormente no metodo leitorCabecalho, onde armazenara os atributos nas suas respectivas classes.
	 * O campo de subtipo eh verificado para identificar o tipo de producao a ser criado. Depois de ja atribuido, a classe Producao eh criada e os dados sao tratados por
	 * metodos da classe ProcessadorDeDados, que serao explicitados no proprio arquivo.
	 * @param processador - Processador de dados onde as informacoes serao direcionadas as suas respectivas classes e serao calculadas as estatisticas
	 * @throws Exception prevencao caso o processador enviado seja nulo
	 */
	public void leCSV(ProcessadorDeDados processador) throws Exception{
		if(this.scanner == null) throw new Exception();
		 
		String aux = new String();
		aux = this.scanner.readLine();
		String[] cabecalho = aux.split(";(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))"); 
		
		while((aux = this.scanner.readLine()) != null ) {
			Producao prod = null;
			linha = aux.split(";(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))"); 
			processador.adicionaInstituicao(linha[3].trim(), linha[2].trim());
			
			if(linha[leitorCabecalho(cabecalho, "ID_SUBTIPO_PRODUCAO")].equals("8")) {
				prod = new Anal(linha[0], linha[leitorCabecalho(cabecalho, "DS_NATUREZA")], linha[leitorCabecalho(cabecalho, "NM_TITULO")], linha[leitorCabecalho(cabecalho, "DS_IDIOMA")], 
						linha[leitorCabecalho(cabecalho, "NM_CIDADE")], linha[leitorCabecalho(cabecalho, "DS_EVENTO")]);
				prod.setPags(linha[leitorCabecalho(cabecalho, "NR_PAGINA_FINAL")], linha[leitorCabecalho(cabecalho, "NR_PAGINA_INICIAL")]);
			}
			else if(linha[leitorCabecalho(cabecalho, "ID_SUBTIPO_PRODUCAO")].equals("9")) {
				prod = new ArtigoJornalRevista(linha[0], " ", linha[leitorCabecalho(cabecalho, "NM_TITULO")], linha[leitorCabecalho(cabecalho, "DS_IDIOMA")], 
						linha[leitorCabecalho(cabecalho, "NM_CIDADE")], " ", linha[leitorCabecalho(cabecalho, "DS_ISSN")], linha[leitorCabecalho(cabecalho, "DT_PUBLICACAO")]);
				prod.setPags(linha[leitorCabecalho(cabecalho, "NR_PAGINA_FINAL")], linha[leitorCabecalho(cabecalho, "NR_PAGINA_INICIAL")]);
			}
			else if(linha[leitorCabecalho(cabecalho, "ID_SUBTIPO_PRODUCAO")].equals("10")) {
				prod = new OutraProducao(linha[0], linha[leitorCabecalho(cabecalho, "DS_NATUREZA")], " ", linha[leitorCabecalho(cabecalho, "DS_IDIOMA")], 
						linha[leitorCabecalho(cabecalho, "NM_CIDADE")], linha[leitorCabecalho(cabecalho, "NM_EDITORA")]);
				prod.setPags(linha[leitorCabecalho(cabecalho, "NR_PAGINAS")]);
			}
			else if(linha[leitorCabecalho(cabecalho, "ID_SUBTIPO_PRODUCAO")].equals("21")) {
				prod = new Traducao(linha[0], linha[leitorCabecalho(cabecalho, "DS_NATUREZA")], linha[leitorCabecalho(cabecalho, "NM_TITULO")], linha[leitorCabecalho(cabecalho, "DS_IDIOMA")], 
						linha[leitorCabecalho(cabecalho, "NM_CIDADE")], linha[leitorCabecalho(cabecalho, "NM_EDITORA_TRADUCAO")], linha[leitorCabecalho(cabecalho, "DS_IDIOMA_TRADUCAO")]);
				prod.setPags(linha[leitorCabecalho(cabecalho, "NR_PAGINAS")]);
			}
			else if(linha[leitorCabecalho(cabecalho, "ID_SUBTIPO_PRODUCAO")].equals("25")) {	
				prod = new ArtigoPeriodico(linha[0], linha[leitorCabecalho(cabecalho, "DS_NATUREZA")], linha[leitorCabecalho(cabecalho, "NM_PROGRAMA_IES")], linha[leitorCabecalho(cabecalho, "DS_IDIOMA")], 
						linha[leitorCabecalho(cabecalho, "NM_CIDADE")], linha[leitorCabecalho(cabecalho, "NM_EDITORA")], linha[leitorCabecalho(cabecalho, "DS_ISSN")],
						linha[leitorCabecalho(cabecalho, "NR_VOLUME")], linha[leitorCabecalho(cabecalho, "DS_FASCICULO")], linha[leitorCabecalho(cabecalho, "NR_SERIE")]);
				prod.setPags(linha[leitorCabecalho(cabecalho, "NR_PAGINA_FINAL")], linha[leitorCabecalho(cabecalho, "NR_PAGINA_INICIAL")]);
			}
			else if(linha[leitorCabecalho(cabecalho, "ID_SUBTIPO_PRODUCAO")].equals("26")) {
				prod = new Livro(linha[0], linha[leitorCabecalho(cabecalho, "DS_NATUREZA")], linha[leitorCabecalho(cabecalho, "NM_TITULO")], linha[leitorCabecalho(cabecalho, "DS_IDIOMA")], 
						linha[leitorCabecalho(cabecalho, "NM_CIDADE_PAIS")], linha[leitorCabecalho(cabecalho, "NM_EDITORA")], linha[leitorCabecalho(cabecalho, "DS_ISBN")]);
				prod.setPags(linha[leitorCabecalho(cabecalho, "NR_PAGINAS_CONTRIBUICAO")]);
			}
			else if(linha[leitorCabecalho(cabecalho, "ID_SUBTIPO_PRODUCAO")].equals("28")) {
				prod = new PartituraMusical(linha[0], linha[leitorCabecalho(cabecalho, "DS_NATUREZA")], " ", linha[leitorCabecalho(cabecalho, "DS_IDIOMA")], 
						linha[leitorCabecalho(cabecalho, "NM_CIDADE")], linha[leitorCabecalho(cabecalho, "NM_EDITORA")], linha[leitorCabecalho(cabecalho, "DS_FORMACAO_INSTRUMENTAL")]);
				prod.setPags(linha[leitorCabecalho(cabecalho, "NR_PAGINAS")]);
			}
		
			
			processador.adicionaPPG(linha[0].trim(), linha[1].trim(), linha[3].trim(), linha[2].trim());
			processador.adicionaProducoesEstatistica(linha[5].trim());
			processador.calculaQtdPags(linha[14], linha[13]);
			processador.adicionaProducao(linha[0], prod);
		}
	}
	
	
}
