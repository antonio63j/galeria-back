package com.afl.galeria.modelos;

import java.io.Serializable;
import lombok.Data;

@Data
public class EmailContactoCliente implements Serializable {

	private String email;
	private String nombre;
	private String telefono;
	private String mensaje;
	
}
