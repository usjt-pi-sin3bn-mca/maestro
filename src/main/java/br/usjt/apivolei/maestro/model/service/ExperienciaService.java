package br.usjt.apivolei.maestro.model.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.usjt.apivolei.maestro.model.bean.Experiencia;
import br.usjt.apivolei.maestro.model.repository.ExperienciaRepository;
import br.usjt.apivolei.maestro.model.util.ResponseUtils;

@Service
public class ExperienciaService {
	
	@Autowired
	private ExperienciaRepository expeRepo;

	public ResponseEntity<?> cadastrar(Experiencia experiencia, HttpServletRequest request){
		String mensagemRetorno = "";
		int statusRetorno = 0;

		try{
			expeRepo.save(experiencia);

			mensagemRetorno = "Experiência cadastrada";
			statusRetorno = 200;
		}catch(Exception e){
			mensagemRetorno = "Erro ao cadastrar experiência";
			statusRetorno = 400;
		}
		finally {
			return ResponseUtils.getInstanceResponseEntity(
					ResponseUtils.getInstanceDetalhesRetorno(new Date(), mensagemRetorno, "uri=" + request.getRequestURI()),
					statusRetorno
			);
		}
	}

	public ResponseEntity<?> buscar(HttpServletRequest request){
		try {
			return ResponseUtils.getInstanceResponseEntity(expeRepo.findAll(), 200);
		}catch(Exception e){
			return ResponseUtils.getInstanceResponseEntity(
					ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Ocorreu um erro ao buscar as experiências", "uri=" + request.getRequestURI()),
					400
			);
		}
	}

	public ResponseEntity<?> buscar(Long id, HttpServletRequest request){
		try {
			return ResponseUtils.getInstanceResponseEntity(expeRepo.findById(id), 200);
		}catch(Exception e){
			return ResponseUtils.getInstanceResponseEntity(
					ResponseUtils.getInstanceDetalhesRetorno(new Date(), "Ocorreu um erro ao buscar a experiência", "uri=" + request.getRequestURI()),
					400
			);
		}
	}

	public ResponseEntity<?> deletar(Experiencia expe, HttpServletRequest request ){
        
		try {
        	expeRepo.delete(expe);
        	return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
 					"Experiencia Deletada", "uri=" + request.getRequestURI()), 200);
 		} catch (Exception e) {
 			return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
 					"Experiencia nao existe", "uri=" + request.getRequestURI()), 400);
 		}

	}
	
	public ResponseEntity<?> alterarExperiencia(Long id, Experiencia experienciaParam, HttpServletRequest request) {
		
		Experiencia experiencia = expeRepo.findById(id).get();
		experiencia.setNome(experienciaParam.getNome());	
		experiencia.setData(experienciaParam.getData());
		experiencia.setDescricao(experienciaParam.getDescricao());
		experiencia.setLocal(experienciaParam.getLocal());

		expeRepo.save(experiencia);

		return ResponseUtils.getInstanceResponseEntity(ResponseUtils.getInstanceDetalhesRetorno(new Date(),
				"Dados alterados", "uri=" + request.getRequestURI()), 200);
		
	}

}
