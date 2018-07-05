package br.uece.engenharia.software.AHPSystem.controller;

import br.uece.engenharia.software.AHPSystem.model.Atividade;
import br.uece.engenharia.software.AHPSystem.model.Criterio;
import br.uece.engenharia.software.AHPSystem.model.Portfolio;
import br.uece.engenharia.software.AHPSystem.repository.AtividadeRepository;
import br.uece.engenharia.software.AHPSystem.repository.CriterioRepository;
import br.uece.engenharia.software.AHPSystem.repository.PortfolioRepository;
import br.uece.engenharia.software.AHPSystem.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(PortfolioController.urlRequestMapping)
public class PortfolioController{

    static final String urlRequestMapping = "/portfolio";

    @Autowired
    CriterioRepository criterioRepository;
    @Autowired
    AtividadeRepository atividadeRepository;
    @Autowired
    PortfolioRepository portfolioRepository;


    @GetMapping
    public ModelAndView listAll(ModelAndView modelAndView){
        List<Portfolio> entities = portfolioRepository.findAll();
        modelAndView.addObject("entities", entities);
        modelAndView.setViewName(getViewPathByAction(Consts.viewList));
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView formNew(ModelAndView modelAndView) {
        // adiciona na view (tela) uma instancia do objeto para o formulario
        modelAndView.addObject("entity", getPortfolio());
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @PostMapping("/novo")
    // Metodo que intercepta o caminho '/novo' da url (metodo HTTP POST)
    // para submissao do formulario a ser processado
    public ModelAndView processNew(ModelAndView modelAndView, @Valid @ModelAttribute("entity") Portfolio entity, BindingResult result) {
        // verifica se a error de validacao
        if (result.hasErrors()) {
//            System.out.println(result.getAllErrors());
            // existem erros,
            // entao redireciona novamente para o formulario,
            // adicionando novamente o objeto na view
            // (caso não adicione, os dados anteriormente inseridos sao perdidos,
            // pois nao haveria relacao com seus atributos e os campos da view (tela)
            modelAndView.addObject("entity", entity);
            modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
            return modelAndView;
        }
        this.portfolioRepository.save(entity);
        modelAndView.setViewName("redirect:" + urlRequestMapping);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(ModelAndView modelAndView, @PathVariable Long id) {
        Optional<Portfolio> entity = portfolioRepository.findById(id);
        if (!entity.isPresent()) {
            modelAndView.setViewName("redirect:" + urlRequestMapping);
        }
        modelAndView.addObject("entity", entity);
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @GetMapping("/{id}/remover")
    public ModelAndView remove(ModelAndView modelAndView, @PathVariable Long id) {
        Optional<Portfolio> entity = portfolioRepository.findById(id);
        if (entity.isPresent())
            portfolioRepository.delete(entity.get());
        modelAndView.setViewName("redirect:" + urlRequestMapping);
        return modelAndView;

    }

    protected Portfolio getPortfolio() {
        return new Portfolio();
    }

    @GetMapping("/{id}")
    public ModelAndView find(ModelAndView modelAndView, @PathVariable("id") Long id){
        Portfolio portfolio = portfolioRepository.findById(id).get();
        addPortfolioData(modelAndView, portfolio);
        modelAndView.addObject("atividadeBlank", new Atividade());
        modelAndView.addObject("criterioBlank", new Criterio());
        modelAndView.setViewName(getViewPathByAction("index"));
        return modelAndView;
    }

    @PostMapping("/{id}/atividade")
    public ModelAndView processAtividadeForm(ModelAndView modelAndView, @PathVariable("id") Long id, @Valid @ModelAttribute ("atividadeBlank") Atividade atividade, BindingResult result){
        Portfolio portfolio = portfolioRepository.findById(id).get();

        addPortfolioData(modelAndView, portfolio);
        modelAndView.addObject("atividadeBlank", atividade);
        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            modelAndView.setViewName(getViewPathByAction("index"));
        } else {
            atividade.setPortfolio(portfolio);
            atividadeRepository.save(atividade);

            portfolio.getAtividades().add(atividade);
            portfolioRepository.save(portfolio);

            modelAndView.setViewName("redirect:/portfolio/"+ portfolio.getId());
        }
        return modelAndView;
    }

    @PostMapping("/{id}/criterio")
    public ModelAndView processCriterioForm(ModelAndView modelAndView, @PathVariable("id") Long id, @Valid @ModelAttribute ("criterioBlank") Criterio criterio, BindingResult result){
        Portfolio portfolio = portfolioRepository.findById(id).get();
         addPortfolioData(modelAndView, portfolio);

        if(result.hasErrors()){
            modelAndView.setViewName(getViewPathByAction("index"));
            return modelAndView;
        } else {
            criterio.getPortfolios().add(portfolio);
            criterioRepository.save(criterio);

            portfolio.getCriterios().add(criterio);
            portfolioRepository.save(portfolio);

            modelAndView.setViewName("redirect:/portfolio/"+ portfolio.getId());
        }
        return modelAndView;
    }

    protected String getViewPathByAction(String action) {
        return "portfolio/" + action;
    }

    private void addPortfolioData(ModelAndView modelAndView, Portfolio portfolio) {
        modelAndView.addObject("entity", portfolio);
        modelAndView.addObject("atividades", portfolio.getAtividades());
        System.out.println("atividades: " + portfolio.getAtividades().size());
        System.out.println("criterios: " + portfolio.getCriterios().size());
        modelAndView.addObject("criterios", portfolio.getCriterios());
    }
}
