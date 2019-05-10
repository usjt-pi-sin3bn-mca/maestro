package br.usjt.apivolei.maestro.controller;

import br.usjt.apivolei.maestro.model.bean.Experiencia;
import br.usjt.apivolei.maestro.model.service.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Resource
public class ExperienciaController {

    @Autowired
    private ExperienciaService experienciaService;

    @RequestMapping(value = "/experiencia", method = RequestMethod.POST)
    public ResponseEntity<?> cadastrar(@RequestBody Experiencia experiencia, HttpServletRequest request){
        return experienciaService.cadastrar(experiencia, request);
    }

    @RequestMapping(value = "/experiencia/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable Long id, HttpServletRequest request){
        return experienciaService.buscar(id, request);
    }

    @RequestMapping(value = "/experiencia", method = RequestMethod.GET)
    public ResponseEntity<?> buscarTodas(HttpServletRequest request){
        return experienciaService.buscar(request);
    }
}
