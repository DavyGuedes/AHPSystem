package br.uece.engenharia.software.AHPSystem.service;

import br.uece.engenharia.software.AHPSystem.model.Criterio;
import br.uece.engenharia.software.AHPSystem.repository.CriterioRepository;
import br.uece.engenharia.software.AHPSystem.service.utils.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CriterioService extends GenericServiceImpl<Criterio, Long> {
    @Autowired
    CriterioRepository criterioRepository;

    List<Criterio> findByPortfolios_id(Long id){
        return criterioRepository.findByPortfolios_id(id).stream().collect(Collectors.toList());
    }
}
