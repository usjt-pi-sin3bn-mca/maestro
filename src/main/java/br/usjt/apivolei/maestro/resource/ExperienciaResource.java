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
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Erro ao cadastrar experiência");
        }

        return ResponseEntity.ok("Experiência cadastrada");
    }

    @GetMapping
    public ResponseEntity<?> listarExperiencias(){
        List<Experiencia> experienciaList;

        try {
            experienciaList = experienciaService.buscar();
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocorreu um erro ao buscar as experiências");
        }

        return ResponseEntity.ok(experienciaList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarExperiencia(@PathVariable Long id){
        Experiencia experiencia;

        try {
            experiencia = experienciaService.buscar(id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocorreu um erro ao buscar a experiência");
        }

        return ResponseEntity.ok(experiencia);
    }

    @GetMapping(value = "/adquirir/{idExperiencia}/{idTorcedor}")
    public ResponseEntity<?> adquirir(@PathVariable Long idExperiencia, @PathVariable Long idTorcedor){
        
    	boolean experienciaAdquirida = false;

        try {
            Torcedor torcedor = torcedorExperienciaService.buscarTorcedor(idTorcedor);
            Experiencia experiencia = experienciaService.buscar(idExperiencia);
            
            experienciaAdquirida = experienciaService.adquirir(experiencia, torcedor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ocorreu um erro ao adquirir experiência, tente novamente!");
        }

        if (experienciaAdquirida) {
            return ResponseEntity.ok("Experiência adquirida");
        }

        return ResponseEntity.badRequest().body("O torcedor não possuí pontuação suficiente para adquirir a experiência");
    }
    public ResponseEntity<?> cancelar(@PathVariable Long idExperiencia, @PathVariable Long idTorcedor, HttpServletRequest request){
    	boolean experienciaRemovida = false;
    	
    	try {
    		Torcedor torcedor = torcedorExperienciaService.buscarTorcedor(idTorcedor);
            Experiencia experiencia = experienciaService.buscar(idExperiencia);
            
            experienciaRemovida = experienciaService.cancelar(experiencia, torcedor);
            
		} catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Ocorreu um erro ao cancelar experiência, tente novamente!");
		}
        if(experienciaRemovida){
            return ResponseEntity.ok("Experiência removida");
        }else{
            return ResponseEntity.badRequest().body("O torcedor não possuí experiencia para cancelar");
        }
    	
    }
}
