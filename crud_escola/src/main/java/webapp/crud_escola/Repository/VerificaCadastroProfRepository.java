package webapp.crud_escola.Repository;

import org.springframework.data.repository.CrudRepository;
import webapp.crud_escola.Model.VerificaCadastroProf;

public interface VerificaCadastroProfRepository extends CrudRepository<VerificaCadastroProf, String> {

    VerificaCadastroProf findByCpf(String cpf);

    VerificaCadastroProf findByNome(String nome);

}
