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

import com.hostmdy.bus_ticket_booking.domain.BusType;
import com.hostmdy.bus_ticket_booking.service.BusTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/busType")
@CrossOrigin(origins = "http://localhost:3000")
public class BusTypeController {
	
	private final BusTypeService busTypeService;
	
	@PostMapping("/create")
	public ResponseEntity<BusType> createBusType(@RequestBody BusType busType){
		return new ResponseEntity<BusType>(busTypeService.save(busType),HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BusType>> getAllBusType(){
		List<BusType> busTypes = busTypeService.getAllBusType();
		
		return ResponseEntity.ok().body(busTypes);
	}
	
	@GetMapping("/{busTypeId}")
	public ResponseEntity<BusType> getBusType(@PathVariable Long busTypeId){
		Optional<BusType> busTypeOpt = busTypeService.getBusTypeById(busTypeId);
		
		return ResponseEntity.ok().body(busTypeOpt.get());
	}
	
	
}
