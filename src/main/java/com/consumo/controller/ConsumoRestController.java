package com.consumo.controller;

import java.util.List;

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
		return null;
	}

	@GetMapping("/monthly")
	public List<Double> monthly(@RequestBody Consumer request) {
		if (request.getPeriod().equalsIgnoreCase("monthly")) {
			return consumoService.getConsumoByMonth(request.getDate());
		}
		return null;
	}

	@GetMapping("/weekly")
	public List<Double> weekly(@RequestBody Consumer request) {
		if (request.getPeriod().equalsIgnoreCase("weekly")) {
			return consumoService.getConsumoByWeek(request.getDate());
		}
		return null;
	}

}
