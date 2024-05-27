package com.afl.galeria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afl.galeria.dao.IPedidoDao;
import com.afl.galeria.dao.IPedidoLineaLoteDao;
import com.afl.galeria.entities.Pedido;
import com.afl.galeria.entities.PedidoLineaLote;

@Service

public class PedidoLineaLoteService implements IPedidoLineaLoteService {

	@Autowired IPedidoLineaLoteDao pedidoLineaLoteDao;
	
	@Autowired IPedidoDao pedidoDao;

	
	@Override
	@Transactional
	public void deleteById(Long id) {
		pedidoLineaLoteDao.deleteById (id);

	}

	@Override
	@Transactional
	public PedidoLineaLote save(PedidoLineaLote pedidoLineaLote) {
		return pedidoLineaLoteDao.save (pedidoLineaLote);
	}

	@Override
	@Transactional
	public Pedido deleteLineaLoteId(Long idPedido, Long idLineaLote) {
		pedidoLineaLoteDao.deleteById(idLineaLote);
		
		pedidoLineaLoteDao.flush();
		
		Pedido carrito = pedidoDao.findById(idPedido).orElse(null);
		
		if (carrito == null | (carrito.getPedidoLineaSugerencias().size() == 0 & carrito.getPedidoLineaLotes().size() == 0)) {
			pedidoDao.deleteById(idPedido);
			return null;
		} else {
			// carrito.setCalculos();
			return pedidoDao.saveAndFlush(carrito);
		}
	}
	
	

}
