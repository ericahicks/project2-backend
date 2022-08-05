package com.skillstorm.hotel.service;



import java.awt.IllegalComponentStateException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.skillstorm.hotel.models.HotelUsers;
import com.skillstorm.hotel.repository.UsersRepository;



@Service
public class UsersService {

	/////////////////////////////////////////////////////////////////
	//////////////////////// Instance Variables /////////////////////
	/////////////////////////////////////////////////////////////////
	
	@Autowired
	private final UsersRepository usersRepository;
	

	/////////////////////////////////////////////////////////////////
	//////////////////////// Constructors ///////////////////////////
	/////////////////////////////////////////////////////////////////
	
	
	public UsersService(UsersRepository usersRepository) {
		//super();
		this.usersRepository = usersRepository;
	}


	/////////////////////////////////////////////////////////////////
	/////////////////////////// Methods /////////////////////////////
	/////////////////////////////////////////////////////////////////
	

	public List<HotelUsers> getUsers(int page, int limit) {
		return usersRepository.findAll(PageRequest.of(page, limit)).toList();
	}
	
	public List<HotelUsers> getAllUsers() {
		return usersRepository.findAll();
	}
	
	public HotelUsers getUsersById(int id) {
		Optional<HotelUsers> users = usersRepository.findById(id);
		return users.isPresent() ? users.get() : null;
	}
	
	@Transactional
	public HotelUsers addNewUser(HotelUsers hotelUser) {
		System.out.println("=================================");
		System.out.println("Hi, I'm the Service. Saving new user: " + hotelUser);
		Optional<HotelUsers> userOptional =  usersRepository.findUserByEmail(hotelUser.getEmail());
		if(userOptional.isPresent()) {
			throw new IllegalComponentStateException("This Email Has Already Been Taken"); // Runtime Exception will cause Rollback!
		}
		return usersRepository.save(hotelUser);
	}
	
	@Transactional
	public HotelUsers updateUser(HotelUsers hotelUser) {
		Optional<HotelUsers> userOptional =  usersRepository.findUserByEmail(hotelUser.getEmail());
//		System.out.println("==================================================================");
//		System.out.println("updateUser: user.id=" + userOptional.get().getId() + " hotelUser.id=" + hotelUser.getId());
		if(userOptional.isPresent() && userOptional.get().getId() != hotelUser.getId()) {
			throw new IllegalComponentStateException("This Email Has Already Been Taken");
		}
		return usersRepository.save(hotelUser);
		
	}
	
	public boolean existsById(int id) {
		return usersRepository.existsById(id);
	}

	public void deleteUser(int userid) {
		// Don't bother checking if it exists, since they want the user gone, if it's gone already that's "success"
//		boolean exists = usersRepository.existsById(userid);
//		if(!exists) {
//			throw new IllegalStateException("User with this id " + userid + " does not exist!");
//		}
		usersRepository.deleteById(userid);
		
	}


	
}
