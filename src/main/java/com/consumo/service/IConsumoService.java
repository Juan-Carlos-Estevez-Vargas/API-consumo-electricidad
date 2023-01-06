package com.consumo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.consumo.entity.Consumo;

@Service
public interface IConsumoService {

	/**
	 * Obtiene un listado con todos los registros de la base de datos de tipo
	 * Consumo.
	 * 
	 * @return Lista con los datos obtenidos de la base de datos.
	 * @author Juan Carlos Estevez Vargas;
	 */
	List<Consumo> list();

	/**
	 * Calcula y obtiene los consumos por hora de una fecha especificada.
	 * 
	 * @param meterDate fecha a obtener los consumos.
	 * @return Listado con los consumos por hora.
	 * @author Juan Carlos Estevz Vargas.
	 */
	List<Double> getConsumoByDate(String meterDate);

	/**
	 * Calcula y obtiene los consumos de electridad diario en un periodo de un mes,
	 * el cual viene especificado en el parámetro de entrada.
	 * 
	 * @param meterDate por el cual se calcularán los consumos de electricidad.
	 * @return Listado con los consumos diarios.
	 * @author Juan Carlos Estevz Vargas.
	 */
	List<Double> getConsumoByMonth(String meterDate);

	/**
	 * Calcula y obtiene los registros de consumo de electricidad por dia en un
	 * periodo de una semana a partir de una fecha especificada.
	 * 
	 * @param meterDate mediante el cual se calcularan los dias de la semana a
	 *                   calcular los consumos de electricidad.
	 * @return Listado con los consumos diarios.
	 * @author Juan Carlos Estevz Vargas.
	 */
	List<Double> getConsumoByWeek(String meterDate);
}
