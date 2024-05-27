package com.afl.galeria.entities;
import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@NoArgsConstructor 
@Getter 
@Setter

@Entity

//@Table(name="pedido_linea_Lote", uniqueConstraints = {
//	    @UniqueConstraint(columnNames = { "Lote_id", "primero_id", "segundo_id", "postre_id", "pedido_id"  })
//	})
public class PedidoLineaLote{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private int cantidad;
    
    @NotNull
    private double precioInicio;
     
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lote lote;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    
    /* AFL adaptacion para artefluido

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sugerencia primero;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sugerencia segundo;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sugerencia postre;
    
    */ 
    
    	@ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Sugerencia primero;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Sugerencia segundo;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Sugerencia postre;
    
    
    

    
    

    
    @Override
	public String toString() {
		return "PedidoLineaLote [id=" + id + ", cantidad=" + cantidad + ",precioInicio=" + precioInicio + "lote=" + lote + ", primero=" + primero
				+ ", segundo=" + segundo + ", postre=" + postre + "]";
	}
    
    
}
