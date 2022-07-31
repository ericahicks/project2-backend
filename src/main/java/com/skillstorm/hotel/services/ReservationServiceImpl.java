package com.skillstorm.hotel.services;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.skillstorm.hotel.models.Reservation;
import com.skillstorm.hotel.repositories.ReservationRepository;

@Service
@Primary
@Transactional
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationRepository repository;

	@Override
	public Set<Reservation> findAll() {
		Set<Reservation> revs = new HashSet<Reservation>(repository.findAll());
		return revs;
	}

	@Override
	public Set<Reservation> findAllAfter(Date date) {
		Set<Reservation> revs = new HashSet<Reservation>(repository.findAllAfter(date));
		return revs;
	}

	@Override
	public Reservation findById(int id) {
		Optional<Reservation> rev = repository.findById(id);
		return rev.isEmpty() ? null : rev.get();
	}

	@Override
	public Reservation save(Reservation reservation) {
		Reservation rev = repository.save(reservation);
		return rev;
	}

	@Override
	public Reservation update(Reservation reservation) {
		Reservation rev = repository.save(reservation);
		return rev;
	}

	@Override
	public void deleteById(int id) {
		repository.deleteById(id);
		
	}

}
