package producoes;
public class Livro extends Publicacao{
	private String isbn;
	
	/**
	 * Construtor da classe Livro, que extende Publicacao e Producao. Como atributo proprio, apenas o isbn.
	 * @param id - codigo identificador da Producao
	 * @param natureza - natureza da Producao
	 * @param titulo - titulo da Producao
	 * @param idioma - idioma em que foi escrita a Producao
	 * @param cidade - cidade em que foi produzida a Producao
	 * @param editora - editora responsavel pela Publicacao
	 * @param isbn - codigo representando o isbn (International Standard Book Number) do Livro
	 */
	public Livro(String id, String natureza, String titulo, String idioma, String cidade, String editora, String isbn) {
		super(id, natureza, titulo, idioma, cidade, editora);
		this.isbn = isbn;
	}
	
	/**
	 * Retorna o isbn do objeto Livro.
	 * @return String representando o isbn do objeto Livro
	 */
	public String getIsbn() {
		return isbn;
	}
	
	/**
	 * Metodo sobrescrito para impressao mais simples da classe Livro.
	 */
	@Override
	public String toString() {
		return natureza + ";" + titulo + ";" + idioma + ";" + editora + ";" + cidade + ";" + isbn + ";" + npags;
	}
	
	/**
	 * Imprime cabecalho da classe Livro especificado no comando csv.
	 */
	public void imprimeCabecalho() {
		System.out.println("Natureza;Titulo;Idioma;Editora;Cidade;ISBN;Paginas");
	}

	/**
	 * Metodo sobrescrito utilizado para ordenar uma lista de Livro (por meio do metodo sort da classe Collections) 
	 * da maneira especificada pelo comando csv, realizando as devidas comparacoes.
	 * @param p2 - outro objeto da classe Livro (extendido de Producao)
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
		else if(!this.getEditora().equals(((Livro)p2).getEditora()))
			return this.getEditora().compareTo(((Livro)p2).getEditora());
		else if(!this.getCidade().equals(p2.getCidade()))
			return this.getCidade().compareTo(p2.getCidade());
		else if(!this.getIsbn().equals(((Livro)p2).getIsbn()))
			return this.getIsbn().compareTo(((Livro)p2).getIsbn());
		else if(this.npags != p2.npags)
			return this.npags - p2.npags;
		else
			return 0;
	}
}
