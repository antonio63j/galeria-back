package com.afl.galeria.services;

import com.afl.galeria.entities.Slider;

public interface ISliderService {

	public Slider findById(Long id);
	
	public Slider save (Slider slider);
	
	void deleteById (Long id);
}
