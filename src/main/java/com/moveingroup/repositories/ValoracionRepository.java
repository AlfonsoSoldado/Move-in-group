package com.moveingroup.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.moveingroup.entities.Valoracion;

public interface ValoracionRepository extends CrudRepository<Valoracion, Long> {

	
	@Query("select count(v) from Valoracion v where v.medalla = :medalla")
	long countByMedalla(@Param("medalla") String medalla);
}
