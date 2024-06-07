package webapp.crud_escola.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.crud_escola.Model.VerificaCadastroUsuario;

public interface VerificaCadastroUsuarioRepository extends CrudRepository<VerificaCadastroUsuario, String> {

    VerificaCadastroUsuario findByCpf(String cpf);

    VerificaCadastroUsuario findByNome(String nome);

}
