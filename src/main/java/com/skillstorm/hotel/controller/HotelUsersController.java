package com.skillstorm.hotel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.hotel.models.HotelUsers;
import com.skillstorm.hotel.service.UsersService;

// Controller for Users of the Hotel App

@RestController
@RequestMapping(path = "/users")
@CrossOrigin("*")
public class HotelUsersController {

	/////////////////////////////////////////////////////////////////
	//////////////////// Instance Variables /////////////////////////
	/////////////////////////////////////////////////////////////////

	@Autowired
	private final UsersService usersService;

	/////////////////////////////////////////////////////////////////
	//////////////////////// Constructors ///////////////////////////
	/////////////////////////////////////////////////////////////////

	public HotelUsersController(UsersService usersService) {
		// super();
		this.usersService = usersService;
	}

	/////////////////////////////////////////////////////////////////
	///////////////////////////// Methods ///////////////////////////
	/////////////////////////////////////////////////////////////////

	// This method gets all the Users from the Db
	@GetMapping
	public List<HotelUsers> getUsers() {
		return usersService.getUsers();
	}
	
	@GetMapping("/{id}")
	public HotelUsers getUsersById(@PathVariable int id) {
		return usersService.getUsersById(id);
	}

	// This Method will allow users to Create a new Account
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HotelUsers createNewUser(@Valid @RequestBody HotelUsers hotelUser) {
		return usersService.addNewUser(hotelUser);
	}

	@PutMapping("/{id}")
	public HotelUsers updateUser(@Valid @RequestBody HotelUsers hotelUser, @PathVariable int id) {
		hotelUser.setId(id);
		return usersService.updateUser(hotelUser);
	}

	// This Deletes a User
	@DeleteMapping(path = "{userid}")
	public void deleteUser(@PathVariable("userid") int userid) {
		usersService.deleteUser(userid);
	}
}
