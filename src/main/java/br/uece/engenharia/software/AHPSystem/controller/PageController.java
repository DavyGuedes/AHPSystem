package br.uece.engenharia.software.AHPSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PageController {
    @GetMapping
    public ModelAndView paginaInicial(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("sobre")
    public ModelAndView paginaSobe(ModelAndView modelAndView){
        modelAndView.setViewName("sobre");
        return modelAndView;
    }

}
