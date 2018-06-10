package br.uece.engenharia.software.AHPSystem.repository;

import br.uece.engenharia.software.AHPSystem.model.Criterio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioRepository extends JpaRepository<Criterio, Long> {
}
