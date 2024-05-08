package webapp.escola_completo.Repository;

import org.springframework.data.repository.CrudRepository;
import webapp.escola_completo.Model.Materias;

public interface MateriaRepository extends CrudRepository<Materias, Integer> {
    Materias findById(int id);
    Materias findByMnome(String mnome);
}
