package webapp.escola_completo.Model;

import java.io.Serializable;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Materias implements Serializable {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String mnome;
    private double nota;
    private double pfaltas;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aluno", referencedColumnName = "ra")
    private Aluno aluno;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMnome() {
        return mnome;
    }

    public void setMnome(String mnome) {
        this.mnome = mnome;
    }

    

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    public double getPfaltas() {
        return pfaltas;
    }

    public void setPfaltas(double pfaltas) {
        this.pfaltas = pfaltas;
    }
   
    
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

   


    



    
}