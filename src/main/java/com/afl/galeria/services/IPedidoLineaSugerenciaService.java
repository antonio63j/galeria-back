package com.afl.galeria.services;

import java.util.Set;

import com.afl.galeria.entities.Pedido;
import com.afl.galeria.entities.PedidoLineaSugerencia;

public interface IPedidoLineaSugerenciaService {
	
	public void deleteById (Long id);

	public PedidoLineaSugerencia save (PedidoLineaSugerencia PedidoLineaSugerencia);
	
	public Pedido deleteLineaSugerenciaById(Long idPedido, Long idLineaSugerencia);
	
}