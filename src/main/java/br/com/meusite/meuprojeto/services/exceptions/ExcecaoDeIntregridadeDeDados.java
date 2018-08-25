package br.com.meusite.meuprojeto.services.exceptions;

public class ExcecaoDeIntregridadeDeDados extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExcecaoDeIntregridadeDeDados(String msg) {
		super(msg);
	}
	
	public ExcecaoDeIntregridadeDeDados(String msg, Throwable cause) {
		super(msg, cause);
	}

}
