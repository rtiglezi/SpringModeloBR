package br.com.meusite.meuprojeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meusite.meuprojeto.domain.Usuario;
import br.com.meusite.meuprojeto.domain.enums.Perfil;
import br.com.meusite.meuprojeto.repositories.UsuarioRepository;
import br.com.meusite.meuprojeto.security.UserSS;
import br.com.meusite.meuprojeto.services.exceptions.ExcecaoDeAutorizacao;
import br.com.meusite.meuprojeto.services.exceptions.ExcecaoDeObjetoNaoEncontrado;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	
	public List<Usuario> listarTodosService() {
		return repo.findAll();
	}
	
	public Usuario buscarPorIdService(Integer id) {

		UserSS usuario = IdentificacaoDeUsuarioLogadoService.usuarioLogado();
		
		if (usuario==null || !usuario.temPerfil(Perfil.ADMIN) && !id.equals(usuario.getId())) {
			
			throw new ExcecaoDeAutorizacao("Acesso negado");
		}
		
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ExcecaoDeObjetoNaoEncontrado(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getSimpleName()));
	}
}
