package br.com.meusite.meuprojeto.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.meusite.meuprojeto.domain.Usuario;
import br.com.meusite.meuprojeto.domain.enums.Perfil;
import br.com.meusite.meuprojeto.repositories.UsuarioRepository;


@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void instantiateInicialTestData() throws ParseException {
		
		Usuario u1 = new Usuario(null, "Ronaldo", "ronaldotonioli@gmail.com", "ronaldotonioli", pe.encode("12345678"));
		u1.adicionaPerfil(Perfil.ADMIN);
		
		Usuario u2 = new Usuario(null, "Marcia", "marciabertti01@hotmail.com", "marciabertti", pe.encode("12345678"));
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		
		
	}
}
