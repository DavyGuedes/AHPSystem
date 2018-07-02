package br.uece.engenharia.software.AHPSystem.controller.crud;

import br.uece.engenharia.software.AHPSystem.model.Atividade;
import br.uece.engenharia.software.AHPSystem.utils.Consts;
import com.fasterxml.classmate.GenericType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public abstract class AbstractCrudController<Entity, ID extends Serializable> {

    @Autowired
    protected JpaRepository<Entity, ID> repository;

    @GetMapping
    public ModelAndView listAll(ModelAndView modelAndView){
        List<Entity> entities = repository.findAll();
        modelAndView.addObject("entities", entities);
        modelAndView.setViewName(getViewPathByAction(Consts.viewList));
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView formNew(ModelAndView modelAndView) {
        // adiciona na view (tela) uma instancia do objeto para o formulario
        modelAndView.addObject("entity", getEntity());
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @PostMapping("/novo")
    // Metodo que intercepta o caminho '/novo' da url (metodo HTTP POST)
    // para submissao do formulario a ser processado
    public ModelAndView processNew(ModelAndView modelAndView, @Valid @ModelAttribute("entity") Entity entity, BindingResult result, RedirectAttributes redirectAttributes) {
        // verifica se a error de validacao
        if (result.hasErrors()) {
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
        modelAndView.setViewName("redirect:/" + getViewPathByAction(""));
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView find(ModelAndView modelAndView, @PathVariable ID id) {
        Optional<Entity> entity = repository.findById(id);
        if (!entity.isPresent()) {
            modelAndView.setViewName("redirect:/" + getViewPathByAction(""));
        }
        modelAndView.addObject("entity", entity);
        modelAndView.setViewName(getViewPathByAction(Consts.viewCreateOrUpdateForm));
        return modelAndView;
    }

    @GetMapping("/{id}/remover")
    public ModelAndView remove(ModelAndView modelAndView, @PathVariable ID id) {
        Optional<Entity> entity = repository.findById(id);
        if (entity.isPresent())
            repository.delete(entity.get());
        modelAndView.setViewName("redirect:/" + getViewPathByAction(""));
        return modelAndView;

    }

    protected  abstract String getViewPathByAction(String action);

    protected abstract Entity getEntity();
}
