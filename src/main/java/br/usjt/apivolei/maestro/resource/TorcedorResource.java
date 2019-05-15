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

import br.usjt.apivolei.maestro.model.bean.Torcedor;
import br.usjt.apivolei.maestro.model.service.TorcedorService;

@RestController
@RequestMapping("/api/torcedor")
public class TorcedorResource {
	
	@Autowired
	private TorcedorService service;

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody Torcedor torcedor, HttpServletRequest request) {
		return service.cadastrar(torcedor, request);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterarDados(@PathVariable Long id, @RequestBody Torcedor torcedor, HttpServletRequest request) {
		return service.alterarDados(id, torcedor, request);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id, HttpServletRequest request) {
		return service.excluir(id, request);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(service.getTorcedor(id));
	}

	@PostMapping("/logar")
	public ResponseEntity<?> logar(@RequestBody Torcedor usuario, HttpServletRequest request) {
		return service.logar(usuario, request);
	}

	@PutMapping("/sersocio/{id}")
	public ResponseEntity<?> serSocio(@PathVariable Long id, @RequestBody Torcedor socio, HttpServletRequest request) {
		return service.serSocio(id, socio, request);
	}
	
	@PutMapping("/naosersocio/{id}")
	public ResponseEntity<?> naoSerSocio(@PathVariable Long id, HttpServletRequest request) {
		return service.naoSerSocio(id, request);
	}

	@GetMapping("/sousocio/{id}")
	public ResponseEntity<?> souSocio(@PathVariable Long id, HttpServletRequest request) {
		return service.souSocio(id, request);
	}

	@PutMapping("/desativar/{id}")
	public ResponseEntity<?> desativarConta(@PathVariable Long id, HttpServletRequest request) {
		return service.desativarConta(id, request);
	}
	
	@PutMapping("/ativar/{id}")
	public ResponseEntity<?> ativarConta(@PathVariable Long id, HttpServletRequest request) {
		return service.ativarConta(id, request);
	}

	@GetMapping("/pontuacao/{id}")
	public ResponseEntity<?> getPonto(@PathVariable Long id){
		Torcedor torcedor = service.buscarTorcedor(id);

		return ResponseEntity.ok(torcedor.getPontos());
	}
}