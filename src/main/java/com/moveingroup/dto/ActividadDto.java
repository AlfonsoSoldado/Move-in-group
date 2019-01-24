package com.moveingroup.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ActividadDto {

	private Long id;
	
	private String name;
	
	private Date moment;
	
	private String city;
	
	private String country;
	
	private String address;
	
	private String type;
	
	private Integer price;
}
