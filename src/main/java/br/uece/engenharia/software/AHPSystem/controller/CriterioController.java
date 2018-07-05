package br.uece.engenharia.software.AHPSystem.controller;

import br.uece.engenharia.software.AHPSystem.model.Criterio;
import br.uece.engenharia.software.AHPSystem.repository.CriterioRepository;
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
@RequestMapping(CriterioController.urlRequestMapping)
public class CriterioController {

    static final String urlRequestMapping = "/criterio";

    @Autowired
    protected CriterioRepository repository;

    @GetMapping
    public ModelAndView listAll(ModelAndView modelAndView){
        List<Criterio> entities = repository.findAll();
        modelAndView.addObject("entities", entities);
        modelAndView.setViewName(getViewPathByAction(Consts.viewList));
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView formNew(ModelAndView modelAndView) {
        // adiciona na view (tela) uma instancia do objeto para o formulario
        modelAndView.addObject("entity", getCriterio());
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @PostMapping("/novo")
    // Metodo que intercepta o caminho '/novo' da url (metodo HTTP POST)
    // para submissao do formulario a ser processado
    public ModelAndView processNew(ModelAndView modelAndView, @Valid @ModelAttribute("entity") Criterio entity, BindingResult result) {
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
        this.repository.save(entity);
        modelAndView.setViewName("redirect:" + urlRequestMapping);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView find(ModelAndView modelAndView, @PathVariable Long id) {
        Optional<Criterio> entity = repository.findById(id);
        if (!entity.isPresent()) {
            modelAndView.setViewName("redirect:" + urlRequestMapping);
        }
        modelAndView.addObject("entity", entity);
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @GetMapping("/{id}/remover")
    public ModelAndView remove(ModelAndView modelAndView, @PathVariable Long id) {
        Optional<Criterio> entity = repository.findById(id);
        if (entity.isPresent())
            repository.delete(entity.get());
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
