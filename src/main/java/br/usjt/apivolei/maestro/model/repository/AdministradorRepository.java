package br.usjt.apivolei.maestro.model.repository;

import br.usjt.apivolei.maestro.model.bean.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByEmailAndSenhaAndAtivo(String usuario, String senha, boolean ativo);
}
