package com.epam.jmp.springadvancepet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController
{
	@GetMapping("/")
	public String greeting(Model model, @RequestParam(name = "say", required = false, defaultValue = "Hello world") String say)
	{
		model.addAttribute("greetingMsg", say);
		return "greeting";
	}
}
