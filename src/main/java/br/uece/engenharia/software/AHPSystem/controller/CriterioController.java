package br.uece.engenharia.software.AHPSystem.controller;

import br.uece.engenharia.software.AHPSystem.model.Criterio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class CriterioController extends AbstractCrudController<Criterio, Long> {

    @Autowired
    public CriterioController(JpaRepository<Criterio, Long> repository) {
        super(repository);
    }

    @Override
    public String getPath(String path) {
        return "criterio/" + path;
    }

    @Override
    public Criterio getEntity() {
        return new Criterio();
    }
}
