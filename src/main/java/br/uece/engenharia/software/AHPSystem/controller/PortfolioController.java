package br.uece.engenharia.software.AHPSystem.controller;

import br.uece.engenharia.software.AHPSystem.model.Atividade;
import br.uece.engenharia.software.AHPSystem.model.Criterio;
import br.uece.engenharia.software.AHPSystem.model.Portfolio;
import br.uece.engenharia.software.AHPSystem.repository.AtividadeRepository;
import br.uece.engenharia.software.AHPSystem.repository.CriterioRepository;
import br.uece.engenharia.software.AHPSystem.repository.PortfolioRepository;
import br.uece.engenharia.software.AHPSystem.service.AtividadeService;
import br.uece.engenharia.software.AHPSystem.service.CriterioService;
import br.uece.engenharia.software.AHPSystem.service.PortfolioService;
import br.uece.engenharia.software.AHPSystem.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(PortfolioController.urlRequestMapping)
public class PortfolioController{

    static final String urlRequestMapping = "/portfolio";

    @Autowired
    CriterioService criterioService;
    @Autowired
    AtividadeService atividadeService;
    @Autowired
    PortfolioService portfolioService;


    @GetMapping
    public ModelAndView listAll(ModelAndView modelAndView){
        List<Portfolio> portfolios = portfolioService.findAll();
        modelAndView.addObject("portfolios", portfolios);
        modelAndView.setViewName(getViewPathByAction(Consts.viewList));
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView formNew(ModelAndView modelAndView) {
        modelAndView.addObject("portfolio", getPortfolio());
        modelAndView.addObject("allCriterios", criterioService.findAll());
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @PostMapping("/novo")
    public ModelAndView processNew(ModelAndView modelAndView, @Valid @ModelAttribute("portfolio") Portfolio portfolio, BindingResult result) {
        if (result.hasErrors()) {
            modelAndView.addObject("portfolio", portfolio);
            modelAndView.addObject("allCriterios", criterioService.findAll());
            modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
            return modelAndView;
        }
        this.portfolioService.save(portfolio);
        modelAndView.setViewName("redirect:" + urlRequestMapping);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(ModelAndView modelAndView, @PathVariable Long id) {
        Optional<Portfolio> portfolio = portfolioService.findById(id);
        if (!portfolio.isPresent()) {
            modelAndView.setViewName("redirect:" + urlRequestMapping);
        }
        modelAndView.addObject("portfolio", portfolio);
        modelAndView.addObject("allCriterios", criterioService.findAll());
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @GetMapping("/{id}/remover")
    public ModelAndView remove(ModelAndView modelAndView, @PathVariable Long id) {
        Optional<Portfolio> portfolio = portfolioService.findById(id);
        if (portfolio.isPresent())
            portfolioService.delete(portfolio.get());
        modelAndView.setViewName("redirect:" + urlRequestMapping);
        return modelAndView;

    }

    protected Portfolio getPortfolio() {
        return new Portfolio();
    }

    @GetMapping("/{id}")
    public ModelAndView find(ModelAndView modelAndView, @PathVariable("id") Long id){
        Portfolio portfolio = portfolioService.findById(id).get();

        fillData(modelAndView, portfolio);

        modelAndView.addObject("atividade", new Atividade());
        modelAndView.addObject("criterio", new Criterio());
        modelAndView.setViewName(getViewPathByAction("index"));
        return modelAndView;
    }


    protected String getViewPathByAction(String action) {
        return "portfolio/" + action;
    }

    @PostMapping("/{id}/criterio")
    public ModelAndView processCriterioForm(ModelAndView modelAndView, @PathVariable("id") Long id, @Valid @ModelAttribute ("criterio") Criterio criterio, BindingResult result){
        Portfolio portfolio = portfolioService.findById(id).get();

        if(result.hasErrors()){
            fillData(modelAndView, portfolio);

            modelAndView.addObject("atividade", new Atividade());
            modelAndView.addObject("criterio", criterio);

            modelAndView.setViewName(getViewPathByAction("index"));
            return modelAndView;
        } else {
            criterio.getPortfolios().add(portfolio);
            criterioService.save(criterio);

            portfolio.getCriterios().add(criterio);
            portfolioService.save(portfolio);

            modelAndView.setViewName("redirect:/portfolio/"+ portfolio.getId());
        }
        return modelAndView;
    }

    private void fillData(ModelAndView modelAndView, Portfolio portfolio) {
        modelAndView.addObject("portfolio", portfolio);
        modelAndView.addObject("atividades", portfolio.getAtividades());
        modelAndView.addObject("criterios", portfolio.getCriterios());
        modelAndView.addObject("allCriterios", criterioService.findAll());
    }

    @PostMapping("/{id}/atividade")
    public ModelAndView processAtividadeForm(ModelAndView modelAndView, @PathVariable("id") Long id, @Valid @ModelAttribute ("atividade") Atividade atividade, BindingResult result){
        Portfolio portfolio = portfolioService.findById(id).get();

        if(result.hasErrors()){
            fillData(modelAndView, portfolio);

            modelAndView.addObject("atividade", atividade);
            modelAndView.addObject("criterio", new Criterio());

            modelAndView.setViewName(getViewPathByAction("index"));
            return modelAndView;
        } else {
            atividade.setPortfolio(portfolio);
            atividadeService.save(atividade);

            portfolio.getAtividades().add(atividade);
            portfolioService.save(portfolio);

            modelAndView.setViewName("redirect:/portfolio/"+ portfolio.getId());
        }
        return modelAndView;
    }
}
