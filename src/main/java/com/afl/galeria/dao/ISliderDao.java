package com.afl.galeria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.afl.galeria.entities.Slider;

public interface ISliderDao extends JpaRepository<Slider, Long> {
	

}
