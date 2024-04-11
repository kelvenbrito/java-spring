package webapp.escola_completo.Repository;

import org.springframework.data.repository.CrudRepository;
import webapp.escola_completo.Model.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, String>{
    Professor findByCpf(String cpf);
    Professor findBySenha(String senha);

    
}

