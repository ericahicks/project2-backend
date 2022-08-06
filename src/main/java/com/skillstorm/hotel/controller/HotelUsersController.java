package com.skillstorm.hotel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	// This method gets the Users requested page of users from the Db
	@GetMapping
	public List<HotelUsers> getUsers(@RequestParam(defaultValue = "1") int page, 
			@RequestParam(defaultValue = "3") int limit) {
		return usersService.getUsers(--page, limit);
	}
	
	// This method gets all the Users from the Db
	@GetMapping("/all")
	public List<HotelUsers> getAllUsers() {
		return usersService.getAllUsers();
	}
	
	// This method finds a user by id
	@GetMapping("/{id}")
	public HotelUsers getUsersById(@PathVariable int id) {
		return usersService.getUsersById(id);
	}
	
	// This method finds a user's id by email
	@PostMapping("/email") // Using POST for a GET bc email in url is weird
	public ResponseEntity<Integer> getUsersByEmail(@RequestBody String email) {
		HotelUsers user = usersService.getUsersByEmail(email);
		Integer id = user == null ? 0 : user.getId();
		return new ResponseEntity<>(id, id == 0 ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}

	// This Method will allow users to Create a new Account
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // TODO add @Valid 
	public HotelUsers createNewUser(@RequestBody HotelUsers hotelUser) {
		System.out.println("=================================");
		System.out.println("Hi, I'm the controller. Saving new user: " + hotelUser);
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
