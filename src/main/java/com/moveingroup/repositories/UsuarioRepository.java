package com.moveingroup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.moveingroup.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	List<Usuario> findTop10ByOrderByValoracionRangoDesc();
	
	@Query("select count(u) from Usuario u")
	long usuarioCount();
}
