package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hostmdy.bus_ticket_booking.domain.User;
import com.hostmdy.bus_ticket_booking.domain.security.UserRole;
import com.hostmdy.bus_ticket_booking.exception.UsernameAlreadyExistsException;
import com.hostmdy.bus_ticket_booking.repository.RoleRepository;
import com.hostmdy.bus_ticket_booking.repository.UserRepository;
import com.hostmdy.bus_ticket_booking.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public Optional<User> getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) {
		// TODO Auto-generated method stub
		Optional<User> existedUserOpt = getUserByUsername(user.getUsername());
		
		if(existedUserOpt.isPresent()) {
			throw new UsernameAlreadyExistsException("User with email="+user.getUsername()+" already exists");
		}
		
		userRoles.forEach((ur) -> roleRepository.save(ur.getRole()));
		user.setUserRoles(userRoles);
		
		return save(user);
	}
}
