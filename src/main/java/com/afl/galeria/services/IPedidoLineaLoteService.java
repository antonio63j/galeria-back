package com.afl.galeria.services;

import com.afl.galeria.entities.Pedido;
import com.afl.galeria.entities.PedidoLineaLote;

public interface IPedidoLineaLoteService {

	public void deleteById (Long id);
	
	public PedidoLineaLote save (PedidoLineaLote pedidoLineaLote);
	
	public Pedido deleteLineaLoteId(Long idPedido, Long idLineaLote);
}
