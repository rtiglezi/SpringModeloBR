package br.com.meusite.meuprojeto.services.exceptions;

public class ExcecaoDeObjetoNaoEncontrado extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExcecaoDeObjetoNaoEncontrado(String msg) {
		super(msg);
	}
	
	public ExcecaoDeObjetoNaoEncontrado(String msg, Throwable cause) {
		super(msg, cause);
	}

}
