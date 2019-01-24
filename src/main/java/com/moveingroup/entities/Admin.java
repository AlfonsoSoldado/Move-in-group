package com.moveingroup.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Admin {

	public Admin() {
		super();
	}
	
	@Id
	@GeneratedValue
	private Long id;
}
