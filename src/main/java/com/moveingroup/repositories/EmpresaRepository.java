package com.moveingroup.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.moveingroup.entities.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {

	@Query("select count(e) from Empresa e")
	long empresaCount();
}
