package com.hostmdy.bus_ticket_booking.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.service.BusService;
import com.hostmdy.bus_ticket_booking.service.MapValidationErrorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/busType")
@CrossOrigin(origins = "http://localhost:3000")
public class BusController {
	
	private final BusService busService;
	private final MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createBusType(@Valid @RequestBody Bus busType,BindingResult result){
		
		ResponseEntity<Map<String,String>> errorResponse = mapValidationErrorService.validate(result);
		if(errorResponse != null) {
			return errorResponse;
		}
		
		return new ResponseEntity<Bus>(busService.save(busType),HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Bus>> getAllBusType(){
		List<Bus> busTypes = busService.getAllBusType();
		
		return ResponseEntity.ok().body(busTypes);
	}
	
	@GetMapping("/{busTypeId}")
	public ResponseEntity<Bus> getBusType(@PathVariable Long busTypeId){
		Optional<Bus> busTypeOpt = busService.getBusTypeById(busTypeId);
		
		return ResponseEntity.ok().body(busTypeOpt.get());
	}
	
	
}
