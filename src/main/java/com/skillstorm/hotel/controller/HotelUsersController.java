package com.skillstorm.hotel.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.hotel.models.hotelUsers;
import com.skillstorm.hotel.service.UsersService;


// Controller for Users of the Hotel App

@RestController
@RequestMapping(path = "/Hotel")
public class HotelUsersController {
	
	@Autowired
	private final UsersService usersService;
	
	
	public HotelUsersController(UsersService usersService) {
		//super();
		this.usersService = usersService;
	}
	// This method gets all the Users from the Db
	@GetMapping
	public List<hotelUsers> getUsers() {
		return usersService.getUsers();
		
	}
	
	// This Method will allow users to Create a new Account
	@PostMapping
	public void createNewUser(@RequestBody hotelUsers hotelUser) {
		usersService.addNewUser(hotelUser);
	}

	// This Deletes a User
	@DeleteMapping(path= "{userid}")
	public void deleteUser (@PathVariable("userid")int userid) {
		usersService.deleteUser(userid);
	}
}
