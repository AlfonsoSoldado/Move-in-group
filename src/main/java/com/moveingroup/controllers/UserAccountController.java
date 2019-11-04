package com.moveingroup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.UserAccountDto;
import com.moveingroup.services.UserAccountService;

@RestController
@RequestMapping("/userAccount")
public class UserAccountController {
	
	@Autowired
	private UserAccountService userAccountService;

	@RequestMapping(value = "userAccount", method = RequestMethod.POST)
	public UserAccountDto save(@RequestBody UserAccountDto userAccountDto) {
		return userAccountService.save(userAccountDto);
	}
	
	@GetMapping("/findByUsername/{username}")
	public UserAccountDto findByUsername(@PathVariable String username) {
		return userAccountService.findByUsername(username);
	}
}
