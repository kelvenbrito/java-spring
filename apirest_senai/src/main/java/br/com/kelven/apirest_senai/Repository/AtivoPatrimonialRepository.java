package br.com.kelven.apirest_senai.Repository;

import org.springframework.data.repository.CrudRepository;

import br.com.kelven.apirest_senai.Model.Ambiente;

public interface AtivoPatrimonialRepository extends CrudRepository<Ambiente,Long>{
    
}
