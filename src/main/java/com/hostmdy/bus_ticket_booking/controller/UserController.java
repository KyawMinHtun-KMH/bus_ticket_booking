package com.hostmdy.bus_ticket_booking.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.bus_ticket_booking.domain.User;
import com.hostmdy.bus_ticket_booking.domain.security.Role;
import com.hostmdy.bus_ticket_booking.domain.security.UserRole;
import com.hostmdy.bus_ticket_booking.exception.RoleNotFoundException;
import com.hostmdy.bus_ticket_booking.payload.JwtSuccessResponse;
import com.hostmdy.bus_ticket_booking.payload.LoginRequest;
import com.hostmdy.bus_ticket_booking.service.MapValidationErrorService;
import com.hostmdy.bus_ticket_booking.service.RoleService;
import com.hostmdy.bus_ticket_booking.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final RoleService roleService;
	private final MapValidationErrorService mapValidationErrorService;

	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<?> signup(@Valid @RequestBody User user, BindingResult result) {
		ResponseEntity<Map<String, String>> errorResponse = mapValidationErrorService.validate(result);

		if (errorResponse != null) {
			return errorResponse;
		}

		Optional<Role> roleOpt = roleService.getRoleByName("ROLE_USER");

		if (roleOpt.isEmpty()) {
			throw new RoleNotFoundException("role with name = ROLE_USER is not found");
		}

		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, roleOpt.get()));
		User createdUser = userService.createUser(user, userRoles);
		List<String> roles = user.getUserRoles().stream().map((ur)->ur.getRole().getName()).toList();
		JwtSuccessResponse jwtSuccessResponse = new JwtSuccessResponse(true, createdUser, roles);

		return ResponseEntity.ok(jwtSuccessResponse);

	}

	@PostMapping("/signin")
	public ResponseEntity<?> signin( @Valid @RequestBody LoginRequest loginRequest,BindingResult result) {
		ResponseEntity<Map<String, String>> errorResponse = mapValidationErrorService.validate(result);

		if (errorResponse != null) {
			return errorResponse;
		}
		
		Optional<User> userOpt = userService.getUserByUsername(loginRequest.getUsername());

		if (userOpt.isEmpty()) {
			throw new RoleNotFoundException("user with username=" + loginRequest.getUsername() + " is not found");
		}
		User user = userOpt.get();
		
		List<String> roles = user.getUserRoles().stream().map((ur)->ur.getRole().getName()).toList();
		JwtSuccessResponse jwtSuccessResponse = new JwtSuccessResponse(true, user, roles);

		return ResponseEntity.ok(jwtSuccessResponse);

	}

}
