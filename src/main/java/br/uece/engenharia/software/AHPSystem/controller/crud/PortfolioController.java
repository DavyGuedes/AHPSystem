package br.uece.engenharia.software.AHPSystem.controller.crud;

import br.uece.engenharia.software.AHPSystem.model.Portfolio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController extends AbstractCrudController<Portfolio, Long> {

    @Override
    protected String getViewPathByAction(String action) {
        return Portfolio.class
                .getSimpleName().toLowerCase()
                + "/"
                + action;
    }

    @Override
    protected Portfolio getEntity() {
        return new Portfolio();
    }
}
