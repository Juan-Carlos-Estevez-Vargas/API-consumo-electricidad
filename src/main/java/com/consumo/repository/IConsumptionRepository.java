package com.consumo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.consumo.entity.ElectricalConsumtion;

@Repository
public interface IConsumptionRepository extends CrudRepository<ElectricalConsumtion, Integer> {

	/**
	 * Obtiene los registros de consumo de electricidad en una fecha especifica.
	 * 
	 * @param meter_date por el cuál se listarán los registros.
	 * @return Lista de tipo Consumo con los registros encontrados.
	 * @author Juan Carlos Estevez Vargas.
	 */
	@Query(value = "SELECT * FROM CONSUMOS WHERE METER_DATE=:meterDate", nativeQuery = true)
	List<ElectricalConsumtion> getConsumptionByDate(String meterDate);

}
