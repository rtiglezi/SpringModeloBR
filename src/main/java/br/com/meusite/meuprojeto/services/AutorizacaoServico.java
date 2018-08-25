package br.com.meusite.meuprojeto.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.meusite.meuprojeto.domain.Usuario;
import br.com.meusite.meuprojeto.repositories.UsuarioRepository;
import br.com.meusite.meuprojeto.services.exceptions.ObjectNotFoundException;

@Service
public class AutorizacaoServico {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	/*
	 * @Autowired
	 * private EmailService emailService;
	 */
	
	private Random rand = new Random();
	
	public void enviarNovaSenha(String email) {
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = novaSenha();
		usuario.setSenha(pe.encode(newPass));
		
		usuarioRepository.save(usuario);
		//emailService.sendNewPasswordEmail(usuario, newPass);
	}

	private String novaSenha() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
