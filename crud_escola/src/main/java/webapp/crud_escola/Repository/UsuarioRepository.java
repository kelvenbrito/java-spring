package webapp.crud_escola.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import webapp.crud_escola.Model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findByCpf(String cpf);

    List<Usuario> findByDisciplina1OrDisciplina2(String disciplina1, String disciplina2);

}
