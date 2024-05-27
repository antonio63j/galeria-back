package com.afl.galeria.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "tiposugerencia")
public class Tiposugerencia implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotNull
	@NotEmpty
	private String nombre;
	
	private String imgFileName;
	
    private String label;
    
    private String descripcion; 
}
