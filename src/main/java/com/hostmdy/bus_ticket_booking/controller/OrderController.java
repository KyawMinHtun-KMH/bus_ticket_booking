package com.hostmdy.bus_ticket_booking.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.bus_ticket_booking.domain.Order;
import com.hostmdy.bus_ticket_booking.domain.User;
import com.hostmdy.bus_ticket_booking.exception.OrderNotFoundException;
import com.hostmdy.bus_ticket_booking.exception.UsernameNotFoundException;
import com.hostmdy.bus_ticket_booking.payload.OrderRequest;
import com.hostmdy.bus_ticket_booking.service.MapValidationErrorService;
import com.hostmdy.bus_ticket_booking.service.OrderService;
import com.hostmdy.bus_ticket_booking.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;
	private final UserService userService;
	private final MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/create/{ticketId}/{userId}")
	public ResponseEntity<?> createOrder(@PathVariable Long userId,@PathVariable Long ticketId,@Valid @RequestBody OrderRequest orderRequest,BindingResult result){
		
		ResponseEntity<Map<String, String>> errorResponse = mapValidationErrorService.validate(result);

		if (errorResponse != null) {
			return errorResponse;
		}
		
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
	
	@DeleteMapping("/{orderId}/delete")
	public ResponseEntity<String> deleteOrderById(@PathVariable Long orderId){
		orderService.deleteOrderById(orderId);
		return ResponseEntity.status(HttpStatus.OK).body(orderId.toString());
	}
	
	@PutMapping("/{orderId}/update")
	public ResponseEntity<?> updateOrderById(@PathVariable Long orderId){
		Optional<Order> orderOpt = orderService.getOrderById(orderId);
		if(orderOpt.isEmpty()) {
			throw new OrderNotFoundException("order with id="+orderId+" is not found");
		}
		Order order = orderOpt.get();
		order.setStatus(true);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(order));
	}

}
