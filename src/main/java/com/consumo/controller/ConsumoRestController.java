package com.consumo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumo.entity.Consumer;
import com.consumo.entity.Consumo;
import com.consumo.service.IConsumoService;

@RestController
@RequestMapping("/consumo")
public class ConsumoRestController {

	@Autowired
	private IConsumoService consumoService;

	@GetMapping("/findAll")
	public List<Consumo> list() {
		return consumoService.list();
	}

	@GetMapping("/daily")
	public List<Double> daily(@RequestBody Consumer request) {
		if (request.getPeriod().equalsIgnoreCase("daily")) {
			return consumoService.getConsumoByDate(request.getDate());
		}
		return new ArrayList<>();
	}

	@GetMapping("/monthly")
	public Map<String, Double> monthly(@RequestBody Consumer request) {
		if (request.getPeriod().equalsIgnoreCase("monthly")) {
			return consumoService.getConsumoByMonth(request.getDate());
		}
		return new HashMap<>();
	}

	@GetMapping("/weekly")
	public Map<String, Double> weekly(@RequestBody Consumer request) {
		if (request.getPeriod().equalsIgnoreCase("weekly")) {
			return consumoService.getConsumoByWeek(request.getDate());
		}
		return new HashMap<>();
	}

}
