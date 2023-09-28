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

import com.hostmdy.bus_ticket_booking.domain.Ticket;
import com.hostmdy.bus_ticket_booking.payload.TicketRequest;
import com.hostmdy.bus_ticket_booking.service.TicketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {
	
	private final TicketService ticketService;
	
	@PostMapping("/create")
	public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest ticketRequest){
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
}
