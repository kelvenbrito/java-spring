package webapp.crud_escola.Repository;

import org.springframework.data.repository.CrudRepository;
import webapp.crud_escola.Model.Professor;

public interface ProfessoresRepository extends CrudRepository<Professor, String> {

    Professor findByCpf(String cpf);

}
