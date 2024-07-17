package com.kims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kims.entites.Login;
import com.kims.entites.User;
import com.kims.services.UserService;
import com.kims.utilities.SessionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {
	@Autowired
	private UserService uService;
	@Autowired
	private SessionHandler sessionHandler;

	@GetMapping("/home")
	public String home() {
		
	return "home";
	}
	
	@GetMapping("/sign-up")
	public String signUp(Model model) {
		
	return "sign-up";
	}
	
	@PostMapping("/do-sign-up")
	public String doSignUp(@ModelAttribute("userSignup") User userSignup, Model model) {
		
			User u = uService.createNewUser(userSignup);
			if (u==null) {
				//We have to create an error page here
				return "/error";
			} 
			else {
				//We have to create a pop up which will appear before we move to home page after a successful registration;
				model.addAttribute("reg-msg", "You have registered successfully!");
				return "redirect:/home";
			}
	}
	
	
	@GetMapping("/forgot-password")
	public String forgotPassword(Model model) {
		return "forgot-password";
	}
	
	@PostMapping("/verify-email")
	public String verifyEmail(@RequestParam("email") String email, Model model, HttpSession session, HttpServletRequest request) {
		User u = uService.findByEmail(email);
		System.out.println(u);
		if (u==null) {
			//Need to show some error page at this place
			return "redirect:/forgot-password";
		} 
		else {
			model.addAttribute("verfiyEmail", true);
			session = request.getSession();
			session.setAttribute("update-user-password", u);
			return "forgot-password";
		}
	}
	
	@PostMapping("/set-password")
	public String setPassword(@RequestParam("password") String password, Model model, HttpSession session) {
		User u = (User) session.getAttribute("update-user-password");
		u.setPassword(password);
		uService.createNewUser(u);
		session.removeAttribute("update-user-password");
		model.addAttribute("verfiyEmail", false);
		return "redirect:/home";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginData") Login login, HttpSession session) {
			User user = uService.login(login.getEmail(), login.getPassword());
			if (user==null) {
				return "redirect:/home";
			}
			session.setAttribute("user", user);
			sessionHandler.sessionLoader(session);
			return "redirect:/dashboard";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user==null) {
			return "redirect:/home";
		}
		else {
			SessionHandler.resetSession(session);
			return "redirect:/home";
		}
	}
	
}
