package br.com.meusite.meuprojeto.services.exceptions;

public class ExcecaoDeAutorizacao extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExcecaoDeAutorizacao(String msg) {
		super(msg);
	}
	
	public ExcecaoDeAutorizacao(String msg, Throwable cause) {
		super(msg, cause);
	}

}
