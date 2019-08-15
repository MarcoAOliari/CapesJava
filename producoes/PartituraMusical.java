package producoes;
public class PartituraMusical extends Publicacao{
	private String formacao;
	
	/**
	 * Construtor da classe PartituraMusical. Como atributo proprio, tem-se formacao (que representa a formacao musical),
	 * sendo todos os outros extendidos de Publicacao e Producao.
	 * @param id - codigo identificador da Producao
	 * @param natureza - natureza da Producao
	 * @param titulo - titulo da Producao
	 * @param idioma - idioma em que foi escrita a Producao
	 * @param cidade - cidade em que foi produzida a Producao
	 * @param editora - editora responsavel pela Publicacao
	 * @param formacaomusical - formacao musical do autor da PartituraMusical
	 */
	public PartituraMusical(String id, String natureza, String titulo, String idioma, String cidade, String editora, String formacaomusical) {
		super(id, natureza, titulo, idioma, cidade, editora);
		formacao = formacaomusical;
	}
	
	/**
	 * Retorna a formacao musical do autor da PartituraMusical.
	 * @return String representando a formacao musical do autor da PartituraMusical
	 */
	public String getFormacao() {
		return formacao;
	}
	
	/**
	 * Metodo sobrescrito para impressao mais simples da classe PartituraMusical. Verifica-se o numero de paginas,
	 * que so eh impresso caso seja maior que 0.
	 */
	@Override
	public String toString() {
		if(npags>0)
			return natureza + ";" + editora + ";" + cidade + ";" + formacao + ";" + npags;
		else
			return natureza + ";" + editora + ";" + cidade + ";" + formacao + ";";
	}
	
	/**
	 * Imprime cabecalho da classe PartituraMusical especificado no comando csv.
	 */
	public void imprimeCabecalho() {
		System.out.println("Natureza;Editora;Cidade;Formacao;Paginas");
	}

	
	/**
	 * Metodo sobrescrito utilizado para ordenar uma lista de PartituraMusical (por meio do metodo sort da classe Collections) 
	 * da maneira especificada pelo comando csv, realizando as devidas comparacoes.
	 * @param p2 - outro objeto da classe PartituraMusical (extendido de Producao)
	 * @return - Inteiro logico auxiliar para o metodo sort realizar a ordenacao da maneira desejada
	 */
	@Override
	public int compareTo(Producao p2) {
		if(!this.getNatureza().equals(p2.getNatureza()))
			return this.getNatureza().compareTo(p2.getNatureza());
		else if(!this.getEditora().equals(((PartituraMusical)p2).getEditora()))
			return this.getEditora().compareTo(((PartituraMusical)p2).getEditora());
		else if(!this.getCidade().equals(p2.getCidade()))
			return this.getCidade().compareTo(p2.getCidade());
		else if(!this.getFormacao().equals(((PartituraMusical)p2).getFormacao()))
			return this.getFormacao().compareTo(((PartituraMusical)p2).getFormacao());
		else if(this.npags != p2.npags)
			return this.npags - p2.npags;
		else
			return 0;
	}
}
