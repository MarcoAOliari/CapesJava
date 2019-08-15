package producoes;
public class ArtigoPeriodico extends Artigo{
	private int volume = 0;
	private int fasciculo = 0;
	private int serie = 0;
	
	/**
	 * Construtor da classe ArtigoPeriodico, que extende Artigo, Publicacao e Producao. Como atributos proprios, possui tres inteiros,
	 * representando o volume, o numero do fasciculo e a serie do ArtigoPeriodico. Em todos os tres inteiros, tem-se uma verificacao
	 * de erro, para caso a String lida do arquivo csv de entrada nao esteja formatada da maneira ideal. Se o erro for detectado, os
	 * valores sao atribuidos como 0.
	 * @param id - codigo identificador da Producao
	 * @param natureza - natureza da Producao
	 * @param titulo - titulo da Producao
	 * @param idioma - idioma em que foi escrita a Producao
	 * @param cidade - cidade em que foi produzida a Producao
	 * @param editora - editora responsavel pela Publicacao
	 * @param issn - codigo representando o issn (International Standard Serial Number) do Artigo
	 * @param volume - String representando o volume em que foi publicado o ArtigoPeriodico
	 * @param fasciculo - String representando o numero do fasciculo em que foi publicado o ArtigoPeriodico
	 * @param serie - String representando o numero da serie em que foi publicado o ArtigoPeriodico 
	 */
	public ArtigoPeriodico(String id, String natureza, String titulo, String idioma, String cidade, String editora, String issn, String volume, String fasciculo, String serie) {
		super(id, natureza, titulo, idioma, cidade, editora, issn); 
		
		try {
			this.fasciculo = Integer.parseInt(fasciculo);
		} catch(NumberFormatException e) {
			this.fasciculo = 0;
		}
		
		try {
			this.volume = Integer.parseInt(volume);
		} catch(NumberFormatException e) {
			this.volume = 0;
		}
		
		try {
			this.serie = Integer.parseInt(serie);
		} catch(NumberFormatException e){
			this.serie = 0;
		}
	}
	
	/**
	 * Retorna o volume do ArtigoPeriodico.
	 * @return Inteiro representando o volume do ArtigoPeriodico
	 */
	public int getVolume() {
		return volume;
	}
	
	/**
	 * Retorna o fasciculo do ArtigoPeriodico.
	 * @return Inteiro representando o fasciculo do ArtigoPeriodico
	 */
	public int getFasciculo() {
		return fasciculo;
	}
	
	/**
	 * Retorna a serie do ArtigoPeriodico.
	 * @return Inteiro representando a serie do ArtigoPeriodico
	 */
	public int getSerie() {
		return serie;
	}

	/**
	 * Metodo sobrescrito para impressao mais simples da classe ArtigoPeriodico. Realizam-se verificacoes para todos os inteiros
	 * (volume, fasciculo, serie e npags), caso sejam maiores que 0 sao inclusos na impressao, ao contrario sao excluidos.
	 */
	@Override
	public String toString() {
		String retorno =  natureza + ";" + idioma + ";" + editora + ";" + cidade + ";";
		
		if(volume > 0) retorno += volume + ";";
		else retorno += "" + ";";
		
		if(fasciculo > 0) retorno += fasciculo + ";";
		else retorno += "" + ";";
		
		if(serie > 0) retorno += serie + ";";
		else retorno += "" + ";";
		
		retorno += issn + ";";
		
		if(npags > 0)
			retorno += npags;
		
		return retorno;

	}
	
	/**
	 * Imprime cabecalho da classe ArtigoPeriodico especificado no comando csv.
	 */
	public void imprimeCabecalho() {
		System.out.println("Natureza;Idioma;Editora;Cidade;Volume;Fasciculo;Serie;ISSN;Paginas");
	}

	/**
	 * Metodo sobrescrito utilizado para ordenar uma lista de ArtigoPeriodico (por meio do metodo sort da classe Collections) 
	 * da maneira especificada pelo comando csv, realizando as devidas comparacoes.
	 * @param p2 - outro objeto da classe ArtigoPeriodico (extendido de Producao)
	 * @return - Inteiro logico auxiliar para o metodo sort realizar a ordenacao da maneira desejada
	 */
	@Override
	public int compareTo(Producao p2) {
		if(!this.getNatureza().equals(p2.getNatureza()))
			return this.getNatureza().compareTo(p2.getNatureza());
		else if(!this.getIdioma().equals(p2.getIdioma()))
			return this.getIdioma().compareTo(p2.getIdioma());
		else if(!this.getEditora().equals(((ArtigoPeriodico)p2).getEditora()))
			return this.getEditora().compareTo(((ArtigoPeriodico)p2).getEditora());
		else if(!this.getCidade().equals(p2.getCidade()))
			return this.getCidade().compareTo(p2.getCidade());
		else if(this.getVolume() != (((ArtigoPeriodico)p2).getVolume()))
			return this.getVolume() - (((ArtigoPeriodico)p2).getVolume());
		else if(this.getFasciculo() != (((ArtigoPeriodico)p2).getFasciculo()))
			return this.getFasciculo() - (((ArtigoPeriodico)p2).getFasciculo());
		else if(this.getSerie() != (((ArtigoPeriodico)p2).getSerie()))
			return this.getSerie() - (((ArtigoPeriodico)p2).getSerie());
		else if(!this.getIssn().equals(((ArtigoPeriodico)p2).getIssn()))
			return this.getIssn().compareTo(((ArtigoPeriodico)p2).getIssn());
		else if(this.npags != p2.npags)
			return this.npags - p2.npags;
		else
			return 0;
	}
}
