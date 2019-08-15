package producoes;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ArtigoJornalRevista extends Artigo{
	private Date data;
	
	/**
	 * Construtor da classe ArtigoJornalRevista, que extende Artigo, Publicacao e Producao. Como atributo proprio, possui apenas uma data
	 * (classe Date). Para atribuir a data ao objeto, realiza-se uma verificacao de erro. Caso a String data lida do arquivo csv de entrada
	 * nao possua a formatacao ideal, o atributo data recebe null. No contrario, a String eh transformada para a classe Data e armazenada
	 * no objeto.
	 * @param id - codigo identificador da Producao
	 * @param natureza - natureza da Producao
	 * @param titulo - titulo da Producao
	 * @param idioma - idioma em que foi escrita a Producao
	 * @param cidade - cidade em que foi produzida a Producao
	 * @param editora - editora responsavel pela Publicacao
	 * @param issn - codigo representando o issn (International Standard Serial Number) do Artigo
	 * @param data - data de quando foi publicado o Artigo especificado
	 */
	public ArtigoJornalRevista(String id, String natureza, String titulo, String idioma, String cidade, String editora, String issn, String data) {
		super(id, natureza, titulo, idioma, cidade, editora, issn);
		
		try {
			this.data = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			this.data = null;
		}
	}
	
	/**
	 * Retorna a data de publicacao do Artigo.
	 * @return Data classe Date representando o dia em que foi publicado o Artigo
	 */
	public Date getData() {
		return data;
	}
	
	/**
	 * Metodo sobrescrito para impressao mais simples da classe ArtigoJornalRevista. Antes de retornar a String completa, verifica-se se a data
	 * eh nula ou nao, e tambem se o numero de paginas eh maior que 0 ou nao.
	 */
	@Override
	public String toString() {
		String retorno = titulo + ";" + idioma + ";" + cidade + ";";
		
		if(data != null)
			retorno += new SimpleDateFormat("dd/MM/yyyy").format(data) + ";";
		
		retorno += issn + ";";
		
		if(npags>0)
			retorno += npags;
		
		return retorno;
	}
	
	/**
	 * Imprime cabecalho da classe ArtigoJornalRevista especificado no comando csv.
	 */
	public void imprimeCabecalho() {
		System.out.println("Titulo;Idioma;Cidade;Data;ISSN;Paginas");
	}

	/**
	 * Metodo sobrescrito utilizado para ordenar uma lista de ArtigoJornalRevista (por meio do metodo sort da classe Collections) 
	 * da maneira especificada pelo comando csv, realizando as devidas comparacoes.
	 * @param p2 - outro objeto da classe ArtigoJornalRevista (extendido de Producao)
	 * @return - Inteiro logico auxiliar para o metodo sort realizar a ordenacao da maneira desejada
	 */
	@Override
	public int compareTo(Producao p2) {
		if(!this.getTitulo().equals(p2.getTitulo()))
			return this.getTitulo().compareTo(p2.getTitulo());
		else if(!this.getIdioma().equals(p2.getIdioma()))
			return this.getIdioma().compareTo(p2.getIdioma());
		else if(!this.getCidade().equals(p2.getCidade()))
			return this.getCidade().compareTo(p2.getCidade());
		else if(!this.getData().equals(((ArtigoJornalRevista)p2).getData()))
			return this.getData().compareTo(((ArtigoJornalRevista)p2).getData());
		else if(!this.getIssn().equals(((ArtigoJornalRevista)p2).getIssn()))
			return this.getIssn().compareTo(((ArtigoJornalRevista)p2).getIssn());
		else if(this.npags != p2.npags)
			return this.npags - p2.npags;
		else
			return 0;
	}
}
