package br.com.meusite.meuprojeto.resources.exceptions;

import java.io.Serializable;

public class ErroPadrao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long instanteDoErro;
	private Integer status;
	private String identificacaoDoErro;
	private String mensagemDoErro;
	private String pathDoErro;
	
	public ErroPadrao(Long timestamp, Integer status, String error, String message, String path) {
		super();
		this.instanteDoErro = timestamp;
		this.status = status;
		this.identificacaoDoErro = error;
		this.mensagemDoErro = message;
		this.pathDoErro = path;
	}

	public Long getInstanteDoErro() {
		return instanteDoErro;
	}

	public void setInstanteDoErro(Long timestamp) {
		this.instanteDoErro = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIdentificacaoDoErro() {
		return identificacaoDoErro;
	}

	public void setIdentificacaoDoError(String error) {
		this.identificacaoDoErro = error;
	}

	public String getMensagemDoErro() {
		return mensagemDoErro;
	}

	public void setMensagemDoErro(String message) {
		this.mensagemDoErro = message;
	}

	public String getPathDoErro() {
		return pathDoErro;
	}

	public void setPathDoErro(String path) {
		this.pathDoErro = path;
	}
}
