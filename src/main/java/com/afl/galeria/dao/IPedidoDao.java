package com.afl.galeria.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.afl.galeria.entities.EnumEstadoPedido;
import com.afl.galeria.entities.Pedido;
import com.afl.galeria.entities.Sugerencia;

@Repository
public interface IPedidoDao extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

//   @Query("from Pedido p where p.usuario = ?1 and p.estadoPedido in (?2) order by p.fechaRegistro desc")	
    @Query("from Pedido p where p.usuario = ?1 and p.estadoPedido in (?2)")	

	Set<Pedido> findByUsuarioContainigIgnoreCaseAndEstadoPedidoContainingIgnoreCase_2(String usuario, EnumEstadoPedido estado);

   
}
