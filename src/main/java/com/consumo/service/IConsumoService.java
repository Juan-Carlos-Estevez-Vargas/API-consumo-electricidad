package com.consumo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.consumo.entity.Consumo;

@Service
public interface IConsumoService {
	List<Consumo> list();
}
