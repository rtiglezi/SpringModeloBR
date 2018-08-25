package br.com.meusite.meuprojeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.meusite.meuprojeto.domain.Usuario;
import br.com.meusite.meuprojeto.domain.enums.Perfil;
import br.com.meusite.meuprojeto.dto.UsuarioDTO;
import br.com.meusite.meuprojeto.dto.UsuarioNewDTO;
import br.com.meusite.meuprojeto.repositories.UsuarioRepository;
import br.com.meusite.meuprojeto.security.UserSS;
import br.com.meusite.meuprojeto.services.exceptions.AuthorizationException;
import br.com.meusite.meuprojeto.services.exceptions.DataIntegrityException;
import br.com.meusite.meuprojeto.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public List<Usuario> findAll() {
		return repo.findAll();
	}
	
	public Usuario find(Integer id) {

		UserSS usuario = IdentificacaoDeUsuarioLogadoService.usuarioLogado();
		
		if (usuario==null || !usuario.temPerfil(Perfil.ADMIN) && !id.equals(usuario.getId())) {
			
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getSimpleName()));
	}
	
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj.setSenha(pe.encode(obj.getSenha()));
		obj = repo.save(obj);
		return obj;
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	
	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há registros relacionados");
		}
	}
	
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getLogin(), null);
	}
	
	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario obj = new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getLogin(), objDto.getSenha());
		return obj;
	}
	
	
}
