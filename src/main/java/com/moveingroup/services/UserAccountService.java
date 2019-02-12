package com.moveingroup.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moveingroup.dto.UserAccountDto;
import com.moveingroup.entities.UserAccount;
import com.moveingroup.repositories.UserAccountRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;
	
	
    public UserAccountDto loginWithUsername(String username, String password, Long idExplotacion) {
    	try {
    	    if (userAccountRepository.loginWithUsername(username,
    		    idExplotacion) != null) {
    	    	UserAccount userAccount = userAccountRepository.loginWithUsername(username,
    			idExplotacion);

    		if (userAccount.getPassword().equals(userAccount.getPassword())) {
    		    ModelMapper modelMapper = new ModelMapper();
    		    return modelMapper.map(userAccount, UserAccountDto.class);
    		} else {
    		    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    		    Boolean iguales = passwordEncoder.matches(password, userAccount.getPassword());
    		    if (iguales) {
    			ModelMapper modelMapper = new ModelMapper();
    			return modelMapper.map(userAccount, UserAccountDto.class);
    		    } else {
    			throw new IllegalArgumentException("Contraseña incorrecta.");
    		    }
    		}

    	    } else {
    	    	throw new IllegalArgumentException("Excepción en método loginWithUsername de UserAccountService");
    	    }
    	} catch (Throwable e) {
    	    log.error("Error en el método findByDniAndTelefonoAndIdExplotacion de UsuarioRolService " + e);
    	    throw new IllegalArgumentException("Excepción en método loginWithUsername de UserAccountService");
    	}
        }
}
