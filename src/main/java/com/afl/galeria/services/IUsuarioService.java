package com.afl.galeria.services;

import java.util.List;

import com.afl.galeria.entities.Usuario;

public interface IUsuarioService {
	
	public Usuario findById (Long id);

	public Usuario findByUsername (String username);
	
	public Usuario save (Usuario usuario);
	
	public Usuario findByCodActivacion(String codActivacion);
	
	public boolean activarUsuario(String token);
	
	public List<Usuario> findAll(); 
	
	public boolean existsByUsername(String username);
	
}
