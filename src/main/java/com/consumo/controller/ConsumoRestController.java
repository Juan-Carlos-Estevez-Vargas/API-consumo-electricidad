package com.consumo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
