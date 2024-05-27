package com.afl.galeria.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor

@Entity

/*
 * // AFL adaptacion para artefluido
@Table(name="lote_sugerencia", uniqueConstraints = {
	    @UniqueConstraint(columnNames = { "lote_id", "sugerencia_id"})
	})
*/

@Table(name="lote_sugerencia", uniqueConstraints = {
	    @UniqueConstraint(columnNames = { "sugerencia_id"})
	})


public class LoteSugerencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id")
    @NotNull
    @JsonIgnore
    private Lote lote;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sugerencia_id")
    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sugerencia sugerencia;

    //private boolean primerPlato;
    @Enumerated(EnumType.STRING)
    @Column(length = 7)
    private EnumComponenteLote componenteLote;
    
    public LoteSugerencia(Lote lote, Sugerencia sugerencia, EnumComponenteLote componenteLote) {
    	this.lote = lote;
        this.sugerencia = sugerencia;
        this.componenteLote = componenteLote;
        
    }

}
