package br.uece.engenharia.software.AHPSystem.controller;

import br.uece.engenharia.software.AHPSystem.model.Portifolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/portifolio")
public class PortifolioController extends AbstractCrudController<Portifolio, Long> {
    @Autowired
    public PortifolioController(JpaRepository<Portifolio, Long> repository) {
        super(repository);
    }

    @Override
    public String getPath(String path) {
        return "portifolio/" + path;
    }

    @Override
    public Portifolio getEntity() {
        return new Portifolio();
    }

    @GetMapping
    public String listPortifolio(Model model){
        List<Portifolio> portifolios = repository.findAll();
        model.addAttribute("portifolios", portifolios);
        return getPath("list");
    }
}
