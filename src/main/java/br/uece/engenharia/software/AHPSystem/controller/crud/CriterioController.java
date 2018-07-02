package br.uece.engenharia.software.AHPSystem.controller.crud;

import br.uece.engenharia.software.AHPSystem.model.Criterio;

public class CriterioController extends AbstractCrudController<Criterio, Long> {

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
