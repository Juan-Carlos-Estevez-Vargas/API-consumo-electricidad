package com.consumo.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consumo.entity.ElectricalConsumtion;
import com.consumo.repository.IConsumptionRepository;

@Service
public class ConsumptionServiceImpl implements IConsumptionService {

	@Autowired
	private IConsumptionRepository consumptionRepository;

	@Override
	public List<ElectricalConsumtion> list() {
		return (List<ElectricalConsumtion>) consumptionRepository.findAll();
	}

	@Override
	public Map<String, Double> getConsumptionByDate(String meterDate) {
    SortedMap<String, Double> consumptionPerHour = new TreeMap<>();
    List<ElectricalConsumtion> consumptionPerDay = consumptionRepository.getConsumptionByDate(meterDate);

    for (int hour = 0; hour < 24; hour++) {
        double maximumConsumption = 0;
        double minimumConsumption = Double.MAX_VALUE;

        String hourString = String.format("%02d", hour);
        for (ElectricalConsumtion consumption : consumptionPerDay) {
            if (consumption.getMeterHour().startsWith(hourString)) {
                maximumConsumption = Math.max(maximumConsumption, consumption.getActiveEnergy());
                minimumConsumption = Math.min(minimumConsumption, consumption.getActiveEnergy());
            }
        }

        double dailyConsumption = maximumConsumption - minimumConsumption;
        consumptionPerHour.put(hourString + ":00:00", dailyConsumption >= 0 ? dailyConsumption : 0.0);
    }

    return consumptionPerHour;
	}

	@Override
	public Map<String, Double> getConsumoByMonth(String meterDate) {
    SortedMap<String, Double> consumptionMap = new TreeMap<>();
    String[] dateParts = meterDate.split("-");
    String year = dateParts[0];
    String month = dateParts[1];

    for (int i = 1; i <= 31; i++) {
        String date;
        if (i < 10) {
            date = String.format("%s-%s-0%s", year, month, i);
        } else {
            date = String.format("%s-%s-%s", year, month, i);
        }
        
        getConsumptionsByDay(date, consumptionMap);
    }

    return consumptionMap;
	}

	@Override
	public Map<String, Double> getConsumptionByWeek(String meterDate) {
    SortedMap<String, Double> consumptionPerDays = new TreeMap<>();
    
    String[] dateParts = meterDate.split("-");
    String year = dateParts[0];
    String month = dateParts[1];
    String day = dateParts[2];

    Calendar date = Calendar.getInstance();
    date.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));

    int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);

    date.add(Calendar.DATE, -dayOfWeek - 1 + Calendar.MONDAY);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    for (int i = 0; i < 7; i++) {
        date.add(Calendar.DATE, 1);
        getConsumptionsByDay(sdf.format(date.getTime()), consumptionPerDays);
    }

    return consumptionPerDays;
	}

	/**
	 * Calculates the daily consumption for a specific date.
	 * 
	 * @param meterDate      the date to calculate the daily consumption for.
	 * @param consumptionsByDay the map to add the daily consumption to.
	 * @author Juan Carlos Estevez Vargas.
	 */
	private void getConsumptionsByDay(String meterDate, SortedMap<String, Double> consumptionsByDay) {
    try {
        List<ElectricalConsumtion> consumptions = consumptionRepository.getConsumptionByDate(meterDate);
        if (consumptions != null) {
            Double minimumConsumption = consumptions.get(0).getActiveEnergy();
            Double maximumConsumption = consumptions.get(consumptions.size() - 1).getActiveEnergy();
            Double consumptionPerDay = maximumConsumption - minimumConsumption;
            consumptionsByDay.put(meterDate, consumptionPerDay);
        } else {
            consumptionsByDay.put(meterDate, 0.0);
        }
    } catch (Exception e) {
        consumptionsByDay.put(meterDate, 0.0);
    }
	}

}
