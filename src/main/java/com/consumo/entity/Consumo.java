package com.consumo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "consumos")
@Data
public class Consumo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "active_energy")
	private Double activeEnergy;

	@Column(name = "meter_date")
	private Date meterDate;

	@Column(name = "meter_id")
	private String meterId;
	
	@Column(name = "meter_hour")
	private String meterHour;
}
