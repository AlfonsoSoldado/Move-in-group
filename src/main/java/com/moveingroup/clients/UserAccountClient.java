package com.moveingroup.clients;

import javax.inject.Named;

import com.moveingroup.dto.UserAccountDto;
import com.moveingroup.rest.UserAccountRestTemplate;

@Named
public class UserAccountClient {

	private static String RESOURCE_URL = "/userAccount/";

	public UserAccountDto save(UserAccountDto userAccountDto) {
		UserAccountRestTemplate service = UserAccountRestTemplate.builder().build();
		return service.save(RESOURCE_URL, userAccountDto);
	}
}
