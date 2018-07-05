package br.uece.engenharia.software.AHPSystem.repository;

import br.uece.engenharia.software.AHPSystem.model.Atividade;
import br.uece.engenharia.software.AHPSystem.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    Set<Atividade> findByPortfolio(Portfolio portfolio);
}
