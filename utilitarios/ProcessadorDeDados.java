package utilitarios;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import capes.Instituicao;
import capes.PPG;
import producoes.Producao;

import java.util.HashMap;
import java.util.Locale;
import java.util.Collections;

public class ProcessadorDeDados {
	private List<String> producoes = new ArrayList<>();
	private Map<String, Instituicao> instituicoes = new HashMap<>();
	private Map<String, PPG> ppgs = new HashMap<>();
	private int contadorpagsvalidas = 0;
	private int pags = 0;
	
	/**
	 * Adiciona uma instituicao na tabela Hash de instituicoes (classe Instituicao).<br><br>
	 * A chave de busca consiste na concatenacao entre nome e sigla, para torna-la menos repetivel.<br><br>
	 * @param nome - nome completo da instituicao
	 * @param sigla - sigla da instituicao
	 */
	public void adicionaInstituicao(String nome, String sigla) {
		instituicoes.put(nome + sigla, new Instituicao(nome, sigla));
	}
	
	/**
	 * Adiciona um novo ppg na tabela Hash de ppgs (classe PPG). Alem disso, caso o ppg ja esteja inserido na tabela,
	 * adiciona uma nova instituicao na lista de instituicoes do PPG (String).<br><br>
	 * Para preencher essa lista, envia-se uma string concatenando a sigla + " ;" + instituicao. A string central
	 * foi escolhida de maneira estrategica para auxiliar na organizacao por ordem alfabetica dos dados.
	 * Ja para a tabela do proprio processador, a chave de busca utilizada foi o codigo fornecido pelo arquivo csv de entrada.
	 * @param codigo - codigo do ppg fornecido pelo arquivo csv
	 * @param nome - nome do ppg a ser adicionado
	 * @param instituicao - nome completo da instituicao responsavel pelo ppg
	 * @param sigla - sigla da intituicao responsavel pelo ppg
	 */
	public void adicionaPPG(String codigo, String nome, String instituicao, String sigla) {
		if(ppgs.containsKey(codigo)) {
			ppgs.get(codigo).novaInstituicao(sigla+" ;"+instituicao);
			return;
		}
		ppgs.put(codigo, new PPG(sigla+" ;"+instituicao, codigo, nome));
	}
	
	/**
	 * Adiciona uma nova producao na lista de producoes (String) do processador de dados para realizar a contagem. 
	 * @param nome - nome da producao
	 */
	public void adicionaProducoesEstatistica(String nome) {
		this.producoes.add(nome);
	}
	
	/**
	 * Adiciona uma producao (classe Producao) no seu respectivo ppg, encontrado pelo codigo fornecido pelo arquivo csv que tambem funciona como chave.<br><br>
	 * As repeticoes de producao sao tratadas para a parte 5 posteriomente.
	 * @param codigoPPG - codigo de identificacao do ppg responsavel pela producao
	 * @param prod - objeto da classe producao ja especificado com seus atributos
	 */
	public void adicionaProducao(String codigoPPG, Producao prod) {
		ppgs.get(codigoPPG).novaProducao(prod);
	}
	
	/**
	 * Faz a contabilidade das paginas de uma certa producao. Os valores sao lidos do arquivo como string, portanto eles tentam 
	 * ser convertidos para inteiro dentro desse metodo. Caso nao seja possivel, o erro eh capturado e nao sao acrescentadas paginas na contabilidade total.
	 * Caso sejam transformadas com exito, verifica-se se a pagina final eh maior que a inicial e se ambas sao maiores que 0. Se todas essas condicoes 
	 * forem satisfeitas, o numero de paginas eh adicionado ao contador do processador de dados, e o contador de paginas validas, utilizado para calcular
	 * a media posteriormente, eh incrementado em uma unidade.
	 * @param paginicial - String lida do arquivo representando a pagina inicial da producao
	 * @param pagfinal - String lida do arquivo representando a pagina final da producao
	 */
	public void calculaQtdPags(String paginicial, String pagfinal) {
		try {
			int pi = Integer.parseInt(paginicial);
			int pf = Integer.parseInt(pagfinal);
			if(pf >= pi && pf >= 0 && pi >= 0) {
				if(pf - pi <= 2000) {
					pags += pf - pi + 1;
					contadorpagsvalidas++;
				}
			}
		}catch(NumberFormatException e) {
			pags += 0;
		}
	}
	
	/**
	 * Percorre a tabela Hash de ppgs (classe PPG), preenchendo cada instituicao da tabela Hash de instituicoes (classe Instituicao) com suas
	 * respectivas ppgs responsaveis.<br><br>
	 * A chave de busca da tabela de instituicoes corresponde a concatenacao nomeInstituicao + siglaInstituicao, enquanto as instituicoes sao
	 * guardadas dentro de cada ppg como sigla + " ;" + nome. Assim, realiza-se o split do nome de cada instituicao da lista de strings do ppg 
	 * para separarmos o nome e a sigla, e a utilizarmos como chave para buscar a instituicao e adicionar o ppg na lista de ppgs contida
	 * em cada Instituicao.
	 */
	public void preencheInstituicoes() {
		for(PPG p : ppgs.values()) {
			List<String> inst = p.getInstituicoes();
			for(int i=0; i<inst.size(); i++) {
				String[] siglanome = inst.get(i).split(" ;");
				instituicoes.get(siglanome[1] + siglanome[0]).adicionaPpg(p);
			}
		}
	}
	
	/**
	 * Realiza a impressao dos dados estatisticos solicitados na primeira parte do trabalho.
	 */
	public void imprimeEstatisticas() {
		Locale l = new Locale("pt", "BR");
		System.out.println("Instituicoes que publicaram em anais: " + this.instituicoes.size());
		System.out.println("PPGs que publicaram em anais: " + ppgs.size());
		System.out.println("Quantidade de producoes em anais: " + this.producoes.size());
		System.out.println("Quantidade de paginas publicadas em anais: " + this.pags);
		System.out.printf(l, "Media de paginas por publicacao: %.1f", (double)this.pags/(double)this.contadorpagsvalidas);
	}
	
	/**
	 * Imprime todos os ppgs que sao administrados por mais de uma instituicao.<br><br>
	 * Percorre-se toda a tabela de ppgs verificando a presenca ou nao de mais de uma instituicao em cada ppg. Depois da lista
	 * ja completa, a lista eh ordenada de acordo com o codigo de cada ppg e logo depois sao impressos os ppgs em ordem, junto
	 * com suas instituicoes (ordenadas por sigla e por nome).
	 */
	public void imprimeRede() {
		List<PPG> ordenar = new ArrayList<>();
		
		for(PPG i : ppgs.values()) {
			if(i.rede()) 
				ordenar.add(i);
		}
		
		Collections.sort(ordenar);
		
		System.out.println("Programas em rede:");

		for(int i=0; i<ordenar.size(); i++) {
			System.out.println(ordenar.get(i).getCodigo() + ": " + ordenar.get(i).getNome());
			ordenar.get(i).imprimeInstituicoes();
		}
	}
	
	/**
	 * Imprime um ppg especificada pelo usuario, assim como suas estatisticas de producao (instituicoes responsaveis e
	 * quantidade de producoes de cada tipo). Caso o codigo fornecido nao tenha sido registrado, o metodo imprime uma
	 * mensagem de erro e retorna.
	 * @param codigo - codigo do ppg a ser impresso
	 */
	public void imprimePpg(String codigo) {
		if(!ppgs.containsKey(codigo)) {
			System.out.printf("PPG nao encontrado.");
			return;
		}
		System.out.println("Programa: " + ppgs.get(codigo).getNome());
		System.out.println("Instituicoes:");
		ppgs.get(codigo).imprimeInstituicoes();
		System.out.println();
		System.out.println("Quantidade de producoes por tipo:");
		int[] estatisticas = ppgs.get(codigo).calculaEstatisticas();
		System.out.printf("\t- Artigos em anais de eventos: %d\n", estatisticas[0]);
		System.out.printf("\t- Artigos em jornais e revistas: %d\n", estatisticas[1]);
		System.out.printf("\t- Artigos em periodicos cientificos: %d\n", estatisticas[2]);
		System.out.printf("\t- Livros: %d\n", estatisticas[3]);
		System.out.printf("\t- Partituras musicais: %d\n", estatisticas[4]);
		System.out.printf("\t- Traducoes: %d\n", estatisticas[5]);
		System.out.printf("\t- Outros: %d\n", estatisticas[6]);
		System.out.println();
		System.out.printf("Total de paginas produzidas pelo PPG: %d", estatisticas[7]);
	}
	
	/**
	 * Imprime todos os ppgs de uma instituicao (com a mesma sigla), assim como o numero de producoes de cada ppg. Caso a
	 * instituicao nao tenha sido registrada, imprime uma mensagem de erro e retorna.<br><br>
	 * Primeiramente as instituicoes contidas na tabela Hash sao passadas para uma lista, onde sao ordenadas por nome.
	 * Apos isso, percorre-se a lista por completa a procura de instituicoes com a sigla enviada. Quando encontrada,
	 * imprime o nome da instituicao e suas ppgs com as estatisticas, alem de incrementar uma flag, utilizada para verificar
	 * a presenca ou ausencia da instituicao.
	 * @param sigla - sigla da instituicao procurada
	 */
	public void imprimeIes(String sigla) {
		int flag = 0;
		
		List<Instituicao> ordena = new ArrayList<>();
		
		for(Instituicao i : instituicoes.values()) {
			ordena.add(i);
		}
		
		Collections.sort(ordena);
		
		for(int i=0; i<ordena.size(); i++) {
			 if(ordena.get(i).getSigla().equals(sigla)) {
				 System.out.println(ordena.get(i).getNome() + " (" + ordena.get(i).getSigla() + "):");
				 ordena.get(i).imprimePpgs();
				 flag++;
			 }
		}
		
		if(flag==0)
			System.out.println("IES nao encontrada.");
	}
	
	/**
	 * Imprime todas as producoes de um certo tipo de um ppg. Caso o ppg nao esteja cadastrado ou o tipo seja invalido,
	 * o metodo retorna.<br><br>
	 * Depois de encontrado o ppg, verifica-se o tipo de producao pedido pelo usuario e as imprime de maneira ordenada
	 * pre estabelecida.
	 * @param codigo - codigo do ppg a ser pesquisado
	 * @param tipo - tipo de producao a ser impressa de um ppg
	 */
	public void imprimeCsv(String codigo, String tipo) { 
		if(!ppgs.containsKey(codigo)) {
			System.out.printf("PPG nao encontrado.");
			return;
		}
		
		PPG ppg = ppgs.get(codigo);
		
		if(tipo.equals("anais")) 
			ppg.imprimeProducoes("Anal");
		else if(tipo.equals("artjr")) 
			ppg.imprimeProducoes("ArtigoJornalRevista");
		else if(tipo.equals("artpe")) 
			ppg.imprimeProducoes("ArtigoPeriodico");
		else if(tipo.equals("livro"))
			ppg.imprimeProducoes("Livro");
		else if(tipo.equals("partmu")) 
			ppg.imprimeProducoes("PartituraMusical");
		else if(tipo.equals("tradu")) 
			ppg.imprimeProducoes("Traducao");
		else if(tipo.equals("outro")) 
			ppg.imprimeProducoes("OutraProducao");
		else
			System.out.println("Tipo invalido.");
		
	}
}

