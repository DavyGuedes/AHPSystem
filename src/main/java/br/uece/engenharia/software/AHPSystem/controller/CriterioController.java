package br.uece.engenharia.software.AHPSystem.controller;

import br.uece.engenharia.software.AHPSystem.model.Atividade;
import br.uece.engenharia.software.AHPSystem.model.Criterio;
import br.uece.engenharia.software.AHPSystem.model.Portfolio;
import br.uece.engenharia.software.AHPSystem.service.CriterioService;
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
@RequestMapping(CriterioController.urlRequestMapping)
public class CriterioController {

    static final String urlRequestMapping = "/criterio";

    @Autowired
    protected CriterioService criterioService;

    @Autowired
    protected PortfolioService portfolioService;

    @GetMapping
    public ModelAndView listAll(ModelAndView modelAndView, RedirectAttributes attributes){
        List<Criterio> criterios= criterioService.findAll();
        List<Portfolio> allPortfolios = portfolioService.findAll();
        /*if (allPortfolios.isEmpty()){
            attributes.addFlashAttribute("mensagem", "Primeiro crie um Portfólio para depois criar uma atividade");
            modelAndView.setViewName("redirect:" + PortfolioController.urlRequestMapping + "/novo");
            return modelAndView;
        }*/
        modelAndView.addObject("criterios", criterios);
        modelAndView.addObject("allPortfolios", allPortfolios);
        modelAndView.setViewName(getViewPathByAction(Consts.viewList));
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView formNew(ModelAndView modelAndView) {
        modelAndView.addObject("criterio", getCriterio());
        modelAndView.addObject("allPortfolios", portfolioService.findAll());
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @PostMapping("/novo")
    public ModelAndView processNew(ModelAndView modelAndView, @Valid @ModelAttribute("criterio") Criterio criterio, BindingResult result) {
        List<Portfolio> allPortfolios = portfolioService.findAll();
        if (result.hasErrors()) {
            modelAndView.addObject("criterio", criterio);
            modelAndView.addObject("allPortfolios", allPortfolios);
            modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
            return modelAndView;
        }

        for(Portfolio portfolio : criterio.getPortfolios()){

        }
        this.criterioService.save(criterio);
        modelAndView.setViewName("redirect:" + urlRequestMapping);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView find(ModelAndView modelAndView, @PathVariable Long id) {
        Optional<Criterio> criterio = criterioService.findById(id);
        modelAndView.addObject("allPortfolios", portfolioService.findAll());
        if (!criterio.isPresent()) {
            modelAndView.setViewName("redirect:" + urlRequestMapping);
            return modelAndView;
        }
        modelAndView.addObject("criterio", criterio);
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @GetMapping("/{id}/remover")
    public ModelAndView remove(ModelAndView modelAndView, @PathVariable Long id) {
        Optional<Criterio> entity = criterioService.findById(id);
        if (entity.isPresent())
            criterioService.delete(entity.get());
        modelAndView.setViewName("redirect:" + urlRequestMapping);
        return modelAndView;

    }

    protected String getViewPathByAction(String action) {
        return "criterio/" + action;
    }

    protected Criterio getCriterio() {
        return new Criterio();
    }

}
