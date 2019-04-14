package br.usjt.apivolei.maestro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.apivolei.maestro.model.bean.Torcedor;
import br.usjt.apivolei.maestro.model.service.TorcedorService;
import br.usjt.apivolei.maestro.model.util.ResponseUtils;

@RestController
@RequestMapping("/api")
public class TorcedorController {
	
	@Autowired
	private TorcedorService service;

	@RequestMapping(value = "/torcedor", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrar(@RequestBody Torcedor torcedor, HttpServletRequest request) {
		return service.cadastrar(torcedor, request);
	}

	@RequestMapping(value = "/torcedor/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> alterarDados(@PathVariable Long id, @RequestBody Torcedor torcedor, HttpServletRequest request) {
		return service.alterarDados(id, torcedor, request);
	}
	
	@RequestMapping(value = "/torcedor/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> excluir(@PathVariable Long id, HttpServletRequest request) {
		return service.excluir(id, request);
	}

	@RequestMapping(value = "/torcedor/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable("id") Long id) {
		return ResponseUtils.getInstanceResponseEntity(service.getTorcedor(id), 200);
	}

	@RequestMapping(value = "/torcedor/logar", method = RequestMethod.POST)
	public ResponseEntity<?> logar(@RequestBody Torcedor usuario, HttpServletRequest request) {
		return service.logar(usuario, request);
	}

	@RequestMapping(value = { "/torcedor/sersocio/{id}" }, method = RequestMethod.PUT)
	public ResponseEntity<?> serSocio(@PathVariable Long id, @RequestBody Torcedor socio, HttpServletRequest request) {
		return service.serSocio(id, socio, request);
	}
	
	@RequestMapping(value = { "/torcedor/naosersocio/{id}" }, method = RequestMethod.PUT)
	public ResponseEntity<?> naoSerSocio(@PathVariable Long id, HttpServletRequest request) {
		return service.naoSerSocio(id, request);
	}

	@RequestMapping(value = "/torcedor/sousocio/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> souSocio(@PathVariable Long id, HttpServletRequest request) {
		return service.souSocio(id, request);
	}

	@RequestMapping(value = "/torcedor/desativar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> desativarConta(@PathVariable Long id, HttpServletRequest request) {
		return service.desativarConta(id, request);
	}
	
	@RequestMapping(value = "/torcedor/ativar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> ativarConta(@PathVariable Long id, HttpServletRequest request) {
		return service.ativarConta(id, request);
	}
	
}