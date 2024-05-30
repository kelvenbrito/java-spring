package br.com.kelven.apirest_senai.Model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
public class Responsavel implements Serializable {
    //atributos
    @Id
    private Long id;

    private String nome;

}
