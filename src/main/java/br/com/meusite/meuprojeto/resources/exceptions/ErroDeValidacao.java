package br.com.meusite.meuprojeto.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErroDeValidacao extends ErroPadrao {
	private static final long serialVersionUID = 1L;

	private List<ErroDeCampo> errors = new ArrayList<>();

	public ErroDeValidacao(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<ErroDeCampo> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String messagem) {
		errors.add(new ErroDeCampo(fieldName, messagem));
	}
}
