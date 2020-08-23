package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	@RequestMapping("/")
	public String home(Model model)
	{
		return "redirect:/bidList/list";
		//return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/user/list";
	}
}
