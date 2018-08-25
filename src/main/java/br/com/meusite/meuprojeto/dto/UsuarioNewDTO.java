package br.com.meusite.meuprojeto.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.com.meusite.meuprojeto.domain.enums.Perfil;
import br.com.meusite.meuprojeto.services.validation.UsuarioInsert;

@UsuarioInsert
public class UsuarioNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String login;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	public UsuarioNewDTO() {
		adicionaPerfil(Perfil.USUARIO);
	}
	
	
	public UsuarioNewDTO(String nome, String email, String login, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		adicionaPerfil(Perfil.USUARIO);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.converteParaEnum(x)).collect(Collectors.toSet());
	}
	
	public void adicionaPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
}
