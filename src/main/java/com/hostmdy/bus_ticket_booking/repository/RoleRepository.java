package com.hostmdy.bus_ticket_booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.bus_ticket_booking.domain.security.Role;
import java.util.Optional;


public interface RoleRepository extends CrudRepository<Role, Long>{

	Optional<Role> findByName(String name);
	
}
