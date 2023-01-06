package com.consumo.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Consumer implements Serializable{

	private static final long serialVersionUID = 1L;
	private String date;
	private String period;
}
