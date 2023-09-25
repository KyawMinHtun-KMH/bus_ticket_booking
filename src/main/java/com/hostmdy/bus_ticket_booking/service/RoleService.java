package com.hostmdy.bus_ticket_booking.service;

import java.util.Optional;

import com.hostmdy.bus_ticket_booking.domain.security.Role;

public interface RoleService {
	
	Optional<Role> getRoleByName(String name);

}
