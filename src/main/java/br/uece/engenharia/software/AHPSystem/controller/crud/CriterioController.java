package br.uece.engenharia.software.AHPSystem.controller.crud;

import br.uece.engenharia.software.AHPSystem.model.Criterio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CriterioController.urlRequestMapping)
public class CriterioController extends AbstractCrudController<Criterio, Long> {

    static final String urlRequestMapping = "/criterio";

    public CriterioController() {
        super(urlRequestMapping);
    }

    @Override
    protected String getViewPathByAction(String action) {
        return Criterio.class
                .getSimpleName().toLowerCase()
                + "/"
                + action;
    }

    @Override
    protected Criterio getEntity() {
        return new Criterio();
    }
}
