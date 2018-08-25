package br.com.meusite.meuprojeto.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.meusite.meuprojeto.services.exceptions.ExcecaoDeAutorizacao;
import br.com.meusite.meuprojeto.services.exceptions.ExcecaoDeIntregridadeDeDados;
import br.com.meusite.meuprojeto.services.exceptions.ExcecaoDeObjetoNaoEncontrado;

@ControllerAdvice
public class ManipuladorDeExcecoesDeRecurso {
	
	@ExceptionHandler(ExcecaoDeObjetoNaoEncontrado.class)
	public ResponseEntity<ErroPadrao> objetoNaoEncontrado(ExcecaoDeObjetoNaoEncontrado e, HttpServletRequest request) {
		
		ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(ExcecaoDeIntregridadeDeDados.class)
	public ResponseEntity<ErroPadrao> integridadeDosDados(ExcecaoDeIntregridadeDeDados e, HttpServletRequest request) {
		
		ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroPadrao> validacao(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		ErroDeValidacao err = new ErroDeValidacao(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", e.getMessage(), request.getRequestURI());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	@ExceptionHandler(ExcecaoDeAutorizacao.class)
	public ResponseEntity<ErroPadrao> autorizacao(ExcecaoDeAutorizacao e, HttpServletRequest request) {
		
		ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

}
