package com.hostmdy.bus_ticket_booking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.bus_ticket_booking.domain.Order;
import com.hostmdy.bus_ticket_booking.domain.User;
import com.hostmdy.bus_ticket_booking.exception.UsernameNotFoundException;
import com.hostmdy.bus_ticket_booking.payload.OrderRequest;
import com.hostmdy.bus_ticket_booking.service.OrderService;
import com.hostmdy.bus_ticket_booking.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;
	private final UserService userService;
	
	@PostMapping("/create/{ticketId}/{userId}")
	public ResponseEntity<Order> createOrder(@PathVariable Long userId,@PathVariable Long ticketId,@RequestBody OrderRequest orderRequest){
		
		Order createdOrder = orderService.createOrder(orderRequest.getSeatNumber(), ticketId, orderRequest.getPassenger(), orderRequest.getPayment(), userId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
		
	}
	
	@GetMapping("/{ticketId}")
	public ResponseEntity<?> getOrdersByTicket(@PathVariable Long ticketId){
		
		List<Order> orders = orderService.getallOrdersByTicket(ticketId);
		
		return ResponseEntity.status(HttpStatus.OK).body(orders);
		
	}
	
	@GetMapping("/{userId}/get")
	public ResponseEntity<?> getOrdersByUser(@PathVariable Long userId){
		
		Optional<User> userOpt = userService.getUserById(userId);
		if (userOpt.isEmpty()) {
			throw new UsernameNotFoundException("user with id = "+userId+" is not found");
		}
		
		List<Order> orders = orderService.getallOrdersByUser(userOpt.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(orders);
		
	}

}
