package com.moveingroup.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Actividad {
	
	public Actividad() {
		super();
	}

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private Date moment;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String typeOfActivity;
	
	@Column(nullable = true)
	private Integer price;
	
}
