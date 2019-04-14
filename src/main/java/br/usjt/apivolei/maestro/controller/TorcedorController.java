package br.usjt.apivolei.maestro.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.apivolei.maestro.model.bean.SocioTorcedor;
import br.usjt.apivolei.maestro.model.bean.Torcedor;
import br.usjt.apivolei.maestro.model.bean.Usuario;
import br.usjt.apivolei.maestro.model.service.TorcedorService;
import br.usjt.apivolei.maestro.model.service.auth.Auth;
import br.usjt.apivolei.maestro.model.util.ResponseUtils;

@RestController
@RequestMapping("/torcedor")
public class TorcedorController {

	@Autowired
	private TorcedorService service;

	@RequestMapping(value = "/isSocio/{token}", method = RequestMethod.GET)
	public ResponseEntity<?> isSocio(@PathVariable String token, HttpServletRequest request) {
		if (Auth.isValidToken(token)) {
			Map<String, Object> retorno = new HashMap<String, Object>();
			retorno.put("token", token);
			retorno.put("socio", service.getTorcedor(Auth.getId(token)).isSocio() ? true : false);
			return ResponseUtils.getInstanceResponseEntity(retorno, 200);
		}
		return ResponseUtils.getInstanceResponseEntity(
				ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Token inválido", "uri=" + request.getRequestURI()),
				404);
	}

	@RequestMapping(value = "/logar", method = RequestMethod.POST)
	public ResponseEntity<?> logar(@RequestBody Usuario usuario, HttpServletRequest request) {
		Torcedor torcedor = service.logar(usuario);
		if (torcedor != null) {
			return ResponseUtils.getInstanceResponseEntity(torcedor, 200);
		}
		return ResponseUtils.getInstanceResponseEntity(
				ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Acesso negado", "uri=" + request.getRequestURI()),
				404);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<?> logout(@RequestBody Usuario usuario, HttpServletRequest request) {
		if (service.logout(usuario)) {
			return ResponseUtils.getInstanceResponseEntity(
					ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Logout executado", "uri=" + request.getRequestURI()),
					200);
		}
		return ResponseUtils.getInstanceResponseEntity(
				ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Token não encontrado", "uri=" + request.getRequestURI()),
				404);
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrar(@RequestBody Torcedor torcedor, HttpServletRequest request) {
		service.cadastrar(torcedor);
		return ResponseUtils.getInstanceResponseEntity(
				ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Cadastro realizado", "uri=" + request.getRequestURI()),
				200);
		
	}

	@RequestMapping(value = { "/tornarSocio", "/alterarDadosSocio" }, method = RequestMethod.POST)
	public ResponseEntity<?> tornarSocio(@RequestBody SocioTorcedor socio, HttpServletRequest request) {
		if (service.tornarSocio(socio)) {
			return ResponseUtils.getInstanceResponseEntity(
					ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Sucesso", "uri=" + request.getRequestURI()),
					200);
		}
		return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
				"Parametros inválidos", "uri=" + request.getRequestURI()), 400);
	}

//	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET)
//	public ResponseEntity<?> listar(@PathVariable("id") Long id) {
//		return ResponseUtils.getInstanceResponseEntity(service.getTorcedor(id), 200);
//	}

}