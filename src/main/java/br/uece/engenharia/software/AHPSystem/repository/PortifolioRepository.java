package br.uece.engenharia.software.AHPSystem.repository;

import br.uece.engenharia.software.AHPSystem.model.Portifolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortifolioRepository extends JpaRepository<Portifolio, Long> {

}
