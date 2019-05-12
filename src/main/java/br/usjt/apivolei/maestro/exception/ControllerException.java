package br.usjt.apivolei.maestro.exception;

import java.util.Date;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.usjt.apivolei.maestro.model.util.ResponseUtils;

@RestControllerAdvice
public class ControllerException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	  public final ResponseEntity<?> noSuchElement(WebRequest request) {
		return ResponseUtils.getInstanceResponseEntity(
				ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Objeto não encontrado", request.getDescription(false)), 
				404);
	  }
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<?> nullPointer(WebRequest request) {
		return ResponseUtils.getInstanceResponseEntity(
				ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Parametros inválidos", request.getDescription(false)), 
				400);
	  }
	
	
}