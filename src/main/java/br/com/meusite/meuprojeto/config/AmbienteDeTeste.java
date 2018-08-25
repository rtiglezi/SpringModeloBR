package br.com.meusite.meuprojeto.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.meusite.meuprojeto.services.DadosParaTeste;
import br.com.meusite.meuprojeto.services.EmailService;
import br.com.meusite.meuprojeto.services.SmtpEmailService;

@Configuration
@Profile("test")
public class AmbienteDeTeste {

	@Autowired
	private DadosParaTeste dbService;
	
	@Bean
	public boolean instanciaBancoDeDados() throws ParseException {
		dbService.instanciaBancoDeDadosDeTeste();
		return true;
	}
	
	@Bean EmailService emailService() {
		return new SmtpEmailService();
	}
	
}
