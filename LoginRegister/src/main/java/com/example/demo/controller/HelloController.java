package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@RequestMapping("/")
	public String hello(Model model, @RequestParam (value="name" , required = false ,defaultValue ="World")String name) {
		 model.addAttribute("name", name);
		 return "/index";
	}
	
	
	/*@RequestMapping maps /hello request to hello() method.

	name is a query string parameter of /hello request.

	Model object passes value to hello view (hello.jsp).*/
	
}
