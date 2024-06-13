package com.matheus.api_final.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.matheus.api_final.Repository.AmbienteRepository;
import com.matheus.api_final.Repository.AtivoPatrimonialRepository;
import com.matheus.api_final.Repository.ResponsavelRepository;

@Controller
public class AdminController {

    private final ResponsavelRepository responsavelRepository;
    private final AmbienteRepository ambienteRepository;
    private final AtivoPatrimonialRepository ativoPatrimonialRepository;

    public AdminController(ResponsavelRepository responsavelRepository,
            AmbienteRepository ambienteRepository,
            AtivoPatrimonialRepository ativoPatrimonialRepository) {
        this.responsavelRepository = responsavelRepository;
        this.ambienteRepository = ambienteRepository;
        this.ativoPatrimonialRepository = ativoPatrimonialRepository;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        // Adicione os atributos necessários ao modelo
        model.addAttribute("responsaveis", responsavelRepository.findAll());
        model.addAttribute("ambientes", ambienteRepository.findAll());
        model.addAttribute("ativos", ativoPatrimonialRepository.findAll());
        // Retorna o nome da página HTML que será carregada
        return "admin";
    }
}