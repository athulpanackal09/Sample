package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entities.Users;
import com.demo.services.UsersService;

@Controller
public class UsersController
{
	@Autowired
	UsersService userv;

	@PostMapping("/register")
	public String addUser(
			@ModelAttribute Users user) 
	{

		boolean userstatus = userv.emailExists(user.getEmail());
		if(userstatus == false)
		{
			userv.addUser(user);
			System.out.println("User is added");
			return "registersuccess";
		}

		else
		{
			System.out.println("User is already exist");
			return "registerfail";
		}



	}

	@PostMapping("/login")
	public String validateUser(@RequestParam String email, @RequestParam String password)
	{

		if(userv.validateUser(email, password) == true)
		{

			if(userv.getRole(email) .equals("admin"))
			{
				return "adminhome";
			}
			else
			{
				return "customerhome";
			}
		}

		else
		{
			return "loginfail";
		}
	}
	
	
	@GetMapping("/exploresongs")
	public String exploresongs(String email)
	{
		Users user=userv.getUser(email);
		
		boolean userStatus=user.isPremium();
		if(userStatus==true)
		{
			return "viewsong";
		}
		else
		{
			return "payment";
		}
	}


}



