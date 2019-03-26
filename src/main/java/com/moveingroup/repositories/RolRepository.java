package com.moveingroup.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.moveingroup.entities.Rol;

public interface RolRepository extends CrudRepository<Rol, Long> {

	Rol findByTipoRol(@Param("tipoRol") String tipoRol);
}
