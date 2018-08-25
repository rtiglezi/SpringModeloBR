package br.com.meusite.meuprojeto.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.meusite.meuprojeto.domain.Usuario;

public interface EmailService {

	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Usuario usuario, String newPass);
	
}
