package com.example.demo.service;

import org.springframework.validation.BindingResult;

public interface SecurityService {

	public String findLoggedInUsername();
	void autoLogin(String username, String password);
}
