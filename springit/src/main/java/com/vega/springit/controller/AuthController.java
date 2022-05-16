package com.vega.springit.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vega.springit.domain.User;
import com.vega.springit.repository.UserRepository;
import com.vega.springit.service.UserService;

@Controller
public class AuthController {
	
	private UserService userService;
	
	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}
	
	@GetMapping("/profile")
	public String profile() {
	    return "auth/profile";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
	    return "auth/register";
	}
	
	@PostMapping("/register")
	public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("validationErrors", bindingResult.getAllErrors());
			return "auth/register";
		}
		else {
			User newUser = userService.register(user);
			redirectAttributes.addAttribute("id", newUser.getId())
			.addFlashAttribute("success", true);
			
			return "redirect:/register";
		}
	}
	
	@GetMapping("/activate/{email}/{activationCode}")
	public String activate(@PathVariable String email, @PathVariable String activationCode, Model model) {
		Optional<User> userOptional = userService.findByEmailAndActivationCode(email, activationCode);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			user.setEnabled(true);
			user.setConfirmPassword(user.getPassword());
			userService.save(user);
			model.addAttribute("user", user);
			userService.sendWelcomeEmail(user);
	        return "auth/activated";
		}
		return "redirect:/";
	}
}
