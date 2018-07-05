package br.uece.engenharia.software.AHPSystem.repository;

import br.uece.engenharia.software.AHPSystem.model.Criterio;
import br.uece.engenharia.software.AHPSystem.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CriterioRepository extends JpaRepository<Criterio, Long> {
    Set<Criterio> findByPortfolios_id(Long id);
}
