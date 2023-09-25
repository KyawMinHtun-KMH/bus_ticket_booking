package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.bus_ticket_booking.domain.security.Role;
import com.hostmdy.bus_ticket_booking.repository.RoleRepository;
import com.hostmdy.bus_ticket_booking.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
	
	private final RoleRepository roleRepository;

	@Override
	public Optional<Role> getRoleByName(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(name);
	}

}
