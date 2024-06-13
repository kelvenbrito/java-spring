package com.matheus.api_final.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.matheus.api_final.Model.Ambiente;
import com.matheus.api_final.Repository.AmbienteRepository;

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
@RequestMapping("/ambiente")
public class AmbienteController {

    @Autowired
    AmbienteRepository repository;

    @GetMapping()
    public List<Ambiente> getAmbiente() {
        List<Ambiente> ambientes = (List<Ambiente>) repository.findAll();
        System.out.println(ambientes);
        return ambientes;
    }

    @PostMapping()
    public RedirectView postAmbiente(@RequestParam("idAmbiente") Long id,
                                     @RequestParam("nomeAmbiente") String nome) {
        Ambiente ambiente = new Ambiente();
        ambiente.setId(id);
        ambiente.setNome(nome);
        Ambiente salvo = repository.save(ambiente);
        System.out.println("Salvo: " + salvo);

        return new RedirectView("/admin", true); // Redireciona para a página "/admin"
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ambiente>> getAmbienteById(@PathVariable Long id) {
        Optional<Ambiente> ambiente = repository.findById(id);
        if (ambiente.isPresent()) {
            System.out.println(ambiente);
            return ResponseEntity.ok(ambiente);
        } else {
            System.out.println("Ambiente não encontrado");
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Ambiente> putAmbiente(@PathVariable Long id, @RequestBody Ambiente ambiente) {
        Optional<Ambiente> busca = repository.findById(id);
        if (busca.isPresent()) {
            ambiente.setId(id);
            Ambiente atualizado = repository.save(ambiente);
            System.out.println("Atualizado: " + atualizado);
            return ResponseEntity.ok(atualizado);
        } else {
            System.out.println("Ambiente não encontrado para atualização");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/{id}")
    public RedirectView deleteAmbiente(@PathVariable Long id) {
        Ambiente ambiente = repository.findById(id).orElse(null);
        if (ambiente != null) {
            repository.delete(ambiente);
            System.out.println("Deletado");
        } else {
            System.out.println("Ambiente não encontrado para deleção");
        }

        return new RedirectView("/admin", true); // Redireciona para a página "/admin"
    }

    @GetMapping("/editar/{id}")
public String editarAmbiente(@PathVariable Long id, Model model) {
    Optional<Ambiente> ambienteOptional = repository.findById(id);
    if (ambienteOptional.isPresent()) {
        Ambiente ambiente = ambienteOptional.get();
        model.addAttribute("ambiente", ambiente);
        return "editarAmbiente"; // Retorna a página de edição
    } else {
        return "redirect:/admin"; // Redireciona para a página principal
    }
}


}
