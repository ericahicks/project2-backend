package com.skillstorm.hotel.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.skillstorm.hotel.models.Reservation;
import com.skillstorm.hotel.models.User;
import com.skillstorm.hotel.repositories.UserRepository;

@Service
@Primary
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;

	@Override
	public Set<User> findAll() {
		Iterable<User> usersItr = repository.findAll();
		Set<User> users = new HashSet<>();
		for (User user : usersItr) {
			users.add(user);
		}
		return users;
	}

	@Override
	public User findById(int id) {
		Optional<User> user = repository.findById(id);
		return user.isEmpty() ? null : user.get() ;
	}

	@Override
	public Set<Reservation> findReservationsByUserId(int id) {
		Iterable<Reservation> revsItr = repository.findReservationsByUserId(id);
		Set<Reservation> revs = new HashSet<>();
		for (Reservation res : revsItr) {
			revs.add(res);
		}
		return revs;
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public User update(User user) {
		return repository.save(user);
	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);
	}

}
