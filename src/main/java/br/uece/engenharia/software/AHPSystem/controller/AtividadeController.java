package br.uece.engenharia.software.AHPSystem.controller;

import br.uece.engenharia.software.AHPSystem.model.Atividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class AtividadeController extends AbstractCrudController<Atividade, Long> {

    @Autowired
    public AtividadeController(JpaRepository<Atividade, Long> repository) {
        super(repository);
    }

    @Override
    public String getPath(String path) {
        return "atividade/" + path;
    }

    @Override
    public Atividade getEntity() {
        return new Atividade();
    }
}
