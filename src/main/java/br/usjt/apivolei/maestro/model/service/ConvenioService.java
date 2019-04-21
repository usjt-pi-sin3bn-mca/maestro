package br.usjt.apivolei.maestro.model.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.usjt.apivolei.maestro.model.bean.Convenio;
import br.usjt.apivolei.maestro.model.repository.ConvenioRepository;
import br.usjt.apivolei.maestro.model.util.ResponseUtils;

@Service
public class ConvenioService {

	@Autowired
	private ConvenioRepository repo;
	
	public ResponseEntity<?> cadastrar(Convenio convenio, HttpServletRequest request) {
		repo.save(convenio);
		return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
				"Cadastro realizado", "uri=" + request.getRequestURI()), 201);
	}
	
	public ResponseEntity<?> excluir(Long id, HttpServletRequest request) {
		repo.delete(repo.findById(id).get());
		return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
				"Objeto excluído", "uri=" + request.getRequestURI()), 200);
	}
	
	public ResponseEntity<?> alterarDados(Long id, Convenio convenioParam, HttpServletRequest request) {
		Convenio convenio = repo.findById(id).get();
		convenio.setNome(convenioParam.getNome());
		convenio.setCpf(convenioParam.getCpf());
		convenio.setNomeReponsavel(convenioParam.getNomeReponsavel());
		convenio.setFone(convenioParam.getFone());
		convenio.setEmail(convenioParam.getEmail());
		convenio.setEndereco(convenioParam.getEndereco());
		repo.save(convenio);
		return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
				"Dados alterados", "uri=" + request.getRequestURI()), 200);
	}
	
	public ResponseEntity<?> listarConvenios(Long id) {
		return ResponseUtils.getInstanceResponseEntity(repo.findById(id).get(), 200);
	}

}
