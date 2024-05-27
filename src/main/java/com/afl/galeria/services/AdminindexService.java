package com.afl.galeria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afl.galeria.dao.IAdminindexDao;
import com.afl.galeria.entities.Adminindex;
import com.afl.galeria.entities.Empresa;

@Service
public class AdminindexService implements IAdminindexService {
	
	@Autowired 
	IAdminindexDao adminindexDao;

	@Override
	public List<Adminindex> findAll() {
		// TODO Auto-generated method stub
		return adminindexDao.findAll();
	}




}
