package br.com.meusite.meuprojeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.meusite.meuprojeto.domain.Usuario;
import br.com.meusite.meuprojeto.domain.enums.Perfil;
import br.com.meusite.meuprojeto.dto.UsuarioDTO;
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
	
	
	
	private boolean checkReadPermission(Integer id) {
		UserSS usuario = UserService.authenticated();
		if (usuario==null || !usuario.temPerfil(Perfil.ADMIN) && !id.equals(usuario.getId())) {
			throw new AuthorizationException("Usuário sem permissão de acesso a estes dados.");
		}
		return true;
	}
	

	
	
	public List<Usuario> findAll() {
		return repo.findAll();
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);		
	}
	
	public Usuario find(Integer id) {
		checkReadPermission(id);
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getSimpleName()));
	}
	
	
		
	
	public Usuario fromInsertDTO(UsuarioDTO objDto) {
		Usuario obj = new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getLogin(), objDto.getSenha());
		return obj;
	}
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj.setSenha(pe.encode(obj.getSenha()));
		obj = repo.save(obj);
		return obj;
	}
	
	

	
	public Usuario fromUpdateDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getLogin(), null);
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setLogin(obj.getLogin());
	}
		
	public Usuario update(Usuario obj) {
		checkReadPermission(obj.getId());
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
	
	
	
	
	
	
}