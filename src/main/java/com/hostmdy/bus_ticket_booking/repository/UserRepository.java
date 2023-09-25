package com.hostmdy.bus_ticket_booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.bus_ticket_booking.domain.User;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long>{
	
	Optional<User> findByUsername(String username);

}
