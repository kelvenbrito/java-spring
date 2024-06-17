package br.com.kelven.apirest_senai.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.kelven.apirest_senai.Model.Responsavel;
import br.com.kelven.apirest_senai.Repository.AmbienteRepository;
import br.com.kelven.apirest_senai.Repository.AtivoPatrimonialRepository;
import br.com.kelven.apirest_senai.Repository.ResponsavelRepository;

@Controller
public class AdminController {

    private final ResponsavelRepository responsavelRepository;
    private final AmbienteRepository ambienteRepository;
    private final AtivoPatrimonialRepository ativoPatrimonialRepository;

      @Autowired
    ResponsavelRepository repository;

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

    
    @GetMapping("/editar/{id}")
    public String editarResponsavel(@PathVariable Long id, Model model) {
        Optional<Responsavel> responsavelOptional = repository.findById(id);
        if (responsavelOptional.isPresent()) {
            Responsavel responsavel = responsavelOptional.get();
            model.addAttribute("responsavel", responsavel);
            return "editarResponsavel"; // Retorna a página de edição
        } else {
            return "redirect:/admin"; // Redireciona para a página principal
        }
    }
}