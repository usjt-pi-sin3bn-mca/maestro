package br.usjt.apivolei.maestro.model.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
