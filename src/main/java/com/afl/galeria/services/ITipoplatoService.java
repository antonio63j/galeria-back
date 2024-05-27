package com.afl.galeria.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.afl.galeria.entities.Tiposugerencia;

public interface ITipoplatoService {
	

	public List<Tiposugerencia> findAllByNombreTipo();
	
	public Tiposugerencia findById(Long id);
	
	public Tiposugerencia save (Tiposugerencia tiposugerencia);
	
	void deleteById (Long id);
}
