package producoes;
public class Traducao extends Publicacao{
	private String traducao;
	
	
	/**
	 * Construtor da classe Traducao. Como atributo proprio, apenas traducao, sendo todos os outros extendidos de
	 * Publicacao e Producao.
	 * @param id - codigo identificador da Producao
	 * @param natureza - natureza da Producao
	 * @param titulo - titulo da Producao
	 * @param idioma - idioma em que foi escrita a Producao
	 * @param cidade - cidade em que foi produzida a Producao
	 * @param editora - editora responsavel pela Publicacao
	 * @param traducao - idioma de traducao da Traducao
	 */
	public Traducao(String id, String natureza, String titulo, String idioma, String cidade, String editora, String traducao) {
		super(id, natureza, titulo, idioma, cidade, editora);
		this.traducao = traducao;
	}
	
	/**
	 * Retorna o idioma de traducao da Traducao.
	 * @return String que representa o idioma de traducao da Traducao.
	 */
	public String getTraducao() {
		return traducao;
	}
	
	/**
	 * Metodo sobrescrito para impressao mais simples da classe Traducao. Verifica-se o numero de paginas, caso seja maior que 0
	 * eh considerado na impressao, no contrario nao.
	 */
	@Override
	public String toString() {
		if(npags>0)
			return natureza + ";" + titulo + ";" + idioma + ";" + editora + ";" + cidade + ";" + traducao + ";" + npags;
		else
			return natureza + ";" + titulo + ";" + idioma + ";" + editora + ";" + cidade + ";" + traducao + ";";
	}
	
	/**
	 * Imprime cabecalho da classe Traducao especificado no comando csv.
	 */
	public void imprimeCabecalho() {
		System.out.println("Natureza;Titulo;Idioma;Editora;Cidade;Traducao;Paginas");
	}

	/**
	 * Metodo sobrescrito utilizado para ordenar uma lista de Traducao (por meio do metodo sort da classe Collections) 
	 * da maneira especificada pelo comando csv, realizando as devidas comparacoes.
	 * @param p2 - outro objeto da classe Traducao (extendido de Producao)
	 * @return - Inteiro logico auxiliar para o metodo sort realizar a ordenacao da maneira desejada
	 */
	@Override
	public int compareTo(Producao p2) {
		if(!this.getNatureza().equals(p2.getNatureza()))
			return this.getNatureza().compareTo(p2.getNatureza());
		else if(!this.getTitulo().equals(p2.getTitulo()))
			return this.getTitulo().compareTo(p2.getTitulo());
		else if(!this.getIdioma().equals(p2.getIdioma()))
			return this.getIdioma().compareTo(p2.getIdioma());
		else if(!this.getEditora().equals(((Traducao)p2).getEditora()))
			return this.getEditora().compareTo(((Traducao)p2).getEditora());
		else if(!this.getCidade().equals(p2.getCidade()))
			return this.getCidade().compareTo(p2.getCidade());
		else if(!this.getTraducao().equals(((Traducao)p2).getTraducao()))
			return this.getTraducao().compareTo(((Traducao)p2).getTraducao());
		else if(this.npags != p2.npags)
			return this.npags - p2.npags;
		else
			return 0;
	}
}
