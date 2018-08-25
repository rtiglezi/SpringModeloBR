package br.com.meusite.meuprojeto.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.meusite.meuprojeto.domain.enums.Perfil;


@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String nome;
	
	@Column(unique=true)
	@NotEmpty(message="Preenchimento obrigatório")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String login;
	
	@JsonIgnore
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	public Usuario() {
		adicionaPerfil(Perfil.USUARIO);
	}
	
	
	public Usuario(Integer id, String nome, String email, String login, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		adicionaPerfil(Perfil.USUARIO);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
