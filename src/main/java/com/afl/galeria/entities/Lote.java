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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@NoArgsConstructor 
@Getter 
@Setter

@Entity
public class Lote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 4, max=100, message="debe tener de 4 a 100 posiciones") 
    @Column(name = "label", unique = true)
    private String label;

	private String imgFileName;
	
	@NotNull
	private boolean visible;
	
	@NotNull
	private boolean indivisible;
		
	@Column(columnDefinition="TEXT")
	private String descripcion;
	
	@NotNull
	@Column(name = "precio")
    private Double precio;
	
	private EnumEstadoArticulo estado;
	
	private LocalDateTime fechaCambioEstado;

	    
    // @JsonIgnoreProperties(value={"lote", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(mappedBy = "lote", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LoteSugerencia> loteSugerencias;


}
