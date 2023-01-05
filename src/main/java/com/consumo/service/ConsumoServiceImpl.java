package com.consumo.service;

import java.util.ArrayList;
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
	public List<Double> getConsumoByDate(String meter_date) {
		double mayor = 0;
		double menor = 9999999;
		double consumoDiario = 0;
		String startWith = "00";

		List<Consumo> consumosPorDia = consumoRepository.getConsumoByDate(meter_date);
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
				startWith = "0" + String.valueOf(i);
			}
			consumoDiario = mayor - menor;
			consumosPorHora.add(consumoDiario);
		}

		return consumosPorHora;
	}

	@Override
	public List<Double> getConsumoByMonth(String meter_date) {
		String[] dateParts = meter_date.split("-");
		String year = dateParts[0];
		String month = dateParts[1];

		List<Double> resultados = new ArrayList<>();

		for (int i = 1; i <= 31; i++) {
			String datettt = "00";
			if (i >= 1 && i <= 9) {
				datettt = year + "-" + month + "-0" + String.valueOf(i);
			} else {
				datettt = year + "-" + month + "-" + String.valueOf(i);
			}
			try {
			List<Consumo> consumosPorDia = consumoRepository.getConsumoByDate(datettt);
			if (consumosPorDia != null) {
				Double menor = consumosPorDia.get(0).getActiveEnergy();
				Double mayor = consumosPorDia.get(consumosPorDia.size() - 1).getActiveEnergy();
				Double consumoDia = mayor - menor;
				resultados.add(consumoDia);
			} else {
				resultados.add(0.0);
			}}catch (Exception e) {
				System.err.println("Pasó un error");
				resultados.add(0.0);
			}
			
		}

		/*
		 * for (int i = 12; i <= 31; i++) { String datettt =
		 * year+"-"+month+"-"+String.valueOf(i); List<Consumo> consumosPorDia =
		 * consumoRepository.getConsumoByDate(datettt); Double menor =
		 * consumosPorDia.get(0).getActiveEnergy(); Double mayor =
		 * consumosPorDia.get(consumosPorDia.size()-1).getActiveEnergy(); Double
		 * consumoDia = mayor - menor; resultados.add(consumoDia); }
		 */
		// List<Consumo> consumosPorDia =
		// consumoRepository.getConsumoByDate("2022-10-12");
		// Double mayor = consumosPorDia.get(0).getActiveEnergy();
		// Double menor = consumosPorDia.get(consumosPorDia.size()-1).getActiveEnergy();
		// Double consumoDia = mayor - menor;
		// System.out.println(Math.abs(consumoDia));

		return resultados;
		// return consumoRepository.getConsumoByMonth(meter_date_one, meter_date_last);
	}

}
