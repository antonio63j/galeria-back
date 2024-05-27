package com.afl.galeria.services;

import java.util.List;
import java.util.Set;

import com.afl.galeria.entities.Lote;
import com.afl.galeria.entities.LoteSugerencia;

public interface ILoteService {

	public Set<Lote> findAllByLabel();
	
	public Set<Lote> findAllByLabelVisible(boolean visible);
	
	public Lote findById(Long id);
	
	public Lote save (Lote lote);
	
	void deleteById (Long id);
	
	void deleteLoteSugerenciaById (Long id);
	
	LoteSugerencia saveLoteSugerencia( LoteSugerencia loteSugerencia);
}
