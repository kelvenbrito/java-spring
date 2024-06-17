package com.matheus.api_final.Repository;


import org.springframework.data.repository.CrudRepository;

import com.matheus.api_final.Model.AtivoPatrimonial;


public interface AtivoPatrimonialRepository extends CrudRepository<AtivoPatrimonial,Long> {
    
}