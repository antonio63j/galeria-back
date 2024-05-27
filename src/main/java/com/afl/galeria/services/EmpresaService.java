package com.afl.galeria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afl.galeria.dao.IEmpresaDao;
import com.afl.galeria.entities.Empresa;
import com.afl.galeria.entities.Slider;

@Service
public class EmpresaService implements IEmpresaService {
	
	@Autowired IEmpresaDao empresaDao;

	@Override
	@Transactional (readOnly = true)
	public Empresa findById(Long id) {
		return empresaDao.findById(id).orElse(null);
	}

//	@Override
//	@Transactional
//	public Empresa create(Empresa empresa) {
//		return empresaDao.save(empresa);
//	}

	@Override
	@Transactional
	public Empresa save(Empresa empresa) {
		return empresaDao.save(empresa);
	}
	
	@Override
	@Transactional (readOnly = true)
	public List<Slider> findAllSliders() {
		// TODO Auto-generated method stub
		return empresaDao.findAllSliders();
	}
	
	
//	@Override
//	@Transactional 
//	public Slider save (Slider slider) {
//		return empresaDao.save(slider);
//	}
	
	
}
