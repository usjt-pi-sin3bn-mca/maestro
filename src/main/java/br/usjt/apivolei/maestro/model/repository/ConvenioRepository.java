package br.usjt.apivolei.maestro.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.apivolei.maestro.model.bean.Convenio;

@Repository
public interface ConvenioRepository extends JpaRepository< Convenio, Long>{

}