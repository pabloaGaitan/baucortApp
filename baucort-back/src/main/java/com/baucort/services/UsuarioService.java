package com.baucort.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.baucort.controller.dto.UsuarioDto;
import com.baucort.entities.Usuario;
import com.baucort.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioDto getByUsernameAndPassword(String usuario, String contrasenia) {
		Usuario user = usuarioRepository.findByUsuarioAndPassword(usuario, contrasenia);
		if(user != null) {
			String token = getJWTToken(usuario);
			UsuarioDto usuariodto = new UsuarioDto();
			usuariodto.setUsername(usuario);
			usuariodto.setToken(token);		
			return usuariodto;			
		}
		return null;
	}
	
	private String getJWTToken(String username) {
		String secretKey = "baucort";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

}
