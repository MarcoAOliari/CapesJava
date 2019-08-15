package producoes;
public class OutraProducao extends Publicacao implements Comparable<Producao>{

	/**
	 * Construtor da classe OutraProducao. Todos os atributos sao extendidos de Publicacao e Producao.
	 * @param id - codigo identificador da Producao
	 * @param natureza - natureza de cada Producao
	 * @param titulo - titulo da Producao
	 * @param idioma - idioma em que foi escrita a Producao
	 * @param cidade - cidade em que foi produzida a Producao
	 * @param editora - editora responsavel pela Publicacao
	 */
	public OutraProducao(String id, String natureza, String titulo, String idioma, String cidade, String editora) {
		super(id, natureza, titulo, idioma, cidade, editora);
	}
	
	/**
	 * Metodo sobrescrito para impressao mais simples da classe OutraProducao. Verifica-se o numero de paginas,
	 * que so eh impresso caso seja maior que 0.
	 */
	@Override
	public String toString() {
		if(npags>0)
			return natureza + ";" + idioma + ";" + editora + ";" + cidade + ";" + npags;
		else
			return natureza + ";" + idioma + ";" + editora + ";" + cidade + ";";
	}
	
	/**
	 * Imprime cabecalho da classe OutraProducao especificado no comando csv.
	 */
	public void imprimeCabecalho() {
		System.out.println("Natureza;Idioma;Editora;Cidade;Paginas");
	}

	/**
	 * Metodo sobrescrito utilizado para ordenar uma lista de OutraProducao (por meio do metodo sort da classe Collections) 
	 * da maneira especificada pelo comando csv, realizando as devidas comparacoes.
	 * @param p2 - outro objeto da classe OutraProducao (extendido de Producao)
	 * @return - Inteiro logico auxiliar para o metodo sort realizar a ordenacao da maneira desejada
	 */
	@Override
	public int compareTo(Producao p2) {
		if(!this.getNatureza().equals(p2.getNatureza()))
			return this.getNatureza().compareTo(p2.getNatureza());
		else if(!this.getIdioma().equals(p2.getIdioma()))
			return this.getIdioma().compareTo(p2.getIdioma());
		else if(!this.getEditora().equals(((OutraProducao)p2).getEditora()))
			return this.getEditora().compareTo(((OutraProducao)p2).getEditora());
		else if(!this.getCidade().equals(p2.getCidade()))
			return this.getCidade().compareTo(p2.getCidade());
		else if(this.npags != p2.npags)
			return this.npags - p2.npags;
		else
			return 0;
	}
}
