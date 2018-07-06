package br.uece.engenharia.software.AHPSystem.controller;

import br.uece.engenharia.software.AHPSystem.model.Atividade;
import br.uece.engenharia.software.AHPSystem.model.Portfolio;
import br.uece.engenharia.software.AHPSystem.service.AtividadeService;
import br.uece.engenharia.software.AHPSystem.service.PortfolioService;
import br.uece.engenharia.software.AHPSystem.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = AtividadeController.urlRequestMapping)
public class AtividadeController {

    static final String urlRequestMapping = "/atividade";

    @Autowired
    AtividadeService atividadeService;

    @Autowired
    PortfolioService portfolioService;

    @GetMapping
    public ModelAndView listAll(ModelAndView modelAndView, RedirectAttributes attributes) {
        List<Atividade> atividades = atividadeService.findAll();
        List<Portfolio> portfolios = portfolioService.findAll();
        if (portfolios.isEmpty()){
            attributes.addFlashAttribute("mensagem", "Primeiro crie um Portfólio para depois criar uma atividade");
            modelAndView.setViewName("redirect:" + PortfolioController.urlRequestMapping + "/novo");
            return modelAndView;
        }
        modelAndView.addObject("atividades", atividades);
        modelAndView.setViewName(getViewPathByAction(Consts.viewList));
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView formNew(ModelAndView modelAndView, RedirectAttributes attributes) {
        modelAndView.addObject("atividade", getAtividade());
        List<Portfolio> portfolios = portfolioService.findAll();
        if (portfolios.isEmpty()){
            attributes.addFlashAttribute("mensagem", "Primeiro crie um Portfólio para depois criar uma atividade");
            modelAndView.setViewName("redirect:" + PortfolioController.urlRequestMapping + "/novo");
            return modelAndView;
        }
        modelAndView.addObject("portfolios", portfolioService.findAll());
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @PostMapping("/novo")
    public ModelAndView processNew(ModelAndView modelAndView, @Valid @ModelAttribute("atividade") Atividade atividade, BindingResult result) {
        if (result.hasErrors()) {
            modelAndView.addObject("atividade", atividade);
            modelAndView.addObject("portfolios", portfolioService.findAll());
            modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
            return modelAndView;
        }
        this.atividadeService.save(atividade);
        modelAndView.setViewName("redirect:" + urlRequestMapping);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView find(ModelAndView modelAndView, @PathVariable Long id) {
        Optional<Atividade> atividade = atividadeService.findById(id);
        if (!atividade.isPresent()) {
            modelAndView.setViewName("redirect:" + urlRequestMapping);
            return modelAndView;
        }
        modelAndView.addObject("atividade", atividade);
        modelAndView.addObject("portfolios", portfolioService.findAll());
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @GetMapping("/{id}/remover")
    public ModelAndView remove(ModelAndView modelAndView, @PathVariable Long id) {
        Optional<Atividade> entity = atividadeService.findById(id);
        if (entity.isPresent())
            atividadeService.delete(entity.get());
        modelAndView.setViewName("redirect:" + urlRequestMapping);
        return modelAndView;

    }

    private String getViewPathByAction(String action) {
        return "atividade/" + action;
    }

    protected Atividade getAtividade() {
        return new Atividade();
    }

}
