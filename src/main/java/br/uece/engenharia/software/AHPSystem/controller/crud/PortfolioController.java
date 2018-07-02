package br.uece.engenharia.software.AHPSystem.controller.crud;

import br.uece.engenharia.software.AHPSystem.model.Portfolio;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(PortfolioController.urlRequestMapping)
public class PortfolioController extends AbstractCrudController<Portfolio, Long> {

    static final String urlRequestMapping = "/portfolio";

    public PortfolioController() {
        super(urlRequestMapping);
    }

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
