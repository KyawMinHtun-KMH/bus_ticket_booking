package com.hostmdy.bus_ticket_booking.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.hostmdy.bus_ticket_booking.domain.User;
import com.hostmdy.bus_ticket_booking.domain.security.UserRole;

public interface UserService {
	
    User save(User user);
    
    User createUser(User user,Set<UserRole> userRoles);
	
	List<User> getAllUsers();
	
	Optional<User> getUserByUsername(String username);
	
	Optional<User> getUserById(Long id);

}
