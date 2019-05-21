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

	public UserAccountDto loginWithUsuario(String username, String password) {
		try {
			if (userAccountRepository.findByUsername(username) != null) {
				//TODO: Controlar ROL en el repository
				UserAccount userAccount = userAccountRepository.findByUsername(username);

				if (userAccount.getPassword().equals(password)) {
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
			log.error("Error en el método loginWithUsername de UserAccountService " + e);
			throw new IllegalArgumentException("Excepción en método loginWithUsername de UserAccountService");
		}
	}
	
	public UserAccountDto save(UserAccountDto userAccountDto) {
		ModelMapper modelMapper = new ModelMapper();
		UserAccount userAccount = modelMapper.map(userAccountDto, UserAccount.class);

		try {

			UserAccount savedUserAccount = userAccountRepository.save(userAccount);

			return modelMapper.map(savedUserAccount, UserAccountDto.class);

		} catch (Throwable e) {
			throw new IllegalArgumentException();
			// TODO: Tratar excepción
		}

	}
}
