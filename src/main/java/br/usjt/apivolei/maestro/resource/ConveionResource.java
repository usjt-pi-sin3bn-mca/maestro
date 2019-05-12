package br.usjt.apivolei.maestro.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.apivolei.maestro.model.bean.Convenio;
import br.usjt.apivolei.maestro.model.service.ConvenioService;
import br.usjt.apivolei.maestro.model.util.ResponseUtils;

@RestController
@RequestMapping("/api/convenio")
public class ConveionResource {
	@Autowired
	private ConvenioService service;
	
	@PostMapping()
	public ResponseEntity<?> cadastrar(@RequestBody Convenio convenio, HttpServletRequest request) {
		return service.cadastrar(convenio, request);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id, HttpServletRequest request) {
		return service.excluir(id, request);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterarDados(@PathVariable Long id, @RequestBody Convenio convenio, HttpServletRequest request) {
		return service.alterarDados(id, convenio, request);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable("id") Long id) {
		return ResponseUtils.getInstanceResponseEntity(service.listarConvenios(id), 200);
	}
}
