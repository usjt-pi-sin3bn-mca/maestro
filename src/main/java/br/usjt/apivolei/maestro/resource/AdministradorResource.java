package br.usjt.apivolei.maestro.resource;

import br.usjt.apivolei.maestro.model.bean.Administrador;
import br.usjt.apivolei.maestro.model.bean.DetalhesRetorno;
import br.usjt.apivolei.maestro.model.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/administrador")
public class AdministradorResource {
    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private DetalhesRetorno detalhesRetorno;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Administrador administrador, HttpServletRequest request){
        Administrador administradorCadastrado = administradorService.cadastrar(administrador);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(administradorCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(detalhesRetorno.build(new Date(), "Administrador cadastrado", "uri=" + request.getRequestURI()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Administrador> buscar(@PathVariable Long id){
        Administrador administrador = administradorService.buscar(id);

        return ResponseEntity.ok(administrador);
    }

    @PutMapping
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Administrador administrador){
        Administrador administradorRet = administradorService.buscar(id);

        administradorService.alterar(administradorRet, administrador);

        return ResponseEntity.ok("Dados alterados com sucesso!");
    }

}