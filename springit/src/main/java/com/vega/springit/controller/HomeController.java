package com.vega.springit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vega.springit.domain.Link;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model, HttpServletRequest httpServletRequest) {
		model.addAttribute("message", "HelloWorld");
		return "index";
	}
}
