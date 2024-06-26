package com.afl.galeria.services;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.afl.galeria.entities.EnumEstadoPedido;
import com.afl.galeria.entities.Pedido;
import com.afl.galeria.entities.Sugerencia;

public interface IPedidoService {

	public Pedido findById(Long id);

	// para carrito
	public Pedido save(Pedido pedido);
	
	public Pedido addSugerencia(Pedido pedido, Long sugerenciaId) throws Exception;
	
	public Pedido addLote(Pedido pedido, Long loteId) throws Exception;
	
	public Pedido savePedido (Pedido pedido);

	public void deleteById(Long id);
	
	public Set<Pedido> findByUsuarioEstadoCreacion(String usuario, EnumEstadoPedido estado);

	public Pedido confirmarPedido(Pedido pedido);
	
	public Page<Pedido> findAll (Specification<Pedido> especification, Pageable pageable);

	
}
