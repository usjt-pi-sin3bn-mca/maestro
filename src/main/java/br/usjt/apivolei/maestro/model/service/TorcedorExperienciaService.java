package br.usjt.apivolei.maestro.model.service;

import br.usjt.apivolei.maestro.model.bean.Torcedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TorcedorExperienciaService {
    @Autowired
    private TorcedorService torcedorService;

    public Torcedor buscarTorcedor(Long id){
        return torcedorService.buscarTorcedor(id);
    }
}
