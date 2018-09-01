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
		Usuario u2 = new Usuario(null, "Marcia", "marciabertti02@hotmail.com", "marciabertti", pe.encode("12345678"));
		
		Usuario u3 = new Usuario(null, "Marcia", "marciabertti03@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u4 = new Usuario(null, "Marcia", "marciabertti04@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u5 = new Usuario(null, "Marcia", "marciabertti05@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u6 = new Usuario(null, "Marcia", "marciabertti06@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u7 = new Usuario(null, "Marcia", "marciabertti07@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u8 = new Usuario(null, "Marcia", "marciabertti08@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u9 = new Usuario(null, "Marcia", "marciabertti09@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u10 = new Usuario(null, "Marcia", "marciabertti10@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u11 = new Usuario(null, "Marcia", "marciabertti11@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u12 = new Usuario(null, "Marcia", "marciabertti12@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u13 = new Usuario(null, "Marcia", "marciabertti13@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u14 = new Usuario(null, "Marcia", "marciabertti14@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u15 = new Usuario(null, "Marcia", "marciabertti15@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u16 = new Usuario(null, "Marcia", "marciabertti16@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u17 = new Usuario(null, "Marcia", "marciabertti17@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u18 = new Usuario(null, "Marcia", "marciabertti18@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u19 = new Usuario(null, "Marcia", "marciabertti19@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u20 = new Usuario(null, "Marcia", "marciabertti20@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u21 = new Usuario(null, "Marcia", "marciabertti21@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u22 = new Usuario(null, "Marcia", "marciabertti22@hotmail.com", "marciabertti", pe.encode("12345678"));
		Usuario u23 = new Usuario(null, "Marcia", "marciabertti23@hotmail.com", "marciabertti", pe.encode("12345678"));
		
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19, u20, u21, u22, u23));
		
		
		
	}
}
