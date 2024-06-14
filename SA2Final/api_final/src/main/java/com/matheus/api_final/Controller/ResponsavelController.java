package com.matheus.api_final.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.matheus.api_final.Model.Responsavel;
import com.matheus.api_final.Repository.ResponsavelRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {

    @Autowired
    ResponsavelRepository repository;

    @GetMapping()
    public List<Responsavel> getResponsavel() {
        List<Responsavel> responsaveis = (List<Responsavel>) repository.findAll();
        System.out.println(responsaveis);
        return responsaveis;
    }

    @PostMapping()
    public RedirectView postResponsavel(@RequestParam("idResponsavel") Long id,
            @RequestParam("nomeResponsavel") String nome) {
        Responsavel responsavel = new Responsavel();
        responsavel.setId(id);
        responsavel.setNome(nome);
        Responsavel salvo = repository.save(responsavel);
        System.out.println("Salvo: " + salvo);

        return new RedirectView("/admin", true); // Redireciona para a página "/admin"
    }

    @PostMapping("/{id}")
    public ResponseEntity<Optional<Responsavel>> getResponsavelById(@PathVariable Long id) {
        Optional<Responsavel> responsavel = repository.findById(id);
        if (responsavel.isPresent()) {
            System.out.println(responsavel);
            return ResponseEntity.ok(responsavel);
        } else {
            System.out.println("Responsável não encontrado");
            return ResponseEntity.notFound().build();
        }
     }
     @PostMapping("/editar/{id}")
     public ModelAndView editarResponsavel(@PathVariable Long id, RedirectAttributes attributes) {
         ModelAndView mv = new ModelAndView("/editarResponsavel"); // página interna de acesso
         Optional<Responsavel> responsavelOptional = repository.findById(id);
         if (responsavelOptional.isPresent()) {
             Responsavel responsavel = responsavelOptional.get();
             mv.addObject("responsavel", responsavel); // Adicionando responsável ao ModelAndView
             System.out.println("Responsável encontrado: " + responsavel);
             mv.addObject("responsavelId", id); // Adicionando o ID ao ModelAndView
         } else {
             System.out.println("Responsável não encontrado, redirecionando para /admin");
             mv.setViewName("redirect:/admin");
         }
         return mv;
     }
     
    
    @GetMapping("/editar/{id}")
public ModelAndView exibirFormularioEdicao(@PathVariable Long id) {
    ModelAndView mv = new ModelAndView("/editarResponsavel");
    Optional<Responsavel> responsavelOptional = repository.findById(id);
    if (responsavelOptional.isPresent()) {
        Responsavel responsavel = responsavelOptional.get();
        mv.addObject("responsavel", responsavel);
        mv.addObject("responsavelId", id);
    } else {
        mv.setViewName("redirect:/admin");
    }
    return mv;
}

    
    
    
    
@PutMapping("/editar/{id}")
public ResponseEntity<Responsavel> atualizarResponsavel(@PathVariable Long id, @RequestBody Responsavel responsavel) {
    Optional<Responsavel> busca = repository.findById(id);
    if (busca.isPresent()) {
        Responsavel responsavelExistente = busca.get();
        // Atualiza os atributos do responsável existente com os dados recebidos no corpo da requisição
        responsavelExistente.setNome(responsavel.getNome());
        // Não é necessário definir manualmente o ID
        Responsavel atualizado = repository.save(responsavelExistente);
        System.out.println("Responsável atualizado: " + responsavelExistente);
        
            return ResponseEntity.ok(atualizado);
            

    } else {
        System.out.println("Responsável não encontrado para atualização");
        return ResponseEntity.notFound().build();
    }
}

    

    
    
    @DeleteMapping("/{id}")
    public RedirectView deleteResponsavel(@PathVariable Long id) {
        Responsavel responsavel = repository.findById(id).orElse(null);
        if (responsavel != null) {
            repository.delete(responsavel);
            System.out.println("Deletado");
        } else {
            System.out.println("Responsável não encontrado para deleção");
        }
        return new RedirectView("/admin", true);
    }
}

