package com.moveingroup.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.UserAccountDto;
import com.moveingroup.security.JwtUtil;
import com.moveingroup.services.UserAccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserAccountService userAccountService;

	@RequestMapping(value = "loginUsuario", method = RequestMethod.POST)
	public String loginUsuario(@RequestBody UserAccountDto userAccountDto, HttpServletResponse res) {

		String token = "";
		try {
			UserAccountDto userAccount = userAccountService.loginWithUsuario(userAccountDto.getUsername(),
					userAccountDto.getPassword());
			if (userAccount != null) {
				token = jwtUtil.addAuthenticationUsuario(res, userAccount);
			}
		} catch (Exception e) {
			log.error(e.getMessage());

		}
		return token;
	}
	
	@RequestMapping(value = "loginEmpresa", method = RequestMethod.POST)
	public String loginEmpresa(@RequestBody UserAccountDto userAccountDto, HttpServletResponse res) {

		String token = "";
		try {
			UserAccountDto userAccount = userAccountService.loginWithUsuario(userAccountDto.getUsername(),
					userAccountDto.getPassword());
			if (userAccount != null) {
				token = jwtUtil.addAuthenticationEmpresa(res, userAccount);
			}
		} catch (Exception e) {
			log.error(e.getMessage());

		}
		return token;
	}
}
