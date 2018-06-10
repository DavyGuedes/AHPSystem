package br.uece.engenharia.software.AHPSystem.repository;

import br.uece.engenharia.software.AHPSystem.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
