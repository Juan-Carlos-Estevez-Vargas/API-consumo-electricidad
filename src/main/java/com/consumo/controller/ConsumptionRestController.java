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
import com.consumo.service.IConsumptionService;

@RestController
@RequestMapping("/consumption")
public class ConsumptionRestController {

	@Autowired
	private IConsumptionService consumptionService;

	/**
	 * Retrieves a list of all ElectricalConsumtion objects.
	 *
	 * @return the list of ElectricalConsumtion objects
	 */
	@GetMapping("/findAll")
	public List<ElectricalConsumtion> findAll() {
			return consumptionService.list();
	}

	/**
	 * Retrieves the daily consumption based on the given request data.
	 * 
	 * @param request The request data containing the period and date.
	 * @return A map of consumption values.
	 */
	@GetMapping("/daily")
	public Map<String, Double> daily(@RequestBody RequestData request) {
    // Check if the period is daily
    if (request.getPeriod().equalsIgnoreCase("daily")) {
        // Retrieve the consumption by date
        return consumptionService.getConsumptionByDate(request.getDate());
    }
    // Return an empty map if the period is not daily
    return new HashMap<>();
	}

	/**
	 * Retrieves the monthly consumption based on the provided request data.
	 *
	 * @param request The request data object containing the period and date.
	 * @return A map with the monthly consumption data.
	 */
	@GetMapping("/monthly")
	public Map<String, Double> monthly(@RequestBody RequestData request) {
    // Check if the period is monthly
    if (request.getPeriod().equalsIgnoreCase("monthly")) {
        // Retrieve the monthly consumption based on the provided date
        return consumptionService.getConsumoByMonth(request.getDate());
    }
    
    // Return an empty map if the period is not monthly
    return new HashMap<>();
	}

	/**
	 * Retrieves the weekly consumption data based on the given request.
	 *
	 * @param request the request data containing the period and date
	 * @return a map of consumption data for each week
	 */
	@GetMapping("/weekly")
	public Map<String, Double> weekly(@RequestBody RequestData request) {
    // Check if the period is weekly
    if (request.getPeriod().equalsIgnoreCase("weekly")) {
        // Retrieve and return the consumption data by week
        return consumptionService.getConsumptionByWeek(request.getDate());
    }
    // Return an empty map if the period is not weekly
    return new HashMap<>();
	}

}
