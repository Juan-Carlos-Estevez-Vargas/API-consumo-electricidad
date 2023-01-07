package com.consumo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumo.entity.RequestData;
import com.consumo.entity.ElectricalConsumtion;
import com.consumo.service.IConsumoService;

@RestController
@RequestMapping("/consumption")
public class ConsumptionRestController {

	@Autowired
	private IConsumoService consumptionService;

	@GetMapping("/findAll")
	public List<ElectricalConsumtion> list() {
		return consumptionService.list();
	}

	@GetMapping("/daily")
	public Map<String, Double> daily(@RequestBody RequestData request) {
		if (request.getPeriod().equalsIgnoreCase("daily")) {
			return consumptionService.getConsumptionByDate(request.getDate());
		}
		return new HashMap<>();
	}

	@GetMapping("/monthly")
	public Map<String, Double> monthly(@RequestBody RequestData request) {
		if (request.getPeriod().equalsIgnoreCase("monthly")) {
			return consumptionService.getConsumoByMonth(request.getDate());
		}
		return new HashMap<>();
	}

	@GetMapping("/weekly")
	public Map<String, Double> weekly(@RequestBody RequestData request) {
		if (request.getPeriod().equalsIgnoreCase("weekly")) {
			return consumptionService.getConsumptionByWeek(request.getDate());
		}
		return new HashMap<>();
	}

}
