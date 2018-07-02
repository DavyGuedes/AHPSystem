package br.uece.engenharia.software.AHPSystem.controller.crud;

import br.uece.engenharia.software.AHPSystem.model.Atividade;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "portfolio/{id}/atividade")
public class AtividadeController extends AbstractCrudController<Atividade, Long> {

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
