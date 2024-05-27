package com.afl.galeria.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.afl.galeria.entities.Lote;
import com.afl.galeria.entities.LoteSugerencia;

public interface ILoteSugerenciaDao extends JpaRepository<LoteSugerencia, Long> {

	@Query ("select ms from LoteSugerencia ms where ms.sugerencia.id = ?1")
	List<LoteSugerencia> findLoteSugerencias(Long id);


//	@Query ("select ms from LoteSugerencia ms where ms.sugerencia_id = ?1")
//	void deleteAllSugerenciaId(Long id);

//	int deleteLoteSugerenciaByIdEquals(Long id);
	
}
