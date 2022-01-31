package com.baucort.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baucort.controller.dto.UsuarioDto;
import com.baucort.services.UsuarioService;

@RestController
@RequestMapping("usuario")
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioDto> login(@RequestParam("user") String usuario, @RequestParam("password") String pwd){
		
		UsuarioDto dto = usuarioService.getByUsernameAndPassword(usuario, pwd);
		if(dto != null) {
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		
	}
	
	
}
