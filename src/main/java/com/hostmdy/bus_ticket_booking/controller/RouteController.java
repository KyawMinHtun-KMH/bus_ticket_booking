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

import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.service.MapValidationErrorService;
import com.hostmdy.bus_ticket_booking.service.RouteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/route")
@CrossOrigin(origins = "http://localhost:3000")
public class RouteController {
	
	private final RouteService routeService;
	private final MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createRoute(@Valid @RequestBody Route route,BindingResult result){
		
		ResponseEntity<Map<String, String>> errorResponse = mapValidationErrorService.validate(result);
		if(errorResponse != null) {
			return errorResponse;
		}
		
		return new ResponseEntity<Route>(routeService.save(route),HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Route>> getAllRoute(){
		
		return ResponseEntity.ok().body(routeService.getAllRoute());
	}
	
	@GetMapping("/{routeId}")
	public ResponseEntity<Route> getRoute(@PathVariable Long routeId){
		Optional<Route> routeOpt = routeService.getRouteById(routeId);
		return ResponseEntity.ok().body(routeOpt.get());
	}
	/*
	@DeleteMapping("/delete/{routeId}")
	public ResponseEntity<String> deleteRoute(@PathVariable Long routeId){
		Optional<Route> routeOpt = routeService.getRouteById(routeId);
		if(routeOpt.isEmpty()) {
			throw new RouteNotFoundException("Route with id = "+routeId+" is not found");
		}
		routeService.deleteRoute(routeId);
		
		return ResponseEntity.ok().body(routeId.toString());
	}*/
	
}
