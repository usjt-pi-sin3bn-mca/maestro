package br.usjt.apivolei.maestro.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.usjt.apivolei.maestro.model.bean.Torcedor;
import br.usjt.apivolei.maestro.model.interfaces.IPontoTorcedor;
import br.usjt.apivolei.maestro.model.interfaces.PontoTorcedorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.usjt.apivolei.maestro.model.bean.Experiencia;
import br.usjt.apivolei.maestro.model.repository.ExperienciaRepository;

@Service
public class ExperienciaService {
	
	@Autowired
	private ExperienciaRepository expeRepo;

	public void cadastrar(Experiencia experiencia) throws Exception {
		experiencia.setAtivo(true);
		salvar(experiencia);
	}

	public List<Experiencia> findAll() throws Exception {

		List<Experiencia> experiencias = expeRepo.findAll();

		//Populando uma lista de indices apenas com as experiencias que não estão mais disponiveis
		int i = 0;
		List<Integer> indeces = new ArrayList<>();
		for (Experiencia e: experiencias) {
			if (e.getQtdDisponivel() == 0) {
				 indeces.add(i);
			}
			i++;
		}

		//Removendo da lista as experiencias que não estão mais disponiveis
		if (experiencias != null && experiencias.size() > 0) {
			for (Integer n: indeces) {
				experiencias.remove(n);
			}
		}

		return experiencias;
	}

	public Experiencia buscar(Long id) throws Exception {
		return expeRepo.findById(id).get();
	}

	public ResponseEntity<?> deletar(Experiencia expe, HttpServletRequest request ) {
		try {
        	expeRepo.delete(expe);
 		} catch (Exception e) {
			e.printStackTrace();
 			return ResponseEntity.badRequest().body("Experiencia nao existe");
 		}
		return ResponseEntity.ok("Experiência deletada com sucesso");
	}
	
	public void alterarExperiencia(Long id, Experiencia experienciaParam) throws Exception {

		Experiencia experiencia = expeRepo.findById(id).get();
		
		experiencia.setNome(experienciaParam.getNome());
		experiencia.setData(experienciaParam.getData());
		experiencia.setDescricao(experienciaParam.getDescricao());
		experiencia.setLocal(experienciaParam.getLocal());

		salvar(experiencia);
	}

	public boolean adquirir(Experiencia experiencia, Torcedor torcedor) {
		
		if (torcedor.isContaAtiva() 
				&& torcedor.isSocio() 
					&& experiencia.getQtdDisponivel() > 0
						&& torcedor.getPontos() >= experiencia.getCusto()) {
			IPontoTorcedor pontoTorcedor = new PontoTorcedorImpl(torcedor.getPontos());
			
			torcedor.setPontos(pontoTorcedor.decrementar(experiencia.getCusto()));
			experiencia.setQtdDisponivel(experiencia.getQtdDisponivel() - 1);

			if(experiencia.getQtdDisponivel() == 0){
				experiencia.setAtivo(false);
			}

			experiencia.addTorcedor(torcedor);
			salvar(experiencia);
			return true;
		}

		return false;
	}
	
	public boolean cancelar(Experiencia experiencia, Torcedor torcedor) {
		boolean torcedorRemovido = experiencia.removeTorcedor(torcedor);
		
		if(torcedorRemovido) {
			experiencia.setQtdDisponivel(experiencia.getQtdDisponivel() + 1);
			
			IPontoTorcedor pontoTorcedor = new PontoTorcedorImpl(torcedor.getPontos());
			torcedor.setPontos(pontoTorcedor.incrementar(experiencia.getCusto()));
			
			salvar(experiencia);
			
			return true;
		}else {
			return false;
		}
		
	}

	public Experiencia salvar(Experiencia experiencia){
		return expeRepo.save(experiencia);
	}

	public void remove(Long id) throws Exception {
		expeRepo.delete(expeRepo.findById(id).get());
	}
}