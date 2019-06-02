package br.usjt.apivolei.maestro.model.repository;

import br.usjt.apivolei.maestro.model.bean.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByEmailAndSenha(String usuario, String senha);
}