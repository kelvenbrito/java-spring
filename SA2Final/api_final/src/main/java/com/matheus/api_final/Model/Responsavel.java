package com.matheus.api_final.Model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Setter
@Getter

public class Responsavel implements Serializable{
    //atributos
    @Id
    private Long id;
    private String nome;


}