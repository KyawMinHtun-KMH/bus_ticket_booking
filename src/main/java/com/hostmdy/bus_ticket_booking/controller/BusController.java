package com.hostmdy.bus_ticket_booking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.payload.BusRequest;
import com.hostmdy.bus_ticket_booking.service.BusService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bus")
@CrossOrigin(origins = "http://localhost:3000")
public class BusController{
	private final BusService busService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createBus(@RequestBody BusRequest busRequest) {
		Bus createdBus = busService.createBus(busRequest.getBus(), busRequest.getRoute(), busRequest.getTypeName(),busRequest.getPrice());
		
		return ResponseEntity.ok().body(createdBus);
	}
	
}