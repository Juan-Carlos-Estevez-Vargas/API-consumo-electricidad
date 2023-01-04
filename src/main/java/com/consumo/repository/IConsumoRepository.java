package com.consumo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.consumo.entity.Consumo;

@Repository
public interface IConsumoRepository extends CrudRepository<Consumo, Integer>{
	
	@Query(value = "SELECT * FROM CONSUMOS WHERE METER_DATE=:meter_date", nativeQuery = true)
	List<Consumo> getConsumoByDate(String meter_date);
	
}
