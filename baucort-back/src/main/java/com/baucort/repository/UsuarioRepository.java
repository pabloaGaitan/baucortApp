package com.baucort.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baucort.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByUsuarioAndPassword(String usuario, String password);
}
