package com.moveingroup.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.moveingroup.entities.Actividad;

public interface ActividadRepository extends CrudRepository<Actividad, Long> {

	@Query("select a from Actividad a where a.usuario.id = :id and a.cancelada = false and a.momento > :hoy")
	List<Actividad> findByUsuarioId(@Param("id") Long id, @Param("hoy") Date hoy);

	@Query("select a from Actividad a where a.empresa.id = :id and a.cancelada = false and a.momento > :hoy")
	List<Actividad> findByEmpresaId(@Param("id") Long id, @Param("hoy") Date hoy);

	@Query("select a from Actividad a where a.empresa.id != null")
	Iterable<Actividad> getAllByEmpresas();

	@Query("select a from Actividad a where a.cancelada = false and a.momento > :hoy")
	List<Actividad> findAll(@Param("hoy") Date hoy);

	@Query("select a from Actividad a where (:nombre is null or (UPPER(a.nombre) LIKE CONCAT('%',UPPER(:nombre),'%')))"
			+ "and (:pais is null or (UPPER(a.pais) LIKE CONCAT ('%',UPPER(:pais),'%')))"
			+ "and (:ciudad is null or (UPPER(a.ciudad) LIKE CONCAT ('%',UPPER(:ciudad),'%')))"
			+ "and (:desde is null or a.momento >= :desde)"
			+ "and (:hasta is null or a.momento <= :hasta)")
	List<Actividad> filtrar(@Param("nombre") String nombre, @Param("pais") String pais, @Param("ciudad") String ciudad, @Param("desde") Date desde, @Param("hasta") Date hasta);

	@Query("select a from Actividad a where a.usuario.id = :id and a.cancelada = false and a.momento < :hoy")
	List<Actividad> findActividadesTerminadas(@Param("id") Long id, @Param("hoy") Date hoy);
	
	@Query("select count(a) from Actividad a where a.cancelada = false and a.momento < :hoy")
	Long countActividadesTerminadas(@Param("hoy") Date hoy);
	
	@Query("select count(a) from Actividad a where a.cancelada = false and a.momento > :hoy")
	Long countActividadesActivas(@Param("hoy") Date hoy);
	
	@Query("select count(a) from Actividad a where a.cancelada = true")
	Long countActividadesCanceladas();
}
