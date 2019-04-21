package br.usjt.apivolei.maestro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.apivolei.maestro.model.bean.Convenio;
import br.usjt.apivolei.maestro.model.service.ConvenioService;
import br.usjt.apivolei.maestro.model.util.ResponseUtils;

@RestController
@RequestMapping("/api")
public class ConvenioController {
	
	@Autowired
	private ConvenioService service;
	
	@RequestMapping(value = "/convenio", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrar(@RequestBody Convenio convenio, HttpServletRequest request) {
		return service.cadastrar(convenio, request);
	}
	
	@RequestMapping(value = "/convenio/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> excluir(@PathVariable Long id, HttpServletRequest request) {
		return service.excluir(id, request);
	}

	@RequestMapping(value = "/convenio/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> alterarDados(@PathVariable Long id, @RequestBody Convenio convenio, HttpServletRequest request) {
		return service.alterarDados(id, convenio, request);
	}
	@RequestMapping(value = "/convenio/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable("id") Long id) {
		return ResponseUtils.getInstanceResponseEntity(service.listarConvenios(id), 200);
	}
	
	
}
