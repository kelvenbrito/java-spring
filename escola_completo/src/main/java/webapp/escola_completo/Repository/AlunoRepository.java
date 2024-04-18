package webapp.escola_completo.Repository;
import org.springframework.data.repository.CrudRepository;

import webapp.escola_completo.Model.Aluno;


public interface AlunoRepository extends CrudRepository<Aluno, String>{
    Aluno findByRa(String ra);
    Aluno findBySenha(String senha);
}
