package br.uece.engenharia.software.AHPSystem.service;


import br.uece.engenharia.software.AHPSystem.model.Atividade;
import br.uece.engenharia.software.AHPSystem.model.Portfolio;
import br.uece.engenharia.software.AHPSystem.repository.AtividadeRepository;
import br.uece.engenharia.software.AHPSystem.service.utils.GenericService;
import br.uece.engenharia.software.AHPSystem.service.utils.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtividadeService extends GenericServiceImpl<Atividade, Long> {
    @Autowired
    AtividadeRepository atividadeRepository;

    List<Atividade> findByPortfolio(Portfolio portfolio){
        return atividadeRepository.findByPortfolio(portfolio).stream().collect(Collectors.toList());
    }


}
