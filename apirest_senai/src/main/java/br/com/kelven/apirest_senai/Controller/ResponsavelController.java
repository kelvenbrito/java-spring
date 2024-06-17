package br.com.kelven.apirest_senai.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import br.com.kelven.apirest_senai.Model.Responsavel;
import br.com.kelven.apirest_senai.Repository.ResponsavelRepository;

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
        return responsaveis;
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Responsavel>> getResponsavelById(@PathVariable Long id) {
        Optional<Responsavel> responsavel = repository.findById(id);
        if (responsavel.isPresent()) {
            return ResponseEntity.ok(responsavel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsavel> putResponsavel(@PathVariable Long id, @RequestBody Responsavel responsavel) {
        Optional<Responsavel> busca = repository.findById(id);
        if (busca.isPresent()) {
            responsavel.setId(id);
            Responsavel atualizado = repository.save(responsavel);
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 


    @DeleteMapping("/{id}")
    public RedirectView deleteResponsavel(@PathVariable Long id) {
        repository.deleteById(id);
        return new RedirectView("/admin", true); // Redireciona para a página "/admin"
    }
}
