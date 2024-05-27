package com.afl.galeria.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afl.galeria.dao.ILoteSugerenciaDao;
import com.afl.galeria.dao.ISugerenciaDao;
import com.afl.galeria.entities.LoteSugerencia;
import com.afl.galeria.entities.Sugerencia;
import com.afl.galeria.entities.specification.SearchCriteria;
import com.afl.galeria.entities.specification.SearchOperation;
import com.afl.galeria.entities.specification.SugerenciaSpecification;

@Service
public class SugerenciaService implements ISugerenciaService {
	

	@Autowired
	private ISugerenciaDao sugerenciaDao;
	
	@Autowired
	private ILoteSugerenciaDao loteSugerenciaDao;
	
	@Override
	@Transactional (readOnly = true)
	public Page<Sugerencia> findAll(Pageable pageable) {
		return sugerenciaDao.findAll(pageable);
	}

	@Override
	@Transactional (readOnly = true)
	public Set<Sugerencia> findAllByLabel() {
		return sugerenciaDao.findAllByLabel();
	}

	@Override
	@Transactional (readOnly = true)
	public Sugerencia findById(Long id) {
		return sugerenciaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Sugerencia save(Sugerencia sugerencia) {
		return sugerenciaDao.save(sugerencia);
	}
	
	@Override
	@Transactional
	public void deleteById (Long id) {
		
		// eliminar entradas en tabla LoteSugerencia
		
		List<LoteSugerencia> loteSugerencias = loteSugerenciaDao.findLoteSugerencias(id);
		loteSugerenciaDao.deleteAll(loteSugerencias);
		sugerenciaDao.deleteById(id);
	}

	@Override
	@Transactional (readOnly = true)
	public Page<Sugerencia> findAll(Specification<Sugerencia> especification, Pageable pageable) {
		return sugerenciaDao.findAll (especification, pageable);
	}
	
	
//	public Page<Sugerencia> findAll (SugerenciaSearch sugerenciaSearch, Pageable pageable){
//        SugerenciaSpecification espec = new SugerenciaSpecification();
//        espec.add(new SearchCriteria("label", sugerenciaSearch.getLabel(), SearchOperation.MATCH));
//
//        return findAll (espec, pageable);
//	}

//	@Override
//	@Transactional
//	public void deleteLoteSugerenciaById(Long id) {
//		loteSugerenciaDao.deleteById(id);
//
//	}

}
