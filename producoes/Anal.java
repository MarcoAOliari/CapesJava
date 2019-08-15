package producoes;
public class Anal extends Producao {
	private String evento;
	
	/**
	 * Construtor da classe Anal. Como atributo proprio, apenas evento, enquanto os demais sao extendidos por heranca
	 * da classe Producao.
	 * @param id - codigo indentificador da Producao
	 * @param natureza - natureza da Producao
	 * @param titulo - titulo da Producao
	 * @param idioma - idioma em que foi escrita a Producao
	 * @param cidade - cidade em que foi produzida a Producao
	 * @param evento - evento onde ocorreu o Anal
	 */
	public Anal(String id, String natureza, String titulo, String idioma, String cidade, String evento) {
		super(id, natureza, titulo, idioma, cidade);
		this.evento = evento;
	}
	
	/**
	 * Metodo sobrescrito para impressao mais simples de um Anal. Caso o numero de paginas seja menor que 0, imprime todos os atributos
	 * da classe (herdados e proprios) com excecao do numero de paginas. No contrario, imprime todos os atributos ordenados
	 * de acordo com o solicitado. 
	 */
	@Override
	public String toString() {
		if(npags>0) 
			return natureza + ";" + titulo + ";" + idioma + ";" + evento + ";" + cidade + ";" + npags;
		else
			return natureza + ";" + titulo + ";" + idioma + ";" + evento + ";" + cidade + ";";
	}
	
	/**
	 * Imprime o cabecalho dos Anais pedido no comando csv do programa.
	 */
	public void imprimeCabecalho() {
		System.out.println("Natureza;Titulo;Idioma;Evento;Cidade;Paginas");
	}
	
	/**
	 * Retorna o nome do evento onde foi realizado o Anal.
	 * @return String com o nome do evento.
	 */
	public String getEvento() {
		return evento;
	}
	
	/**
	 * Metodo sobrescrito utilizado para ordenar uma lista de Anais (por meio do metodo sort da classe Collections)
	 * da maneira especificada pelo comando csv, realizando as devidas comparacoes.
	 * @param p2 - outro objeto da classe Anal (extendido de Producao)
	 * @return - Inteiro logico auxiliar para o metodo sort realizar a ordenacao da maneira desejada.
	 */
	@Override
	public int compareTo(Producao p2) {
		if(!this.getNatureza().equals(p2.getNatureza()))
			return this.getNatureza().compareTo(p2.getNatureza());
		else if(!this.getTitulo().equals(p2.getTitulo()))
			return this.getTitulo().compareTo(p2.getTitulo());
		else if(!this.getIdioma().equals(p2.getIdioma()))
			return this.getIdioma().compareTo(p2.getIdioma());
		else if(!this.getEvento().equals(((Anal)p2).getEvento()))
			return this.getEvento().compareTo(((Anal)p2).getEvento());
		else if(!this.getCidade().equals(p2.getCidade()))
			return this.getCidade().compareTo(p2.getCidade());
		else if(this.npags != p2.npags)
			return this.npags - p2.npags;
		else
			return 0;
	}

}
