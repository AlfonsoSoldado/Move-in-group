package com.moveingroup.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.moveingroup.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

	@Query("select ur from UserAccount ur where ur.username = :username " + "and ur.usuario.id = :idUsuario")
	UserAccount loginWithUsername(@Param("username") String username, @Param("idUsuario") Long idUsuario);
}
