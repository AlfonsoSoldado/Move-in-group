package com.moveingroup.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.moveingroup.entities.UsuarioApuntado;

public interface UsuarioApuntadoRepository extends CrudRepository<UsuarioApuntado, Long> {

	
	List<UsuarioApuntado> findByActividadId(@Param("id") Long id);
	
	UsuarioApuntado findByActividadIdAndUsuarioId(@Param("actividadId") Long actividadId, @Param("usuarioId") Long usuarioId);
}
