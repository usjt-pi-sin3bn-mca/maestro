package br.usjt.apivolei.maestro.model.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.usjt.apivolei.maestro.model.bean.Torcedor;
import br.usjt.apivolei.maestro.interfaces.CalculoPontuacao;
import br.usjt.apivolei.maestro.interfaces.IPonto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.usjt.apivolei.maestro.model.bean.Experiencia;
import br.usjt.apivolei.maestro.model.repository.ExperienciaRepository;

@Service
public class ExperienciaService {
	
	@Autowired
	private ExperienciaRepository expeRepo;

	public void cadastrar(Experiencia experiencia){
		try{
			experiencia.setAtivo(true);

			expeRepo.save(experiencia);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());

			throw e;
		}
	}

	public List<Experiencia> buscar(){
		List<Experiencia> experienciaList;

		try {
			experienciaList = expeRepo.findAll();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());

			throw e;
		}

		return experienciaList;
	}

	public Experiencia buscar(Long id){
		Experiencia experiencia;

		try {
			experiencia = expeRepo.findById(id).get();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());

			throw e;
		}

		return experiencia;
	}

	public ResponseEntity<?> deletar(Experiencia expe, HttpServletRequest request ){

		try {
        	expeRepo.delete(expe);
 		} catch (Exception e) {
			e.printStackTrace();
 			return ResponseEntity.badRequest().body("Experiencia nao existe");
 		}

		return ResponseEntity.ok("Experiência deletada com sucesso");
	}
	
	public void alterarExperiencia(Long id, Experiencia experienciaParam, HttpServletRequest request) {
		try {
			Experiencia experiencia = expeRepo.findById(id).get();
			experiencia.setNome(experienciaParam.getNome());
			experiencia.setData(experienciaParam.getData());
			experiencia.setDescricao(experienciaParam.getDescricao());
			experiencia.setLocal(experienciaParam.getLocal());

			expeRepo.save(experiencia);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());

			throw e;
		}
	}

	public boolean adquirir(Experiencia experiencia, Torcedor torcedor) {
		if(torcedor.getPontos() > experiencia.getCusto()) {
			IPonto calculoPontuacao = new CalculoPontuacao(torcedor.getPontos().doubleValue());

			torcedor.setPontos(calculoPontuacao.decrementar(experiencia.getCusto()).intValue());

			experiencia.setQtdDisponivel(experiencia.getQtdDisponivel() - 1);

			if(experiencia.getQtdDisponivel() == 0){
				experiencia.setAtivo(false);
			}

			experiencia.addTorcedor(torcedor);
			expeRepo.save(experiencia);

			return true;
		}
		else{
			return false;
		}
	}
}
