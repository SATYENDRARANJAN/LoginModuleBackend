package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	UserValidator userValidator;

	@Autowired
	UserService userService;

	@Autowired
	SecurityService securityService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		System.out.println("in register get");
		model.addAttribute("userForm", new User());
		return "/registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		System.out.println("in register post");
		userValidator.validate(userForm, bindingResult);
		// not validated
		if (bindingResult.hasErrors()) {
			return "/registration";
		}
		// save in db
		System.out.println("satya 1");
		userService.save(userForm);
		System.out.println("satya 2");

		// login post save
		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm() );
		System.out.println("satya 3");


		return "redirect:/welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		
		
		if(error!=null || logout!=null) {
			if (error != null)
				model.addAttribute("error", "Your username and password is invalid");
				
			if (logout != null)
				model.addAttribute("message", "You have been logged out successfully");
		}else {
			model.addAttribute("userForm" , new User());
		}
		System.out.println("iN LOGIN GET");
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("userForm") User userForm,@RequestParam("username") String username, @RequestParam("password") String password) {
		
		System.out.println("iN LOGIN POST" +userForm.getUsername() +" "+userForm.getPassword());
		securityService.autoLogin(username,password );
		
		return "redirect:/welcome";
	}


	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "welcome";
	}
}
