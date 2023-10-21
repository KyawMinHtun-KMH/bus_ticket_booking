package com.hostmdy.bus_ticket_booking.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.bus_ticket_booking.config.JwtTokenProvider;
import com.hostmdy.bus_ticket_booking.domain.User;
import com.hostmdy.bus_ticket_booking.domain.security.Role;
import com.hostmdy.bus_ticket_booking.domain.security.UserRole;
import com.hostmdy.bus_ticket_booking.exception.RoleNotFoundException;
import com.hostmdy.bus_ticket_booking.payload.JwtSuccessResponse;
import com.hostmdy.bus_ticket_booking.payload.LoginRequest;
import com.hostmdy.bus_ticket_booking.service.RoleService;
import com.hostmdy.bus_ticket_booking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private static final String TOKEN_PREFIX = "Bearer ";

	private final UserService userService;
	private final RoleService roleService;
	private final AuthenticationManager authManager;
	private final JwtTokenProvider tokenProvider;
	
	private JwtSuccessResponse authenticate(User user, String password) {

		try {
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), password));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwtToken = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

			List<String> roles = user.getUserRoles().stream().map((ur) -> ur.getRole().getName()).toList();

			return new JwtSuccessResponse(true, jwtToken, user, roles);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new JwtSuccessResponse();
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody LoginRequest loginRequest) {
		Optional<User> userOpt = userService.getUserByUsername(loginRequest.getUsername());

		if (userOpt.isEmpty()) {
			throw new UsernameNotFoundException("user with username="+loginRequest.getUsername()+" is not found");
		}

		return ResponseEntity.ok(authenticate(userOpt.get(), loginRequest.getPassword()));

	}


	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<?> signup(@RequestBody User user) {
		Optional<Role> roleOpt = roleService.getRoleByName("ROLE_USER");

		if (roleOpt.isEmpty()) {
			log.error("ROLE_USER is not found");
			throw new RoleNotFoundException("role with name = ROLE_USER is not found");
		}

		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, roleOpt.get()));
		String rawPassword = user.getPassword();
		User createdUser = userService.createUser(user, userRoles);
		
		return ResponseEntity.ok(authenticate(createdUser, rawPassword));

	}

}
