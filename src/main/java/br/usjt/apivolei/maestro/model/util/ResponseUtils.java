package br.usjt.apivolei.maestro.model.util;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.usjt.apivolei.maestro.model.bean.DetalhesRetorno;

public class ResponseUtils {
	private static ResponseEntity<?> response;
	private static DetalhesRetorno detalhes;
	
	public static ResponseEntity<?> getInstanceResponseEntity(Object obj, int status) {
		response = new ResponseEntity<>(obj, HttpStatus.valueOf(status));
		return response;
	}
	
	public static DetalhesRetorno getInstanceDetalhesRetorno(Date timestamp, String message, String details) {
		detalhes = new DetalhesRetorno(timestamp, message, details);
		return detalhes;
	}
	
}