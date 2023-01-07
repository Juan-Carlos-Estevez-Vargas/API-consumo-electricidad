package com.consumo.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consumo.entity.Consumo;
import com.consumo.repository.IConsumoRepository;

@Service
public class ConsumoServiceImpl implements IConsumoService {

	@Autowired
	private IConsumoRepository consumoRepository;

	@Override
	public List<Consumo> list() {
		return (List<Consumo>) consumoRepository.findAll();
	}

	@Override
	public Map<String, Double> getConsumoByDate(String meterDate) {
		double maximumConsumption = 0;
		double minimumConsumption;
		double dailyConsumption = 0;
		String startWith = "00";

		SortedMap<String, Double> consumptionPerHour = new TreeMap<>();
		List<Consumo> consumptionPerDay = consumoRepository.getConsumoByDate(meterDate);

		for (int i = 1; i <= 24; i++) { // Recorriendo la 24 horas del día

			maximumConsumption = 0;
			minimumConsumption = 9999999;

			for (Consumo consumption : consumptionPerDay) { // Por cada consumo
				if (consumption.getMeterHour().startsWith(startWith)) {
					if (consumption.getActiveEnergy() > maximumConsumption) {
						maximumConsumption = consumption.getActiveEnergy();
					}
					if (consumption.getActiveEnergy() < minimumConsumption) {
						minimumConsumption = consumption.getActiveEnergy();
					}

				}
			}

			if (i >= 10) {
				startWith = String.valueOf(i);
			} else {
				startWith = String.valueOf("0" + i);
			}
			dailyConsumption = maximumConsumption - minimumConsumption;

			if (dailyConsumption != -9999999) {
				consumptionPerHour.put(startWith + ":00:00", dailyConsumption);
			} else {
				consumptionPerHour.put(startWith + ":00:00", 0.0);
			}
		}

		return consumptionPerHour;
	}

	@Override
	public Map<String, Double> getConsumoByMonth(String meterDate) {
		SortedMap<String, Double> mapa = new TreeMap<>();
		String[] dateParts = meterDate.split("-");
		String year = dateParts[0];
		String month = dateParts[1];

		for (int i = 1; i <= 31; i++) {
			String datettt;
			if (i >= 1 && i <= 9) {
				datettt = String.valueOf(year + "-" + month + "-0" + i);
			} else {
				datettt = String.valueOf(year + "-" + month + "-" + i);
			}

			getConsumosByDay(datettt, mapa);

		}

		return mapa;

	}

	@Override
	public Map<String, Double> getConsumoByWeek(String meterDate) {
		SortedMap<String, Double> consumptionPerDays = new TreeMap<>();
		String[] dateParts = meterDate.split("-");
		String year = dateParts[0];
		String month = dateParts[1];
		String day = dateParts[2];

		// Crea un objeto Calendar para representar la fecha de hoy
		Calendar date = Calendar.getInstance();

		// Establece la fecha en la que quieres calcular la semana
		date.set(Integer.parseInt(year), evaluateMonth(Integer.parseInt(month) - 1, date), Integer.parseInt(day));

		// Obtiene el día de la semana de la fecha establecida
		int diaSemana = date.get(Calendar.DAY_OF_WEEK);

		// Obtiene el primer día de la semana (lunes)
		date.add(Calendar.DATE, -diaSemana - 1 + Calendar.MONDAY);

		// Imprime las fechas de la semana
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 7; i++) {
			date.add(Calendar.DATE, 1);
			getConsumosByDay(sdf.format(date.getTime()), consumptionPerDays);
		}

		return consumptionPerDays;
	}

	/**
	 * Calcula los consumos por día de una fecha específica.
	 * 
	 * @param meter_date    a calcular los consumos por día.
	 * @param consumosByDay lista a agregar el consumo diario.
	 * @author Juan Carlos Estevez Vargas.
	 */
	private void getConsumosByDay(String meterDate, SortedMap<String, Double> mapa) {
		try {
			List<Consumo> consumosPorDia = consumoRepository.getConsumoByDate(meterDate);
			if (consumosPorDia != null) {
				Double menor = consumosPorDia.get(0).getActiveEnergy();
				Double mayor = consumosPorDia.get(consumosPorDia.size() - 1).getActiveEnergy();
				Double consumoDia = mayor - menor;
				mapa.put(meterDate, consumoDia);
			} else {
				mapa.put(meterDate, 0.0);
			}
		} catch (Exception e) {
			mapa.put(meterDate, 0.0);
		}

	}

	private int evaluateMonth(int month, Calendar date) {
	    int[] months = {
	        Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH, Calendar.APRIL,
	        Calendar.MAY, Calendar.JUNE, Calendar.JULY, Calendar.AUGUST,
	        Calendar.SEPTEMBER, Calendar.OCTOBER, Calendar.NOVEMBER, Calendar.DECEMBER
	    };
	    if (month < 0 || month >= months.length) {
	        throw new IllegalArgumentException("Unexpected value: " + month);
	    }
	    return months[month];
	}

}
