package com.matheus.api_final.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import com.matheus.api_final.Model.Responsavel;
import com.matheus.api_final.Repository.ResponsavelRepository;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/responsavel")
public class ResponsavelController {
    
    @Autowired
    ResponsavelRepository repository;

    @GetMapping()
    public String getResponsavel(Model model) {
        List<Responsavel> responsaveis = (List<Responsavel>) repository.findAll();
        model.addAttribute("responsaveis", responsaveis);
        return "paginaAdmin"; // Retorna a página principal
    }

    @PostMapping()
    public RedirectView postResponsavel(@RequestParam("idResponsavel") Long id,
                                        @RequestParam("nomeResponsavel") String nome) {
        Responsavel responsavel = new Responsavel();
        responsavel.setId(id);
        responsavel.setNome(nome);
        repository.save(responsavel);
        return new RedirectView("/admin", true); // Redireciona para a página "/admin"
    }

    @GetMapping("/editar/{id}")
    public String editarResponsavel(@PathVariable Long id, Model model) {
        Optional<Responsavel> responsavel = repository.findById(id);
        if (responsavel.isPresent()) {
            model.addAttribute("responsavel", responsavel.get());
            return "editarResponsavel"; // Retorna a página de edição
        } else {
            return "redirect:/responsavel"; // Redireciona para a página principal se o responsável não for encontrado
        }
    }


    

    @PostMapping("/alterar/{id}")
    public ResponseEntity<RedirectView> putResponsavel(@PathVariable Long id,
                                                @RequestParam("idResponsavel") Long idResponsavel,
                                                @RequestParam("nomeResponsavel") String nomeResponsavel) {
        Optional<Responsavel> busca = repository.findById(id);
        if (busca.isPresent()) {
            Responsavel responsavelAtualizado = new Responsavel();
            responsavelAtualizado.setId(idResponsavel);
            responsavelAtualizado.setNome(nomeResponsavel);
    
            repository.save(responsavelAtualizado);
            System.out.println("Atualizado: " + responsavelAtualizado);
    
            return ResponseEntity.ok(new RedirectView("/admin", true));
        } else {
            System.out.println("Responsável não encontrado para atualização");
            return ResponseEntity.notFound().build();
        }
    }
    
    

   

    // @DeleteMapping("/deletar/{id}")
    // public ResponseEntity<RedirectView> deleteResponsavel(@PathVariable Long id) {
    //   Responsavel responsavel = repository.findById(id).orElse(null);
    //   if (responsavel != null) {
    //     repository.delete(responsavel);
    //     return ResponseEntity.ok().build(); // Retorna status 200 OK
    //   } else {
    //     return ResponseEntity.notFound().build(); // Retorna status 404 Not Found
    //   }
    // }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> DeleteResponsavel(@PathVariable Long id) {
      try {
        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
      } catch (Exception e) {
        System.out.println("Error deleting Responsavel with ID: " + id);
        return ResponseEntity.notFound().build(); // Return 404 Not Found if deletion fails
      }
    }



    
    
}