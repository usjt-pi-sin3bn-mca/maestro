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
        return salvar(administrador);
    }

    public Administrador buscar(Long id) {
        return administradorRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void alterar(Administrador administrador, Administrador administradorNovo) {
        administrador.setEmail(administradorNovo.getEmail());
        administrador.setSenha(administradorNovo.getSenha());
        administrador.setNome(administradorNovo.getNome());
        
        salvar(administrador);
    }

    public Administrador salvar(Administrador administrador){
        return administradorRepository.save(administrador);
    }
}
