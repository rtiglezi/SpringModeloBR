package br.com.meusite.meuprojeto.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.meusite.meuprojeto.services.DBService;
import br.com.meusite.meuprojeto.services.EmailService;
import br.com.meusite.meuprojeto.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevEnvConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instantiateInicialTestData();
		return true;
	}
	
	@Bean 
	EmailService emailService() {
		return new SmtpEmailService();
	}
	
}
