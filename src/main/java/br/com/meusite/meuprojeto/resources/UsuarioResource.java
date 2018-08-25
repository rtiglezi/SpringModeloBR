package br.com.meusite.meuprojeto.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.meusite.meuprojeto.domain.Usuario;
import br.com.meusite.meuprojeto.services.UsuarioService;

@Controller
@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listarTodosResource() {
		List<Usuario> list = service.listarTodosService();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> buscarPorIdResource(@PathVariable Integer id) {
		Usuario obj = service.buscarPorIdService(id);
		return ResponseEntity.ok().body(obj);
	}
	
}