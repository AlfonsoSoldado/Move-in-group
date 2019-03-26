package com.moveingroup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.moveingroup.entities.Actividad;

public interface ActividadRepository extends CrudRepository<Actividad, Long> {

	@Query("select a from Actividad a where a.usuario.id = :id and a.cancelada = false")
	List<Actividad> findByUsuarioId(@Param("id") Long id);
	
	@Query("select a from Actividad a where a.empresa.id = :id and a.cancelada = false")
	List<Actividad> findByEmpresaId(@Param("id") Long id);
	
	@Query("select a from Actividad a where a.empresa.id != null")
	List<Actividad> getAllByEmpresas();
	
}
