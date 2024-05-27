package com.afl.galeria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afl.galeria.dao.ITipoplatoDao;
import com.afl.galeria.entities.Tiposugerencia;

@Service
public class TipoplatoSerivice implements ITipoplatoService {

	@Autowired ITipoplatoDao tipoplatoDao;
	
	@Override
	@Transactional (readOnly = true)
	public List<Tiposugerencia> findAllByNombreTipo() {
		
		return tipoplatoDao.findAllByNombreTipo();
	}

	@Override
	@Transactional (readOnly = true)
	public Tiposugerencia findById(Long id) {

		return tipoplatoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Tiposugerencia save(Tiposugerencia tiposugerencia) {
		
		return tipoplatoDao.save(tiposugerencia);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		tipoplatoDao.deleteById(id);
	}

}
