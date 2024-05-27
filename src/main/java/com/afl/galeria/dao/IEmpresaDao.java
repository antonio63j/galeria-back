package com.afl.galeria.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.afl.galeria.entities.Empresa;
import com.afl.galeria.entities.Slider;

public interface IEmpresaDao extends JpaRepository<Empresa, Long> {
	
	@Query ("from Slider")
	public List<Slider> findAllSliders();
	
//	@Query ("from Slider")
//	public Slider save(Slider slider);
	
		
}
