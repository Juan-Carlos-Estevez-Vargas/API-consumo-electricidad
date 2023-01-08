package com.consumo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.consumo.entity.RequestData;
import com.consumo.repository.IConsumptionRepository;

public class TestConsumptionServiceImpl {
	
	private IConsumptionService consumptionService;
	private RequestData requestData;
	
	@BeforeEach
	public void init() {
		Mockito.mock(IConsumptionRepository.class);
		consumptionService = new ConsumptionServiceImpl();
		requestData = new RequestData();
	}
	
	@Test
	void testweek() {
		SortedMap<String, Double> esperatedMap = new TreeMap<String, Double>();
		esperatedMap.put("2022-10-10", 0.0);
		esperatedMap.put("2022-10-11", 0.0);
		esperatedMap.put("2022-10-12", 539.76);
		esperatedMap.put("2022-10-13", 571.9000000000001);
		esperatedMap.put("2022-10-14", 558.7499999999998);
		esperatedMap.put("2022-10-15", 547.8400000000001);
		esperatedMap.put("2022-10-16", 390.27);
		
		requestData.setDate("2022-10-12");
		requestData.setPeriod("weekly");

		Mockito.when(consumptionService.getConsumptionByWeek(ArgumentMatchers.any())).thenReturn(esperatedMap);
				
		Map<String, Double> realVaue = consumptionService.getConsumptionByWeek(requestData.getDate());
		
		assertEquals(esperatedMap, realVaue);
		
		
		
	}

}
