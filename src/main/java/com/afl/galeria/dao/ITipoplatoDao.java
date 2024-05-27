package com.afl.galeria.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.afl.galeria.entities.Tiposugerencia;

public interface ITipoplatoDao extends JpaRepository<Tiposugerencia, Long> {

    @Query ("select p from Tiposugerencia p order by p.nombre asc")
    public List<Tiposugerencia> findAllByNombreTipo();

	// List<Tiposugerencia> findAllByNombre();
	
}