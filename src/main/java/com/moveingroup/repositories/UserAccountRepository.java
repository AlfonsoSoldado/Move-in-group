package com.moveingroup.repositories;

import org.springframework.data.repository.CrudRepository;

import com.moveingroup.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
