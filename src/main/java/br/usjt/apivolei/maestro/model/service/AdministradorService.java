package br.usjt.apivolei.maestro.model.service;

import br.usjt.apivolei.maestro.model.bean.Administrador;
import br.usjt.apivolei.maestro.model.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    public Administrador cadastrar(Administrador administrador) {
        administrador.setAtivo(true);
        return salvar(administrador);
    }

    public Administrador buscar(Long id) {
        return administradorRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public boolean buscar(Administrador administrador){
        return administradorRepository
                .findByUsuarioAndSenhaAndAtivo(administrador.getUsuario(), administrador.getSenha(), true)
                .isPresent();
    }

    public void alterar(Administrador administrador, Administrador administradorNovo) {
        administrador.setEmail(administradorNovo.getEmail());
        administrador.setSenha(administradorNovo.getSenha());
        administrador.setAtivo(administradorNovo.getAtivo());
        administrador.setUsuario(administradorNovo.getUsuario());
        
        salvar(administrador);
    }

    public Administrador salvar(Administrador administrador){
        return administradorRepository.save(administrador);
    }
}
