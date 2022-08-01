package com.skillstorm.hotel.service;



import java.awt.IllegalComponentStateException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.hotel.models.hotelUsers;
import com.skillstorm.hotel.repository.UsersRepository;



@Service
public class UsersService {
	
	@Autowired
	private final UsersRepository usersRepository;
	
	
	public UsersService(UsersRepository usersRepository) {
		//super();
		this.usersRepository = usersRepository;
	}


	public List<hotelUsers> getUsers() {
		return usersRepository.findAll();
	}


	public void addNewUser(hotelUsers hotelUser) {
		Optional<hotelUsers> userOptional =  usersRepository.findUserByEmail(hotelUser.getEmail());
		if(userOptional.isPresent()) {
			throw new IllegalComponentStateException("This Email Has Already Been Taken");
		}
		usersRepository.save(hotelUser);
		
	}


	public void deleteUser(int userid) {
		boolean exists = usersRepository.existsById(userid);
		if(!exists) {
			throw new IllegalStateException("User with this id " + userid + " does not exist!");
		}
		usersRepository.deleteById(userid);
		
	}

	
}
