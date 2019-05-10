package br.usjt.apivolei.maestro.model.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.usjt.apivolei.maestro.model.bean.Experiencia;
import br.usjt.apivolei.maestro.model.repository.ExperienciaRepository;

@Service
public class ExperienciaService {
	
	@Autowired
	private ExperienciaRepository expeRepo;

	public ResponseEntity<?> cadastrar(Experiencia experiencia, HttpServletRequest request){
		try{
			expeRepo.save(experiencia);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Erro ao cadastrar experiência");
		}

		return ResponseEntity.ok("Experiência cadastrada");
	}

	public ResponseEntity<?> buscar(HttpServletRequest request){
		List<Experiencia> experienciaList;

		try {
			experienciaList = expeRepo.findAll();
		}catch(Exception e){
			return ResponseEntity.badRequest().body("Ocorreu um erro ao buscar as experiências");
		}

		return ResponseEntity.ok(experienciaList);
	}

	public ResponseEntity<?> buscar(Long id, HttpServletRequest request){
		Experiencia experiencia;

		try {
			experiencia = expeRepo.findById(id).get();
		}catch(Exception e){
			return ResponseEntity.badRequest().body("Ocorreu um erro ao buscar a experiência");
		}

		return ResponseEntity.ok(experiencia);
	}

	public ResponseEntity<?> deletar(Experiencia expe, HttpServletRequest request ){
        
		try {
        	expeRepo.delete(expe);
 		} catch (Exception e) {
 			return ResponseEntity.badRequest().body("Experiencia nao existe");
 		}

		return ResponseEntity.ok("Experiência deletada com sucesso");
	}
	
	public ResponseEntity<?> alterarExperiencia(Long id, Experiencia experienciaParam, HttpServletRequest request) {
		
		Experiencia experiencia = expeRepo.findById(id).get();
		experiencia.setNome(experienciaParam.getNome());	
		experiencia.setData(experienciaParam.getData());
		experiencia.setDescricao(experienciaParam.getDescricao());
		experiencia.setLocal(experienciaParam.getLocal());

		expeRepo.save(experiencia);

		return ResponseEntity.ok("Dados alterados");
	}

}
