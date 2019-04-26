package com.moveingroup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.moveingroup.entities.Amigos;

public interface AmigosRepository extends CrudRepository<Amigos, Long> {

	@Query("select a from Amigos a where (a.amigoA.id = :idUsuario or a.amigoB.id = :idUsuario) and a.yaEsAmigo = true")
	List<Amigos> getMisAmigos(@Param("idUsuario") Long idUsuario);
	
	@Query("select a from Amigos a where a.amigoB.id = :idUsuario and a.yaEsAmigo = false")
	List<Amigos> getMisPeticionesDeAmistad(@Param("idUsuario") Long idUsuario);
}
