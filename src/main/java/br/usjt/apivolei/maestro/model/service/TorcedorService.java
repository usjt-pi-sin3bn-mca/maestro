package br.usjt.apivolei.maestro.model.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.usjt.apivolei.maestro.model.bean.Torcedor;
import br.usjt.apivolei.maestro.model.repository.TorcedorRepository;
import br.usjt.apivolei.maestro.model.util.ResponseUtils;

@Service
public class TorcedorService {

	@Autowired
	private TorcedorRepository repo;

	public ResponseEntity<?> cadastrar(Torcedor torcedor, HttpServletRequest request) {
		torcedor.setContaAtiva(true);
		torcedor.setSocio(false);
		repo.save(torcedor);
		return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
				"Cadastro realizado", "uri=" + request.getRequestURI()), 201);
	}

	public ResponseEntity<?> logar(Torcedor usuario, HttpServletRequest request) {
		if (usuario.getEmail() != null && usuario.getSenha() != null) {
			Torcedor torcedor = repo.findOneByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
			if (torcedor != null) {
				return ResponseUtils.getInstanceResponseEntity(torcedor, 200);
			}
		}
		return ResponseUtils.getInstanceResponseEntity(
				ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Acesso negado", "uri=" + request.getRequestURI()),
				404);
	}

	public ResponseEntity<?> getTorcedor(Long id) {
		return ResponseUtils.getInstanceResponseEntity(repo.findById(id).get(), 200);
	}

	public ResponseEntity<?> souSocio(Long id, HttpServletRequest request) {
		Map<String, Object> retorno = new HashMap<String, Object>();
		retorno.put("id", id);
		retorno.put("socio", repo.findById(id).get().isSocio());
		return ResponseUtils.getInstanceResponseEntity(retorno, 200);
	}

	@SuppressWarnings("unlikely-arg-type")
	public ResponseEntity<?> serSocio(Long id, Torcedor socio, HttpServletRequest request) {
		if (	socio.getCelular() != null && !"".equals(socio.getCelular()) && 
				socio.getCpf() != null && !"".equals(socio.getCpf())     && 
				socio.getDataNascimento() != null && !"".equals(socio.getDataNascimento()) &&
				socio.getEndereco() != null && !"".equals(socio.getEndereco()) &&
				("M".equals(socio.getGenero()) || "F".equals(socio.getGenero()) || "O".equals(socio.getGenero()))) {

			Torcedor torcedor = repo.findById(id).get();
			torcedor.setSocio(true);
			torcedor.setCpf(socio.getCpf());
			torcedor.setDataNascimento(socio.getDataNascimento());
			torcedor.setEndereco(socio.getEndereco());
			torcedor.setCelular(socio.getCelular());
			torcedor.setGenero(socio.getGenero());
			repo.save(torcedor);
			return ResponseUtils.getInstanceResponseEntity(
					ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Sucesso", "uri=" + request.getRequestURI()),
					200);
		}
		return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
				"Parametros inválidos", "uri=" + request.getRequestURI()), 400);
	}

	public ResponseEntity<?> desativarConta(Long id, HttpServletRequest request) {
		Torcedor torcedor = repo.findById(id).get();
		torcedor.setContaAtiva(false);
		repo.save(torcedor);
		return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
				"Conta desativada", "uri=" + request.getRequestURI()), 200);
	}

	public ResponseEntity<?> ativarConta(Long id, HttpServletRequest request) {
		Torcedor torcedor = repo.findById(id).get();
		torcedor.setContaAtiva(true);
		repo.save(torcedor);
		return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
				"Conta ativada", "uri=" + request.getRequestURI()), 200);
	}

	public ResponseEntity<?> alterarDados(Long id, Torcedor torcedorParam, HttpServletRequest request) {
		Torcedor torcedor = repo.findById(id).get();
		torcedor.setNome(torcedorParam.getNome());
		torcedor.setEmail(torcedorParam.getEmail());
		if (torcedor.isSocio()) {
			torcedor.setCelular(torcedorParam.getCelular());
			torcedor.setCpf(torcedorParam.getCpf());
			torcedor.setDataNascimento(torcedorParam.getDataNascimento());
			torcedor.setEndereco(torcedorParam.getEndereco());
			torcedor.setGenero(torcedorParam.getGenero());
		}
		repo.save(torcedor);
		return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
				"Dados alterados", "uri=" + request.getRequestURI()), 200);
		
	}

	public ResponseEntity<?> excluir(Long id, HttpServletRequest request) {
		repo.delete(repo.findById(id).get());
		return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
				"Objeto excluído", "uri=" + request.getRequestURI()), 200);
	}

	public ResponseEntity<?> naoSerSocio(Long id, HttpServletRequest request) {
		Torcedor torcedor = repo.findById(id).get();
		torcedor.setSocio(false);
		repo.save(torcedor);
		return ResponseUtils.getInstanceResponseEntity(
				ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Sucesso", "uri=" + request.getRequestURI()),
				200);
	}
}