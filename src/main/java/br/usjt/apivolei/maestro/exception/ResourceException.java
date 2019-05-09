package br.usjt.apivolei.maestro.exception;

import java.util.Date;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.usjt.apivolei.maestro.model.bean.DetalhesRetorno;

@RestControllerAdvice
public class ResourceException extends ResponseEntityExceptionHandler {

	@Autowired
	private DetalhesRetorno retorno;
	
	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<?> noSuchElementException(WebRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.retorno.build(new Date(), "Objeto não encontrado", request.getDescription(false)));
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class, TransactionSystemException.class, ConstraintViolationException.class})
	public final ResponseEntity<?> dataIntegrityViolationException(WebRequest request) {
		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "Parametros inválidos", request.getDescription(false)));
	}
	
}