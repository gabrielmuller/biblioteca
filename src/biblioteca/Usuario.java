package biblioteca;

public class Usuario {
	protected int id; // de 0 a 20
	protected boolean ehOperador;
	protected Biblioteca estaBiblioteca;
	protected String nome;
	public Usuario(Biblioteca estaBiblioteca, int id, String nome) {
		this.estaBiblioteca = estaBiblioteca;
		this.id = id;
		this.nome = nome;
		ehOperador = false;

	}

	public String pedirParaLocarItem(int id) {
		return estaBiblioteca.locarItem(id, this, "");
	}
	
	public String pedirParaLocarItem(String nome) {
		return estaBiblioteca.locarItem(estaBiblioteca.getItem(nome), this, nome);
	}

	public String pedirParaDevolverItem(int id) {
		return estaBiblioteca.devolverItem(id, this, "");
	}
	
	public String pedirParaDevolverItem(String nome) {
		return estaBiblioteca.devolverItem(estaBiblioteca.getItem(nome), this, nome);
	}

	public String listaDeItensLocados() {
		String resultado = "Ola " + nome + ", voce tem os seguintes itens: \n";
		Locacao[] minhasLoc = estaBiblioteca.getLocacoes(this);
		for (int i = 0; i < minhasLoc.length; i++) {
			int idDestaLoc = minhasLoc[i].id;
			if (idDestaLoc != -1) {
				InformacoesDeItem info = estaBiblioteca.consultarItem(idDestaLoc);
				resultado = resultado + info.nome + "\n";
			}
		}
		return resultado;
	}

	protected Locacao getLocacaoDoItem(int id) {
		//se o usuario tiver item, retorna uma COPIA da locacao deste item.
		//se nao tiver, retorna null
		Locacao resultado = null;
		Locacao[] minhasLoc = estaBiblioteca.getLocacoes(this);
		for (int i = 0; i < minhasLoc.length && resultado == null; i++) {
			if (id == minhasLoc[i].id) {
				resultado = minhasLoc[i];
			}
		}
		return resultado;
	}
	
	protected String dataDeDevolucao(int id) {
		String resultado;
		Locacao atual = getLocacaoDoItem(id);
		if (atual == null) {
			resultado = "Este usuario nao possui este item.";
		}
		else {
			resultado = atual.getPrazo();
		}
		return resultado;
		//retorna a data de devolucao se tiver a locacao
		//ou uma mensagem de erro se nao tiver
	}

}
