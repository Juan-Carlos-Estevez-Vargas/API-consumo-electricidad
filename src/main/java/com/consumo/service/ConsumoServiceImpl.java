package com.consumo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consumo.entity.Consumo;
import com.consumo.repository.IConsumoRepository;

@Service
public class ConsumoServiceImpl implements IConsumoService{
	
	@Autowired
	private IConsumoRepository consumoRepository;

	@Override
	public List<Consumo> list() {
		return (List<Consumo>) consumoRepository.findAll();
	}

	@Override
	public List<Double> getConsumoByDate(String meter_date) {
		List<Consumo> consumosPorDia = consumoRepository.getConsumoByDate(meter_date);
		List<Double> consumosPorHora = new ArrayList<>();
		double mayor = 0;
		double menor = 9999999;
		double consumoDiario = 0;
		String startWith = "00";
		
		for (int i = 0; i <= 10; i++) {
			for (Consumo consumo : consumosPorDia) {
				if (consumo.getMeterHour().startsWith(startWith)) {
					if (consumo.getActiveEnergy() > mayor) {
						mayor = consumo.getActiveEnergy();
					} 
					if(consumo.getActiveEnergy() < menor) {
						menor = consumo.getActiveEnergy();
					}
				}	
			}
			if (startWith.startsWith("0")) {
				startWith = "0" + String.valueOf(i);
				System.out.println(startWith);
			}
			consumoDiario = mayor - menor;
			consumosPorHora.add(consumoDiario);
		}
		
		startWith = "10";
		for (int i = 10; i <= 24; i++) {
			for (Consumo consumo : consumosPorDia) {
				if (consumo.getMeterHour().startsWith(startWith)) {
					if (consumo.getActiveEnergy() > mayor) {
						mayor = consumo.getActiveEnergy();
					} 
					if(consumo.getActiveEnergy() < menor) {
						menor = consumo.getActiveEnergy();
					}
				}	
			}
			startWith = String.valueOf(i);
			
			consumoDiario = mayor - menor;
			consumosPorHora.add(consumoDiario);
		}
		return consumosPorHora;
	}

	
	
}
