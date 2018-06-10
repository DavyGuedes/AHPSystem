package br.uece.engenharia.software.AHPSystem.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Optional;


@Controller
@RequestMapping("/")
public abstract class AbstractCrudController<Entity, ID extends Serializable> {

    protected final JpaRepository<Entity, ID> repository;

    public AbstractCrudController(JpaRepository<Entity, ID> repository) {
        this.repository = repository;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
    }

    @GetMapping("/new")
    public String initForm(Model model) {
        model.addAttribute("entity", getEntity());
        return getPath("createOrUpdateForm");
    }

    @PostMapping("/new")
    public String processForm(Model model, @Valid @ModelAttribute("entity") Entity entity, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("entity", entity);
            return getPath("createOrUpdateForm");
        } else {
            this.repository.save(entity);
            redirectAttributes.addFlashAttribute("message", "Salvamento concluído.");
            return "redirect:/" + getPath("");
        }
    }

    @GetMapping("/{id}")
    public String find(@PathVariable ID id, RedirectAttributes redirectAttrs) {
        Optional<Entity> entity = repository.findById(id);
        if (!entity.isPresent()) {
            redirectAttrs.addFlashAttribute("message", "Entidade não encontrado(a)");
            return "redirect:/" + getPath("");
        }
        redirectAttrs.addAttribute("entity", entity);
        return getPath("info");
    }

    @GetMapping("/{id}/delete")
    public String remove(@PathVariable ID id, RedirectAttributes redirectAttributes) {
        Optional<Entity> entity = repository.findById(id);
        try {
            if (entity.isPresent())
                repository.delete(entity.get());
            else
                redirectAttributes.addFlashAttribute("message", "Entidade não encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Ocorreu um erro. " + e.getMessage());
        }
        return "reditect:/" + getPath("");

    }

    protected abstract String getPath(String path);

    protected abstract Entity getEntity();
}
