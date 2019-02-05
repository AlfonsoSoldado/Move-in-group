package com.moveingroup.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.moveingroup.entities.UsuarioApuntado;

public interface UsuarioApuntadoRepository extends CrudRepository<UsuarioApuntado, Long> {

	
	UsuarioApuntado findByActividadId(@Param("id") Long id);
}
