package com.afl.galeria.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.afl.galeria.entities.EnumComponenteLote;
import com.afl.galeria.entities.Lote;
import com.afl.galeria.entities.LoteSugerencia;
import com.afl.galeria.entities.Sugerencia;
import com.afl.galeria.entities.Tiposugerencia;
import com.afl.galeria.services.ILoteService;
import com.afl.galeria.services.ISugerenciaService;
import com.afl.galeria.services.ITipoplatoService;
import com.afl.galeria.services.files.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")

public class LoteController {

	private Logger log = LoggerFactory.getLogger(LoteController.class);

	@Autowired
	ILoteService loteService;

	@Autowired
	ISugerenciaService sugerenciaService;

	@Autowired
	IUploadFileService uploadFileService;

	@Value("${app.uploadsDir:uploads}")
	private String uploadsDir;

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void messageNotReadableException(HttpMessageNotReadableException exception, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_IMPLEMENTED.value(), exception.getMessage());
	}

//		@Secured({"ROLE_ADMIN"})
	@PostMapping("/lote/create")
	public ResponseEntity<?> createLote(@Valid @RequestBody Lote lote, BindingResult result) {
		Lote loteNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(fielderror -> "El campo '" + fielderror.getField() + "' " + fielderror.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			lote.setImgFileName("no-photo.png");
			loteNew = loteService.save(lote);
		} catch (DataAccessException e) {
			response.put("mensaje", "error en el acceso a la base de datos, no ha sido posible persistir el objeto");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			log.error(response.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "creado lote");
		response.put("data", loteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

//	@Secured({"ROLE_ADMIN"})
	@PostMapping("/lotesugerencia/create")
	public ResponseEntity<?> createLoteSugerencia(@RequestBody Lote loteAct,
			@RequestParam(value = "sugerenciaId", required = true) Long sugerenciaId,
//			@RequestParam(value = "primerPlato", required = true) boolean primerPlato) {
	    	@RequestParam(value = "componenteLote", required = true) EnumComponenteLote componenteLote,
	    	Authentication authentication, Principal principal) {

		Map<String, Object> response = new HashMap<>();
		Lote loteFinal;
		try {

            /*
	        System.out.println(authentication.getName());
	        System.out.println("-----------------");
	        System.out.println(principal.getName());
	        */
			
			log.debug("autentication.getName():");
			log.debug(authentication.getName());
			
							     
	        Sugerencia sugerencia = sugerenciaService.findById(sugerenciaId);
			LoteSugerencia loteSugerencia = new LoteSugerencia(loteAct, sugerencia, componenteLote);
			loteService.saveLoteSugerencia(loteSugerencia);
			
			
			loteFinal = loteService.findById(loteAct.getId());
			response.put("mensaje", "creada nueva sugerencia al Lote");
			response.put("data", loteFinal);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		} catch (DataAccessException e) {

			response.put("mensaje", "error no ha sido posible persistir el objeto");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			log.error(response.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/lote/list")
	public Set<Lote> findAllLotes() {
		return loteService.findAllByLabel();
	}
	
	@GetMapping("/lote/list-visible")
	public Set<Lote> findAllLotesVisibles() {
		return loteService.findAllByLabelVisible(new Boolean(true));
	}
	

//		@Secured({"ROLE_ADMIN"})
	@PutMapping("/lote/update")
	public ResponseEntity<?> update(@Valid @RequestBody Lote lote, BindingResult result) {

		Lote loteUpdated = null;
		Lote loteActual = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(fielderror -> "El campo '" + fielderror.getField() + "' " + fielderror.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		loteActual = loteService.findById(lote.getId());
		if (loteActual == null) {
			response.put("mensaje",
					"lote con id=".concat(lote.getId().toString().concat(" no está en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			loteActual.setLabel(lote.getLabel());
			loteActual.setDescripcion(lote.getDescripcion());
			loteActual.setPrecio(lote.getPrecio());
            loteActual.setVisible(lote.isVisible());
            loteActual.setIndivisible(lote.isIndivisible());
			loteUpdated = loteService.save(loteActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "error al actualizar Lote con id=".concat(lote.getId().toString()));
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "sin error al actualizar Lote con id=".concat(lote.getId().toString()));
		response.put("data", loteUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// @Secured({"ROLE_ADMIN"})
	@DeleteMapping("/lotesugerencia/{id}")
	public ResponseEntity<?> deleteLoteSugerencia(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			loteService.deleteLoteSugerenciaById(id);
		} catch (DataAccessException e) {
			response.put("mensaje",
					"lote sugerencia id=".concat(id.toString().concat(" error al eliminar en la base de datos")));
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "lote sugerencia id=".concat(id.toString().concat(" eliminado de la base de datos")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// @Secured({"ROLE_ADMIN"})
	@DeleteMapping("/lote/{id}")
	public ResponseEntity<?> deleteLote(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			loteService.deleteById(id);
		} catch (DataAccessException e) {
			response.put("mensaje",
					"lote id=".concat(id.toString().concat(" error al eliminar en la base de datos")));
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "lote id=".concat(id.toString().concat(" eliminado de la base de datos")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/lote/{id}")
	public ResponseEntity<?> getLoteById (@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
	    Lote lote;
		try {
			lote = loteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje",
					"lote id=".concat(id.toString().concat(" error acceso a la base de datos")));
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "acceso satisfactorio a lote con id=".concat(lote.getId().toString()));
		response.put("data", lote);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	// @Secured({"ROLE_ADMIN"})
	@PostMapping("/lote/uploads/img")
	public ResponseEntity<?> uploadFoto(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

		Lote lote;
		Map<String, Object> response = new HashMap<>();

		log.debug("id=" + id.toString());

		lote = loteService.findById(id);
		if (lote == null) {
			response.put("mensaje", "lote con id=".concat(id.toString().concat(" no está en la base de datos")));
			response.put("error", "lote con id=".concat(id.toString().concat(" no está en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		String nombreArchivo = null;
		if (!archivo.isEmpty()) {
			nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = uploadFileService.getPath(uploadsDir + File.separator + "lotes", nombreArchivo);
			try {
				uploadFileService.copia(rutaArchivo, archivo, nombreArchivo);
			} catch (IOException e) {
				e.printStackTrace();
				response.put("mensaje", "lote con id=".concat(id.toString().concat(" error al subir imagen")));
				response.put("error", "IOException");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = lote.getImgFileName();
			uploadFileService.eliminar(uploadsDir + File.separator + "lotes", nombreFotoAnterior);
			lote.setImgFileName(nombreArchivo);
			loteService.save(lote);
			response.put("data", lote);
			response.put("mensaje", "lote id=".concat(id.toString().concat(" upload img OK")));
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/lote/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		return verFotoGenerico(Paths.get(uploadsDir + "/lotes").resolve(nombreFoto).toAbsolutePath());

	}

	private ResponseEntity<Resource> verFotoGenerico(Path path) {
		Resource resource = null;
		try {
			log.debug("path:");
			log.debug(path.toString());
			resource = uploadFileService.salidaFichero(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
		return new ResponseEntity<Resource>(resource, cabecera, HttpStatus.OK);

	}
}
