package com.moveingroup.repositories;

import org.springframework.data.repository.CrudRepository;

import com.moveingroup.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
