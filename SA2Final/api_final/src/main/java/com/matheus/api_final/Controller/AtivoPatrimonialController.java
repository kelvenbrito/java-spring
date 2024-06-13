package com.matheus.api_final.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import com.matheus.api_final.Model.AtivoPatrimonial;
import com.matheus.api_final.Repository.AtivoPatrimonialRepository;

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
@RequestMapping("/ativo")
public class AtivoPatrimonialController {

    @Autowired
    AtivoPatrimonialRepository repository;

    @GetMapping()
    public List<AtivoPatrimonial> getAtivoPatrimonial() {
        List<AtivoPatrimonial> ativo = (List<AtivoPatrimonial>) repository.findAll();
        System.out.println(ativo);
        return ativo;
    }

    @PostMapping()
    public RedirectView postAtivoPatrimonial(@RequestParam("idAtivo") Long id,
                                  @RequestParam("nomeAtivo") String nome) {
        AtivoPatrimonial ativo = new AtivoPatrimonial();
        ativo.setId(id);
        ativo.setNome(nome);
        AtivoPatrimonial salvo = repository.save(ativo);
        System.out.println("Salvo: " + salvo);

        return new RedirectView("/admin", true); // Redireciona para a página "/admin"
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AtivoPatrimonial>> getAtivoById(@PathVariable Long id) {
        Optional<AtivoPatrimonial> ativo = repository.findById(id);
        if (ativo.isPresent()) {
            System.out.println(ativo);
            return ResponseEntity.ok(ativo);
        } else {
            System.out.println("Ativo não encontrado");
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<AtivoPatrimonial> putAtivo(@PathVariable Long id, @RequestBody AtivoPatrimonial ativo) {
        Optional<AtivoPatrimonial> busca = repository.findById(id);
        if (busca.isPresent()) {
            ativo.setId(id);
            AtivoPatrimonial atualizado = repository.save(ativo);
            System.out.println("Atualizado: " + atualizado);
            return ResponseEntity.ok(atualizado);
        } else {
            System.out.println("Ativo não encontrado para atualização");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/{id}")
    public RedirectView deleteAtivo(@PathVariable Long id) {
        AtivoPatrimonial ativo = repository.findById(id).orElse(null);
        if (ativo != null) {
            repository.delete(ativo);
            System.out.println("Deletado");
        } else {
            System.out.println("Ativo não encontrado para deleção");
        }

        return new RedirectView("/admin", true); // Redireciona para a página "/admin"
    }

@GetMapping("/editar/{id}")
public String editarAtivo(@PathVariable Long id, Model model) {
    Optional<AtivoPatrimonial> ativoOptional = repository.findById(id);
    if (ativoOptional.isPresent()) {
        AtivoPatrimonial ativo = ativoOptional.get();
        model.addAttribute("ativo", ativo);
        return "editarAtivo"; // Retorna a página de edição
    } else {
        return "redirect:/admin"; // Redireciona para a página principal
    }
}


}
