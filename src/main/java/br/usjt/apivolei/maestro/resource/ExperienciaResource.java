package br.usjt.apivolei.maestro.resource;

import br.usjt.apivolei.maestro.model.bean.Experiencia;
import br.usjt.apivolei.maestro.model.bean.Torcedor;
import br.usjt.apivolei.maestro.model.service.ExperienciaService;
import br.usjt.apivolei.maestro.model.service.TorcedorExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/experiencia")
public class ExperienciaResource {
	
    @Autowired
    private ExperienciaService experienciaService;

    @Autowired
    private TorcedorExperienciaService torcedorExperienciaService;


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Experiencia experiencia){
        try {
            experienciaService.cadastrar(experiencia);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao cadastrar experiência");
        }
        return ResponseEntity.ok("Experiência cadastrada");
    }

    @GetMapping
    public ResponseEntity<?> listarExperiencias(){
        try {
            return ResponseEntity.ok(experienciaService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ocorreu um erro ao buscar as experiências");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarExperiencia(@PathVariable Long id){
        try {
            return ResponseEntity.ok(experienciaService.buscar(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Experiencia não existe");
        }
    }

    @PutMapping(value = "/adquirir/{idExperiencia}/{idTorcedor}")
    public ResponseEntity<?> adquirir(@PathVariable Long idExperiencia, @PathVariable Long idTorcedor){
        
    	boolean experienciaAdquirida = false;

        try {
            Torcedor torcedor = torcedorExperienciaService.buscarTorcedor(idTorcedor);
            Experiencia experiencia = experienciaService.buscar(idExperiencia);
            
            experienciaAdquirida = experienciaService.adquirir(experiencia, torcedor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Experiencia ou usuario não existe");
        }

        if (experienciaAdquirida) {
            return ResponseEntity.ok("Experiência adquirida");
        }

        return ResponseEntity.badRequest().body("O torcedor não possuí pontuação suficiente para adquirir a experiência");
    }
    
    @PutMapping("/cancelar/{idExperiencia}/{idTorcedor}")
    public ResponseEntity<?> cancelar(@PathVariable Long idExperiencia, @PathVariable Long idTorcedor){
    	boolean experienciaRemovida = false;
    	
    	try {
    		Torcedor torcedor = torcedorExperienciaService.buscarTorcedor(idTorcedor);
            Experiencia experiencia = experienciaService.buscar(idExperiencia);
            
            experienciaRemovida = experienciaService.cancelar(experiencia, torcedor);
            
		} catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ocorreu um erro ao cancelar experiência, tente novamente!");
		}
        if(experienciaRemovida){
            return ResponseEntity.ok("Experiência removida");
        }else{
            return ResponseEntity.badRequest().body("O torcedor não possuí experiencia para cancelar");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Experiencia experiencia) {
        try {
            experienciaService.alterarExperiencia(id, experiencia);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Json inválido");
        }
        return ResponseEntity.ok("Dados alterados com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            experienciaService.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Experiencia não existe");
        }
        return ResponseEntity.ok("Sucesso");
    }
}