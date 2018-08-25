package br.com.meusite.meuprojeto.resources.exceptions;

import java.io.Serializable;

public class ErroDeCampo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nomeDoCampo;
	private String mensagem;
	
	public ErroDeCampo() {
	}

	public ErroDeCampo(String fieldName, String message) {
		super();
		this.nomeDoCampo = fieldName;
		this.mensagem = message;
	}

	public String getNomeDoCampo() {
		return nomeDoCampo;
	}

	public void setNomeDoCampo(String fieldName) {
		this.nomeDoCampo = fieldName;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String message) {
		this.mensagem = message;
	}
}
