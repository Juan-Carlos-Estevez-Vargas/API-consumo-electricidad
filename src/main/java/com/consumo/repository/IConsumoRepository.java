package com.consumo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.consumo.entity.Consumo;

@Repository
public interface IConsumoRepository extends CrudRepository<Consumo, Integer>{

}
