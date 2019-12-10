package com.epam.jmp.springadvancepet01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GreetingController
{
	@GetMapping("/")
	public String greeting(Model model, @RequestParam(name = "say", required = false, defaultValue = "Welcome to PetClinic") String say)
	{
		log.info("Add greetingMsg to model");
		model.addAttribute("greetingMsg", say);
		return "greeting";
	}
}
