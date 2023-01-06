package com.consumo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	public List<Double> getConsumoByDate(String meterDate) {
		double mayor = 0;
		double menor = 9999999;
		double consumoDiario = 0;
		String startWith = "00";

		List<Consumo> consumosPorDia = consumoRepository.getConsumoByDate(meterDate);
		List<Double> consumosPorHora = new ArrayList<>();

		for (int i = 1; i <= 24; i++) {
			for (Consumo consumo : consumosPorDia) {
				if (consumo.getMeterHour().startsWith(startWith)) {
					if (consumo.getActiveEnergy() > mayor) {
						mayor = consumo.getActiveEnergy();
					}
					if (consumo.getActiveEnergy() < menor) {
						menor = consumo.getActiveEnergy();
					}
				}
			}
			if (i >= 11 && i < 24) {
				startWith = String.valueOf(i);
			} else if (startWith.startsWith("0")) {
				startWith = String.valueOf("0" + i);
			}
			consumoDiario = mayor - menor;
			consumosPorHora.add(consumoDiario);
		}

		return consumosPorHora;
	}

	@Override
	public List<Double> getConsumoByMonth(String meterDate) {
		String[] dateParts = meterDate.split("-");
		String year = dateParts[0];
		String month = dateParts[1];

		List<Double> resultados = new ArrayList<>();

		for (int i = 1; i <= 31; i++) {
			String datettt = "00";
			if (i >= 1 && i <= 9) {
				datettt = String.valueOf(year + "-" + month + "-0" + i);
			} else {
				datettt = String.valueOf(year + "-" + month + "-" + i);
			}

			getConsumosByDay(datettt, resultados);

		}

		return resultados;
	}

	@Override
	public List<Double> getConsumoByWeek(String meterDate) {
		// Crea un objeto Calendar para representar la fecha de hoy
		Calendar hoy = Calendar.getInstance();

		// Establece la fecha en la que quieres calcular la semana
		hoy.set(2022, Calendar.OCTOBER, 26);

		// Obtiene el día de la semana de la fecha establecida
		int diaSemana = hoy.get(Calendar.DAY_OF_WEEK);
		List<Double> resultados = new ArrayList<>();

		// Obtiene el primer día de la semana (lunes)
		hoy.add(Calendar.DATE, -diaSemana + Calendar.MONDAY);

		// Imprime las fechas de la semana
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 7; i++) {
			hoy.add(Calendar.DATE, 1);
			getConsumosByDay(sdf.format(hoy.getTime()), resultados);
		}

		return resultados;
	}

	/**
	 * Calcula los consumos por día de una fecha específica.
	 * 
	 * @param meter_date    a calcular los consumos por día.
	 * @param consumosByDay lista a agregar el consumo diario.
	 * @author Juan Carlos Estevez Vargas.
	 */
	private void getConsumosByDay(String meterDate, List<Double> consumosByDay) {
		try {
			List<Consumo> consumosPorDia = consumoRepository.getConsumoByDate(meterDate);
			if (consumosPorDia != null) {
				Double menor = consumosPorDia.get(0).getActiveEnergy();
				Double mayor = consumosPorDia.get(consumosPorDia.size() - 1).getActiveEnergy();
				Double consumoDia = mayor - menor;
				consumosByDay.add(consumoDia);
			} else {
				consumosByDay.add(0.0);
			}
		} catch (Exception e) {
			consumosByDay.add(0.0);
		}
	}
}
