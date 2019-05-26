package br.usjt.apivolei.maestro.model.service;

import java.net.URI;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.usjt.apivolei.maestro.model.bean.DetalhesRetorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.usjt.apivolei.maestro.model.bean.Convenio;
import br.usjt.apivolei.maestro.model.repository.ConvenioRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ConvenioService {

	@Autowired
	private ConvenioRepository repo;

	@Autowired
	private DetalhesRetorno detalhesRetorno;

	public ResponseEntity<?> cadastrar(Convenio convenio, HttpServletRequest request) {
		Convenio convenioNovo = repo.save(convenio);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(convenioNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(detalhesRetorno.build(new Date(), "Cadastro realizado com sucesso", "uri=" + request.getRequestURI()));
	}
	
	public ResponseEntity<?> excluir(Long id, HttpServletRequest request) {
		repo.delete(repo.findById(id).get());

		return ResponseEntity.ok("Objeto excluído");
	}
	
	public ResponseEntity<?> alterarDados(Long id, Convenio convenioParam, HttpServletRequest request) {
		Convenio convenio = repo.findById(id).get();
		convenio.setNome(convenioParam.getNome());
		convenio.setCpf(convenioParam.getCpf());
		convenio.setNomeReponsavel(convenioParam.getNomeReponsavel());
		convenio.setFone(convenioParam.getFone());
		convenio.setEmail(convenioParam.getEmail());
		convenio.setEndereco(convenioParam.getEndereco());
		convenio.setPontuacaoQRCode(convenioParam.getPontuacaoQRCode());
		repo.save(convenio);

		return ResponseEntity.ok("Dados alterados");
	}
	
	public ResponseEntity<?> listarConvenios(Long id) {
		Convenio convenio = repo.findById(id).get();

		return ResponseEntity.ok(convenio);
	}

}
