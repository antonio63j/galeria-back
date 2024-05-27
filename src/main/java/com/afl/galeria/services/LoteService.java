package com.afl.galeria.services;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.afl.galeria.controllers.LoteController;
import com.afl.galeria.dao.ILoteDao;
import com.afl.galeria.dao.ILoteSugerenciaDao;
import com.afl.galeria.entities.Lote;
import com.afl.galeria.entities.LoteSugerencia;
import com.afl.galeria.entities.Sugerencia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoteService implements ILoteService {
	
	private Logger log = LoggerFactory.getLogger(LoteService.class);
	
	@Autowired
	private ILoteDao loteDao;
	
	@Autowired
	private ILoteSugerenciaDao loteSugerenciaDao;
	
	// AFL adaptacion para artefluido
	@Autowired
	ISugerenciaService sugerenciaService;
	//
	

	@Override
	@Transactional (readOnly = true)
	public Set<Lote> findAllByLabel() {
		return loteDao.findAllByLabel();
	}

	
	
	@Override
	public Lote findById(Long id) {
		// TODO Auto-generated method stub
		return loteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Lote save(Lote lote) {
		return loteDao.save(lote);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// AFL adaptacion para artefluido 
		 
		Lote lote;
		
		Sugerencia sugerencia, sugerenciaAct;
		lote= loteDao.findById(id).orElse (null);
     
		for (LoteSugerencia loteSugerencia: lote.getLoteSugerencias()) {
			sugerencia = loteSugerencia.getSugerencia();
			sugerenciaAct = sugerenciaService.findById(sugerencia.getId());
			sugerenciaAct.setLoteNombre(null);
			sugerenciaService.save(sugerenciaAct);
			}
		
		loteDao.deleteById(id);

	}
	
	@Override
	@Transactional
	public void deleteLoteSugerenciaById(Long id) {
		
        // AFL adaptacion para artefluido
		
		// leer LoteSugerencia 
		// leer sugerencia + desmarcar el lote + actualizar la sugerencia
		
		LoteSugerencia loteSugerencia;
		Sugerencia sugerencia;
		
		loteSugerencia = loteSugerenciaDao.findById(id).orElse(null);
		sugerencia = sugerenciaService.findById(loteSugerencia.getSugerencia().getId());
		sugerencia.setLoteNombre(null);
		sugerenciaService.save(sugerencia);
		
		//
		
		loteSugerenciaDao.deleteById(id);

	}

	@Override
	@Transactional
	public LoteSugerencia saveLoteSugerencia(LoteSugerencia loteSugerencia) {
		
		// AFL adaptacion a artefluido
		// 
		Sugerencia sugerenciaAct;
		
		sugerenciaAct = sugerenciaService.findById(loteSugerencia.getSugerencia().getId());
		sugerenciaAct.setLoteNombre(loteSugerencia.getLote().getLabel());
		sugerenciaService.save(sugerenciaAct);
		//
		
		return loteSugerenciaDao.save(loteSugerencia);
	}



	@Override
	public Set<Lote> findAllByLabelVisible(boolean visible) {
		return loteDao.findByVisibleIsOrderByLabel(visible);
	}

}
