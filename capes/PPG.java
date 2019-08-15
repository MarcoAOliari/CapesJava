package capes;
import java.util.List;

import producoes.Anal;
import producoes.ArtigoJornalRevista;
import producoes.ArtigoPeriodico;
import producoes.Livro;
import producoes.OutraProducao;
import producoes.PartituraMusical;
import producoes.Producao;
import producoes.Traducao;

import java.util.ArrayList;
import java.util.Collections;

public class PPG implements Comparable<PPG>{
	private List<String> instituicoes = new ArrayList<>();
	private String codigo;
	private String nome;
	private List<Producao> producoes = new ArrayList<>();	
	
	/**
	 * Construtor da classe PPG, que ja adiciona uma instituicao na sua lista de instituicoes (String).
	 * @param instituicao - nome da primeira instituicao lida com o PPG
	 * @param codigo - codigo do PPG
	 * @param nome - nome do PPG
	 */
	public PPG(String instituicao, String codigo, String nome) {
		instituicoes.add(instituicao);
		this.codigo = codigo;
		this.nome = nome;
	}
	
	/**
	 * Retorna o codigo do PPG.
	 * @return String representando o codigo do PPG
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Retorna o nome do PPG.
	 * @return String representando o nome do PPG
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Retorna o numero de producoes de um PPG.
	 * @return Inteiro representando o numero de producoes de um PPG
	 */
	public int getProducoes() {
		return producoes.size();
	}
	
	/**
	 * Retorna a lista com o nome das instituicoes responsaveis pelo PPG.
	 * @return Lista de String com o nome das instituicoes responsaveis pelo PPG
	 */
	public List<String> getInstituicoes(){
		return instituicoes;
	}
	
	/**
	 * Metodo sobrescrito utilizado para ordenar uma lista de PPG (por meio do metodo sort da classe Collections) de acordo
	 * com o codigo.
	 */
	@Override
	public int compareTo(PPG p2) {
		return codigo.compareTo(p2.getCodigo());
	}
	
	/**
	 * Adiciona um novo nome de instituicao na lista de instituicoes (String) do PPG. Caso o nome ja esteja contido na lista,
	 * o metodo retorna.
	 * @param nome - nome da instituicao a ser adicionada
	 */
	public void novaInstituicao(String nome) {
		for(int i=0; i<instituicoes.size(); i++) {
			if(instituicoes.get(i).equals(nome))
				return;
		}
		instituicoes.add(nome);
	}
	
	/**
	 * Adiciona uma nova Producao no PPG.
	 * @param prod - objeto da classe Producao a ser adicionado.
	 */
	public void novaProducao(Producao prod) {
		producoes.add(prod);
	}
	
	/**
	 * Metodo auxiliar para a execucao do comando rede. Retorna verdadeiro caso o PPG tenha mais de uma instituicao responsavel
	 * e falso caso contrario.
	 * @return
	 */
	public boolean rede() {
		if(instituicoes.size()>1)
			return true;
		else
			return false;
	}
	
	/**
	 * Imprime o nome e a sigla das instituicoes (ja ordenadas) responsaveis pelo PPG.
	 */
	public void imprimeInstituicoes() {
		Collections.sort(instituicoes);
		
		for(int i=0; i<instituicoes.size(); i++) {
			String[] split = instituicoes.get(i).split(" ;");
			System.out.printf("\t- %s (%s)\n", split[0], split[1]);
		}
	}
	
	/**
	 * Calcula os dados estatisticos de um PPG especificados no comando PPG do trabalho e os retorna em um vetor. Os indices 
	 * correspondem aos seguintes dados:<br>
	 * - 0: quantidade de producoes da classe Anal
	 * - 1: quantidade de producoes da classe ArtigoJornalRevista
	 * - 2: quantidade de producoes da classe ArtigoPeriodico
	 * - 3: quantidade de producoes da classe Livro
	 * - 4: quantidade de producoes da classe PartituraMusical
	 * - 5: quantidade de producoes da classe Traducao
	 * - 6: quantidade de producoes da classe OutraProducao
	 * - 7: numero de paginas produzidas por um PPG
	 * @return - vetor de inteiros representando os dados estatisticos de um PPG
	 */
	public int[] calculaEstatisticas(){
		int estatisticas[] = new int[8];
		
		for(int i=0; i<producoes.size(); i++) {
			if(producoes.get(i) instanceof Anal)
				estatisticas[0]++;
			if(producoes.get(i) instanceof ArtigoJornalRevista)
				estatisticas[1]++;
			if(producoes.get(i) instanceof ArtigoPeriodico)
				estatisticas[2]++;
			if(producoes.get(i) instanceof Livro) 
				estatisticas[3]++;
			if(producoes.get(i) instanceof PartituraMusical)
				estatisticas[4]++;
			if(producoes.get(i) instanceof Traducao)
				estatisticas[5]++;
			if(producoes.get(i) instanceof OutraProducao)
				estatisticas[6]++;
			estatisticas[7] += producoes.get(i).getPags();
		}
		
		return estatisticas;
		
	}
	
	/**
	 * Imprime producoes de um tipo especifico de um PPG. Primeiramente percorre-se a lista de producoes (Producao) do
	 * PPG e armazena todas as producoes do tipo especificado em uma outra lista. Depois disso, as producoes sao ordenadas
	 * da maneira especificada pelo comando csv. Logo em seguida, as repeticoes de producao sao removidas e a lista eh
	 * impressa.
	 * @param tipo - nome da Classe que representa o tipo de producao a ser impressa
	 */
	public void imprimeProducoes(String tipo) {
		int j = 0;
		
		List<Producao> ordenar = new ArrayList<>();
		
		for(int i=0; i<producoes.size(); i++) {
			try {
				if(Class.forName("producoes." + tipo).isInstance(producoes.get(i)))
					ordenar.add(producoes.get(i));
			} catch(Exception e) {
				continue;
			}
		}
		
		ordenar.get(0).imprimeCabecalho();
		Collections.sort(ordenar);
		
		int tam = ordenar.size();
		
		for(int i=0; i<tam; i++) {
			j = i+1;
			while(j<tam && ordenar.get(i).compareTo(ordenar.get(j))==0) {
				ordenar.remove(j);
				tam--;
			}	
		}
		
		for(j=0; j<ordenar.size(); j++) {
			System.out.println(ordenar.get(j));
		}
		
	}

}
