package br.usjt.apivolei.maestro.resource;

import br.usjt.apivolei.maestro.model.bean.Experiencia;
import br.usjt.apivolei.maestro.model.service.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/experiencia")
public class ExperienciaResource {

    @Autowired
    private ExperienciaService experienciaService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Experiencia experiencia, HttpServletRequest request){
        return experienciaService.cadastrar(experiencia, request);
    }

    @GetMapping
    public ResponseEntity<?> buscarExperiencias(HttpServletRequest request){
        return experienciaService.buscar(request);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarExperiencia(@PathVariable Long id, HttpServletRequest request){
        return experienciaService.buscar(id, request);
    }
}
