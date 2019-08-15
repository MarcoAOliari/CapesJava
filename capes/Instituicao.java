package capes;
import java.util.ArrayList;
import java.util.List;

import producoes.ComparadorNomePPG;

import java.util.Collections;

public class Instituicao implements Comparable<Instituicao>{
	private String nome;
	private String sigla;
	private List<PPG> ppgs = new ArrayList<>();
	
	/**
	 * Construtor da classe Instituicao, atribuindo um nome e uma sigla.
	 * @param nome - nome da instituicao
	 * @param sigla - sigla da instituicao
	 */
	public Instituicao(String nome, String sigla){
		this.nome = nome;	this.sigla = sigla;
	}
	
	/**
	 * Retorna a sigla de um objeto Instituicao.
	 * @return String representando a sigla da Instituicao
	 */
	public String getSigla() {
		return this.sigla;
	}
	
	/**
	 * Retorna o nome de um objeto Instituicao.
	 * @return String representando o nome da Instituicao
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Adiciona um PPG na lista de ppgs da Instituicao.
	 * @param ppg - objeto do tipo PPG com seus atributos ja armazenados
	 */
	public void adicionaPpg(PPG ppg) {
		ppgs.add(ppg);
	}
	
	/**
	 * Metodo sobrescrito para organizar uma lista de Instituicoes por ordem alfabetica de acordo com o nome.
	 */
	@Override
	public int compareTo(Instituicao i2) {
		return this.getNome().compareTo(i2.getNome());
	}
	
	/**
	 * Imprime a lista de ppgs contida em uma Instituicao, ja ordenada da maneira especificada.
	 */
	public void imprimePpgs() {
		Collections.sort(ppgs, new ComparadorNomePPG());
		for(int i = 0; i<ppgs.size(); i++) {
			System.out.printf("\t- %s: %d producoes\n", ppgs.get(i).getNome(), ppgs.get(i).getProducoes());
		}
	}
	
}
