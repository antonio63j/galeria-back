package com.afl.galeria.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.afl.galeria.controllers.LoteController;
import com.afl.galeria.formateadores.LocalDateTimeSerializer;
import com.afl.galeria.formateadores.LocalDatetimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//@NoArgsConstructor 
//@Getter 
//@Setter

@Data

@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    //@ManyToOne
    //@JoinColumn(name = "userName", nullable = false)
    private String usuario;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 11)
    private EnumEntregaPedido entregaPedido;
    
    @OneToOne
    @JoinColumn(name="direccion_id")
    private Direccion direccion;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private EnumEstadoPedido estadoPedido;
    
//    @NotNull
//    private Double total;
//    
//    @NotNull
//    private Double numArticulos;
    
	@Column(columnDefinition = "TIMESTAMP")
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="d/M/yyyy, H:m:s")
	private LocalDateTime fechaRegistro;

    @Column(columnDefinition = "TIMESTAMP")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="d/M/yyyy, H:m:s")
	private LocalDateTime fechaEntrega;

	private String nota;
	
//	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
	@OneToMany(fetch=FetchType.EAGER, cascade= {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)

	@JoinColumn(name = "pedido_id")
    private Set<PedidoLineaSugerencia> pedidoLineaSugerencias;

//	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
	@OneToMany(fetch=FetchType.EAGER, cascade= {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
	@JoinColumn(name = "pedido_id")
    private Set<PedidoLineaLote> pedidoLineaLotes;
	
	public Pedido copia() {
		
		Pedido pedidoNew = new Pedido();
		
		pedidoNew.usuario = new String(usuario);
		pedidoNew.estadoPedido = estadoPedido;
		pedidoNew.fechaRegistro = LocalDateTime.from(fechaRegistro);
		pedidoNew.fechaEntrega = LocalDateTime.from(fechaEntrega);
		pedidoNew.nota = nota;
		pedidoNew.pedidoLineaSugerencias = getPedidoLineaSugerencias().stream().collect(Collectors.toSet());
		pedidoNew.pedidoLineaLotes = getPedidoLineaLotes().stream().collect(Collectors.toSet());

		return pedidoNew;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", usuario=" + usuario + ", estadoPedido=" + estadoPedido + ", fechaRegistro="
				+ fechaRegistro + ", fechaEntrega=" + fechaEntrega + ", nota=" + nota
				+ "\n, pedidoLineaSugerencias=\n" + pedidoLineaSugerencias.toString() + ",\n pedidoLineaLotes=\n" + pedidoLineaLotes.toString() + "]\n";
	}
	
//    @JsonGetter("usuario")
//    public Long getTheName() {
//        return this.usuario.getId();
//    }
//    
    
//	@Override
//	public String toString() {
//		return "Pedido [id=" + id + ", usuario=" + usuario + ", estadoPedido=" + estadoPedido
//				+ ", pedidoLineaSugerencias=" + pedidoLineaSugerencias.toString() + ", pedidoLineaLotes=" 
//				+ pedidoLineaLotes.toString() + "]";
//	}
	
	
		
//	public void setCalculos() {
//		Double total = 0.0;
//		Double numArticulos = 0.0;
//		
//		for( PedidoLineaSugerencia pedidoSugerencia : pedidoLineaSugerencias) {
//			total = total + pedidoSugerencia.getSugerencia().getPrecio() * pedidoSugerencia.getCantidad();
//			numArticulos = numArticulos + pedidoSugerencia.getCantidad();
//			
//			
//			System.out.println(" ---- total:" + total);
//			System.out.println(" ---- pedidoSugerencia.getSugerencia().getPrecio():" +  pedidoSugerencia.getSugerencia().getPrecio());
//			System.out.println(" ---- pedidoSugerencia.getCantidad():" +  pedidoSugerencia.getCantidad());
//
//					
//			
//		}
//		
//		for( PedidoLineaLote pedidoLote : pedidoLineaLotes) {
//			total = total + pedidoLote.getLote().getPrecio() * pedidoLote.getCantidad();
//			numArticulos = numArticulos + pedidoLote.getCantidad();
//
//		}
//		
//		this.setTotal(total);
//		this.setNumArticulos(numArticulos);
//
//	}    

}
