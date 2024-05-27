package com.afl.galeria.services;

import java.util.List;

import com.afl.galeria.entities.Empresa;
import com.afl.galeria.entities.Slider;

public interface IEmpresaService {

	public Empresa findById(Long id);
	
	// public Empresa create (Empresa empresa);
	
	public Empresa save (Empresa empresa);
	
	List<Slider> findAllSliders();
	
//	public Slider save (Slider slider);
	
}
