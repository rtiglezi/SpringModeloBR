package br.com.meusite.meuprojeto.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.meusite.meuprojeto.dto.EmailDTO;

import br.com.meusite.meuprojeto.security.JWTUtil;
import br.com.meusite.meuprojeto.security.UserSS;
import br.com.meusite.meuprojeto.services.AutorizacaoServico;
import br.com.meusite.meuprojeto.services.IdentificacaoDeUsuarioLogadoService;

@RestController
@RequestMapping(value = "/autorizacao")
public class AutorizacaoResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AutorizacaoServico service;
	
	@RequestMapping(value = "/reinicia_token", method = RequestMethod.POST)
	public ResponseEntity<Void> reiniciaToken(HttpServletResponse response) {
		UserSS user = IdentificacaoDeUsuarioLogadoService.usuarioLogado();
		String token = jwtUtil.geraToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/nova_senha", method = RequestMethod.POST)
	public ResponseEntity<Void> lembrarSenha(@Valid @RequestBody EmailDTO objDto) {
		service.enviarNovaSenha(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
	
}
