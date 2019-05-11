package br.usjt.apivolei.maestro.resource;

import br.usjt.apivolei.maestro.model.bean.Experiencia;
import br.usjt.apivolei.maestro.model.bean.Torcedor;
import br.usjt.apivolei.maestro.model.service.ExperienciaService;
import br.usjt.apivolei.maestro.model.service.TorcedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/experiencia")
public class ExperienciaResource {

    @Autowired
    private ExperienciaService experienciaService;

    @Autowired
    private TorcedorService torcedorService;


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
    public ResponseEntity<?> buscarExperiencia(@PathVariable Long id, HttpServletRequest request){
        Experiencia experiencia;

        try {
            experiencia = experienciaService.buscar(id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocorreu um erro ao buscar a experiência");
        }

        return ResponseEntity.ok(experiencia);
    }

    @GetMapping(value = "/adquirir")
    public ResponseEntity<?> adquirir(@RequestParam Long idExperiencia, @RequestParam Long idTorcedor, HttpServletRequest request){
        boolean experienciaAdquirida = false;

        try{
            Experiencia experiencia = experienciaService.buscar(idExperiencia);
            Torcedor torcedor = torcedorService.buscarTorcedor(idTorcedor);

            if(torcedor.getPontos() > 0){
                experienciaAdquirida = experienciaService.adquirir(experiencia, torcedor);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());

            return ResponseEntity.badRequest().body("Ocorreu um erro ao adquirir experiência, tente novamente!");
        }

        if(experienciaAdquirida){
            return ResponseEntity.ok("Experiência adquirida");
        }else{
            return ResponseEntity.badRequest().body("O torcedor não possuí pontuação suficiente para adquirir a experiência");
        }
    }
}
