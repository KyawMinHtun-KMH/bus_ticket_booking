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
import com.hostmdy.bus_ticket_booking.domain.Ticket;
import com.hostmdy.bus_ticket_booking.exception.TicketNotFoundException;
import com.hostmdy.bus_ticket_booking.payload.RouteAndDepatureRequest;
import com.hostmdy.bus_ticket_booking.payload.TicketRequest;
import com.hostmdy.bus_ticket_booking.service.MapValidationErrorService;
import com.hostmdy.bus_ticket_booking.service.TicketService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {
	
	private final TicketService ticketService;
	private final MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createTicket(@Valid @RequestBody TicketRequest ticketRequest,BindingResult bindingResult){
		
		ResponseEntity<Map<String, String>> errorRespose = mapValidationErrorService.validate(bindingResult);
		if(errorRespose != null) {
			return errorRespose;
		}
		
		Ticket ticket = ticketService.createTicket(ticketRequest.getTicket(), ticketRequest.getTypeName(), ticketRequest.getRoute());
		
		return new ResponseEntity<Ticket>(ticket,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Ticket>> getAllTicket(){
		
		return ResponseEntity.ok().body(ticketService.getAllTicket());
	}
	
	@GetMapping("/{ticketId}")
	public ResponseEntity<Ticket> getTicket(@PathVariable Long ticketId){
		Optional<Ticket> ticketOpt = ticketService.getTicketById(ticketId);
		
		return ResponseEntity.ok().body(ticketOpt.get());
	}
	
	@GetMapping("/searchticket")
	public ResponseEntity<?> getAllTicketByRouteAndDepature(@RequestBody RouteAndDepatureRequest routeAndDepatureRequest){
		List<Ticket> tickets= ticketService.getAllTicketByRouteAndDepature(routeAndDepatureRequest.getStartLocation(), routeAndDepatureRequest.getEndLocation(), routeAndDepatureRequest.getDepature());
		if(tickets == null) {
			return new ResponseEntity<String>("This Route is not avaliable",HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return ResponseEntity.ok().body(tickets);
	}
	
	@DeleteMapping("/delete/{ticketId}")
	public ResponseEntity<?> deleteTicket(@PathVariable Long ticketId){
		Optional<Ticket> ticketOpt = ticketService.getTicketById(ticketId);
		if(ticketOpt.isEmpty()) {
			throw new TicketNotFoundException("Ticket with id = "+ticketId+" is not found");
		}
		Ticket ticket = ticketOpt.get();
		if(ticket.getOrders() != null) {
			return new ResponseEntity<String>("Ticket is not deleted order is existed",HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		ticketService.deletTicket(ticketId);
		return ResponseEntity.ok().body(ticketId.toString());
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateTicket(@Valid @RequestBody TicketRequest ticketRequest,BindingResult bindingResult){
		
		ResponseEntity<Map<String, String>> errorRespose = mapValidationErrorService.validate(bindingResult);
		if(errorRespose != null) {
			return errorRespose;
		}
		
		Long ticketId = ticketRequest.getTicket().getId();
		Optional<Ticket> tickeOpt = ticketService.getTicketById(ticketId);
		
		 if(tickeOpt.get().getOrders().isEmpty()) {
			 Ticket ticket = ticketService.updateTicket(ticketRequest.getTicket(), ticketRequest.getTypeName(), ticketRequest.getRoute());
				
				return ResponseEntity.ok().body(ticket);
		 }
		
		
		 return new ResponseEntity<String>("Ticket is not deleted order is existed",HttpStatus.SERVICE_UNAVAILABLE);
	}
}
