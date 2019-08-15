package producoes;
abstract public class Publicacao extends Producao{
	protected String editora;
	
	/**
	 * Construtor da classe abstrata Publicacao. Como atributo proprio, tem-se editora, sendo todos os outros extendidos
	 * de Producao.
	 * @param id - codigo identificador da Producao
	 * @param natureza - natureza da Producao
	 * @param titulo - titulo da Producao
	 * @param idioma - idioma em que foi escrita a Producao
	 * @param cidade - cidade em que foi produzida a Producao
	 * @param editora - editora responsavel pela Publicacao
	 */
	public Publicacao(String id, String natureza, String titulo, String idioma, String cidade, String editora) {
		super(id, natureza, titulo, idioma, cidade);
		this.editora = editora;
	}
	
	/**
	 * Retorna a editora da Publicacao.
	 * @return String representando a editora da Publicacao
	 */
	public String getEditora() {
		return editora;
	}
}
