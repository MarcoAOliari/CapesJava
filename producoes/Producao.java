package producoes;
abstract public class Producao implements Comparable<Producao>{
	protected String id;
	protected String natureza;
	protected String titulo;
	protected String idioma;
	protected String cidade;
	protected int npags = 0;
	
	/**
	 * Construtor da classe abstrata Producao. 
	 * @param id - codigo indentificador da Producao
	 * @param natureza - natureza da Producao
	 * @param titulo - titulo da Producao
	 * @param idioma - idioma em que foi escrita a Producao
	 * @param cidade - cidade em que foi produzida a Producao
	 */
	public Producao(String id, String natureza, String titulo, String idioma, String cidade) {
		this.natureza = natureza;	this.titulo = titulo;		this.id = id;
		this.idioma = idioma;		this.cidade = cidade;		
	}
	
	/**
	 * Retorna o titulo da Producao.
	 * @return String representando o titulo da Producao
	 */
	public String getTitulo() {
		return this.titulo;
	}
	
	/**
	 * Retorna o numero de paginas da Producao.
	 * @return Inteiro representando o numero de paginas da Producao
	 */
	public int getPags() {
		return npags;
	}
	
	/**
	 * Retorna a natureza da Producao.
	 * @return String representando a natureza da Producao
	 */
	public String getNatureza() {
		return natureza;
	}
	
	/**
	 * Retorna o idioma da Producao.
	 * @return String representando o idioma da Producao
	 */
	public String getIdioma() {
		return idioma;
	}
	
	/**
	 * Retorna a cidade da Producao.
	 * @return String representando a cidade da Producao
	 */
	public String getCidade() {
		return cidade;
	}
	
	/**
	 * Retorna o codigo de identificacao da Producao.
	 * @return String representando o codigo de identificacao da Producao
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Atribui a quantidade de paginas a uma producao.<br><br> O metodo possui sobrecarga, porem os dois parametros sao para
	 * producoes que no arquivo csv possuem campos "NR_PAGINA_FINAL" e "NR_PAGINA_INICIAL". As duas Strings sao transformadas
	 * em inteiros, e caso a pagina final seja maior que a inicial, ambas sejam maior que 0 e a quantidade seja menor que 2000,
	 * o numero de paginas eh atribuido ao objeto. Se alguma das Strings (ou ambas) for enviada com formatacao indevida, o erro
	 * eh capturado e o valor eh atribuido como 0.
	 * @param pagf - String com a pagina final da producao
	 * @param pagi - String com a pagina inicial da producao
	 */
	public void setPags(String pagf, String pagi) {
		int pags = 0;	
		try {
			int pi = Integer.parseInt(pagi);
			int pf = Integer.parseInt(pagf);
			if(pf >= pi && pf >= 0 && pi >= 0) {
				if(pf - pi <= 2000) {
					pags = pf - pi + 1;
					this.npags = pags;
				}
			}
		}catch(NumberFormatException e) {
			this.npags = 0;
		}
	}
	
	/**
	 * Atribui a quantidade de paginas a uma producao.<br><br> O metodo possui sobrecarga, porem o parametro eh para producoes
	 * que no arquivo csv possui o campo "NR_PAGINAS". A String eh transformada em inteiro, e caso seja menor ou igual a 2000
	 * e maior que 0, o valor eh atribuido. Se a String for enviada em formatacao indevida, o erro eh capturado e o valor eh
	 * atribuido como 0.
	 * @param pags
	 */
	public void setPags(String pags) {
		try {
			int pi = Integer.parseInt(pags);
			if(pi<=2000 && pi >= 0)
				this.npags = pi;
		}catch(NumberFormatException e) {
			this.npags = 0;
		}
	}
	
	/**
	 * Metodo abstrato para a impressao dos cabecalhos de cada tipo de Producao. Eh sobrescrito nas classes de todos os tipos
	 * de Producao.
	 */
	public abstract void imprimeCabecalho();
	
}
