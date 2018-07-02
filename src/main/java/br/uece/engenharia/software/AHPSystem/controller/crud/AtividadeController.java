package br.uece.engenharia.software.AHPSystem.controller.crud;

import br.uece.engenharia.software.AHPSystem.model.Atividade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = AtividadeController.urlRequestMapping)
public class AtividadeController extends AbstractCrudController<Atividade, Long> {

    static final String urlRequestMapping = "/portfolio/{id}/atividade";

    public AtividadeController() {
        super(urlRequestMapping);
    }

    @Override
    protected String getViewPathByAction(String action) {
        return Atividade.class
                .getSimpleName().toLowerCase()
                + "/"
                + action;
    }

    @Override
    protected Atividade getEntity() {
        return new Atividade();
    }

}
