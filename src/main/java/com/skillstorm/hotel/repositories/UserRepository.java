package com.skillstorm.hotel.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.hotel.models.Reservation;
import com.skillstorm.hotel.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.reservations")
	public Iterable<User> findAll();

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.reservations WHERE u.id = ?1")
	public Optional<User> findById(Integer id);
	
	@Query("SELECT r FROM Reservation r WHERE r.user.id = ?1")
	public Iterable<Reservation> findReservationsByUserId(int id);
	
	/////////////////// Inherited Methods /////////////////////////////////

	public <S extends User> Iterable<S> saveAll(Iterable<S> entities);

	public boolean existsById(Integer id);

	public Iterable<User> findAllById(Iterable<Integer> ids);

	public long count();
	
	public void deleteById(Integer id);
	
	public void delete(User entity);

	public void deleteAllById(Iterable<? extends Integer> ids);
	
	public void deleteAll(Iterable<? extends User> entities);
	
	public void deleteAll();
}
