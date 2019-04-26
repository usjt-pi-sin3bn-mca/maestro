package br.usjt.apivolei.maestro.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.apivolei.maestro.model.bean.Experiencia;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {

	public Experiencia findOneByNome(Experiencia nome);
}
