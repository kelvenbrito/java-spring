package com.matheus.api_final.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Entity
@Setter
@Getter
public class Ambiente implements Serializable {
    //atributos
    @Id
    private Long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Responsavel responsavel;

}