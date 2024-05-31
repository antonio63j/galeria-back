package com.afl.galeria.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.afl.galeria.entities.Lote;
import com.afl.galeria.entities.Tiposugerencia;

public interface ILoteDao extends JpaRepository<Lote, Long> {

    @Query ("select m from Lote m order by m.label asc")
    public Set<Lote> findAllByLabel();
  
	public Set<Lote> findByVisibleIsOrderByLabel(boolean b);
	

	
}
