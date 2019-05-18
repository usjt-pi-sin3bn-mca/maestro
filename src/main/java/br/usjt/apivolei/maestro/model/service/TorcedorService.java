package br.usjt.apivolei.maestro.model.service;

import java.net.URI;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.usjt.apivolei.maestro.model.bean.DetalhesRetorno;
import br.usjt.apivolei.maestro.model.bean.Torcedor;
import br.usjt.apivolei.maestro.model.repository.TorcedorRepository;

@Service
public class TorcedorService {

	@Autowired
	private DetalhesRetorno retorno;
	
	@Autowired
	private TorcedorRepository repo;

	public ResponseEntity<?> cadastrar(Torcedor torcedor, HttpServletRequest request) {

		torcedor.setContaAtiva(true);
		torcedor.setSocio(false);
		Torcedor t = repo.save(torcedor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(t.getId()).toUri();

		return ResponseEntity.created(uri).body(this.retorno.build(new Date(), "Cadastro realizado", "uri=" + request.getRequestURI()));
	}

	public ResponseEntity<?> logar(Torcedor usuario, HttpServletRequest request) {
		
		if (usuario.getEmail() != null && usuario.getSenha() != null) {
			
			Torcedor torcedor = repo.findOneByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
			
			if (torcedor != null) {
				
				if (torcedor.isContaAtiva()) {
					
					return ResponseEntity.ok(torcedor);
				} else {
					return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "A conta está desativada", "uri="+request.getRequestURI()));
				}
			}
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.retorno.build(new Date(), "Acesso negado", "uri="+request.getRequestURI()));
	}

	public ResponseEntity<?> getTorcedor(Long id) {
		return ResponseEntity.ok().header("Content-Type", MediaType.APPLICATION_JSON.toString()).body(repo.findById(id).get());
	}

	public Torcedor buscarTorcedor(Long id) {
		return repo.findByContaAtiva(id, true).orElseThrow(NoSuchElementException::new);
	}

	public ResponseEntity<?> souSocio(Long id, HttpServletRequest request) {
		
		Torcedor torcedor = repo.findById(id).get();
		
		if (torcedor.isContaAtiva()) {
			
			Map<String, Object> retorno = new HashMap<String, Object>();
			retorno.put("id", id);
			retorno.put("socio", repo.findById(id).get().isSocio());
			return ResponseEntity.ok(retorno);
		}
		
		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "A conta está desativada", "uri="+request.getRequestURI()));
	}

	@SuppressWarnings("unlikely-arg-type")
	public ResponseEntity<?> serSocio(Long id, Torcedor socio, HttpServletRequest request) {
		if (camposObrigatoriosOK(socio)) {
			Torcedor torcedor = repo.findById(id).get();
			
			if (torcedor.isContaAtiva()) {

				if (!torcedor.isSocio()) {

					torcedor.setSocio(true);
					torcedor.setCpf(socio.getCpf());
					torcedor.setDataNascimento(socio.getDataNascimento());
					torcedor.setEndereco(socio.getEndereco());
					torcedor.setCelular(socio.getCelular());
					torcedor.setGenero(socio.getGenero());
					torcedor.setPontos(0);
					repo.save(torcedor);
					
					return ResponseEntity.ok(this.retorno.build(new Date(), "Sucesso", "uri=" + request.getRequestURI()));
				} else {
					return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "Usuário já é sócio", "uri=" + request.getRequestURI()));
				}
			} else {
				return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "A conta está desativada", "uri="+request.getRequestURI()));
			}
		}
		
		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "Parametros inválidos", "uri=" + request.getRequestURI()));
	}

	private boolean camposObrigatoriosOK(Torcedor socio) {
		return socio.getCelular() != null
				&& !"".equals(socio.getCelular())
				&& socio.getCpf() != null
				&& !"".equals(socio.getCpf())
				&& socio.getDataNascimento() != null
				&& !"".equals(socio.getDataNascimento())
				&& socio.getEndereco() != null
				&& !"".equals(socio.getEndereco())
				&& ("M".equals(socio.getGenero()) || "F".equals(socio.getGenero()) || "O".equals(socio.getGenero()));
	}

	public ResponseEntity<?> desativarConta(Long id, HttpServletRequest request) {
		
		Torcedor torcedor = repo.findById(id).get();
		
		if (torcedor.isContaAtiva()) {
			torcedor.setContaAtiva(false);
			repo.save(torcedor);
			return ResponseEntity.ok(this.retorno.build(new Date(), "Sucesso, conta desativada", "uri=" + request.getRequestURI()));
		}
		
		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "Conta já está desativada", "uri=" + request.getRequestURI()));
	}

	public ResponseEntity<?> ativarConta(Long id, HttpServletRequest request) {
		
		Torcedor torcedor = repo.findById(id).get();
		
		if (!torcedor.isContaAtiva()) {
			torcedor.setContaAtiva(true);
			repo.save(torcedor);
			return ResponseEntity.ok(this.retorno.build(new Date(), "Sucesso, conta ativada", "uri=" + request.getRequestURI()));
		}
		
		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "Conta já está ativada", "uri=" + request.getRequestURI()));
	}

	public ResponseEntity<?> alterarDados(Long id, Torcedor torcedorParam, HttpServletRequest request) {
		
		Torcedor torcedor = repo.findById(id).get();
		
		if (torcedor.isContaAtiva()) {
			
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
			return ResponseEntity.ok(this.retorno.build(new Date(), "Dados alterados", "uri=" + request.getRequestURI()));
		}

		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "A conta está desativada", "uri="+request.getRequestURI()));
	}

	public ResponseEntity<?> excluir(Long id, HttpServletRequest request) {
		repo.delete(repo.findById(id).get());
		return ResponseEntity.ok(this.retorno.build(new Date(), "Objeto excluído", "uri=" + request.getRequestURI()));
	}

	public ResponseEntity<?> naoSerSocio(Long id, HttpServletRequest request) {

		Torcedor torcedor = repo.findById(id).get();

		if (torcedor.isContaAtiva()) {

			torcedor.setSocio(false);
			repo.save(torcedor);
			return ResponseEntity.ok(this.retorno.build(new Date(), "Sucesso", "uri=" + request.getRequestURI()));
		}
		
		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "A conta está desativada", "uri="+request.getRequestURI()));
	}
}