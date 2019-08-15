package producoes;
abstract public class Artigo extends Publicacao{
	protected String issn;
	
	/**
	 * Construtor da classe abstrata Artigo, que eh extendida por ArtigoJornalRevista e ArtigoPeriodico. A classe possui como
	 * atributo proprio apenas uma String issn, enquanto os demais sao extendidos de Publicacao e Producao.
	 * @param id - codigo identificador da Producao
	 * @param natureza - natureza da Producao
	 * @param titulo - titulo da Producao
	 * @param idioma - idioma em que foi escrita a Producao
	 * @param cidade - cidade em que foi produzida a Producao
	 * @param editora - editora responsavel pela Publicacao
	 * @param issn - codigo representando o issn (International Standard Serial Number) do Artigo
	 */
	public Artigo(String id, String natureza, String titulo, String idioma, String cidade, String editora, String issn) {
		super(id, natureza, titulo, idioma, cidade, editora);
		this.issn = issn;
	}

	/**
	 * Retorna o codigo issn do Artigo.
	 * @return String representando o codigo issn do Artigo.
	 */
	public String getIssn() {
		return issn;
	}
}
