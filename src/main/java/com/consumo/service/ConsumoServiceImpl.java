package com.consumo.service;

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
		// TODO Auto-generated method stub
		return (List<Consumo>) consumoRepository.findAll();
	}

	/*@Override
	public List<T> list() {
		List<T> returnList = new ArrayList<>();
		getRepository().findAll().forEach(returnList::add);
		return returnList;
	}*/
	
}
