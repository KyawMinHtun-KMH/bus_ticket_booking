package com.hostmdy.bus_ticket_booking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.exception.BusNotFoundException;
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
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllBus(){
		
		List<Bus> buses = busService.getAllBus();
		if(buses.isEmpty()) {
			return new ResponseEntity<String>("buses is empty",HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok().body(buses);
	}
	
	@GetMapping("{busId}")
	public ResponseEntity<?> getBusById(@PathVariable Long busId){
		Optional<Bus> busOpt = busService.getBusById(busId);
		if(busOpt.isEmpty()) {
			throw new BusNotFoundException("Bus with id = "+busId+" is not found");
		}
		return ResponseEntity.ok().body(busOpt.get());
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateBus(@RequestBody BusRequest busRequest){
		Bus bus = busRequest.getBus();
		if(bus.getId() == null) {
			throw new BusNotFoundException("Bus with id = "+bus.getId()+" is not found");
		}
		
		Bus updatedBus = busService.updateBus(busRequest.getBus(), busRequest.getRoute(), busRequest.getTypeName(),busRequest.getPrice());
		
		return ResponseEntity.ok().body(updatedBus);
		
	}
	
	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<?> deletBus(@PathVariable Long busId){
		Optional<Bus> busOpt = busService.getBusById(busId);
		if(busOpt.isEmpty()) {
			throw new BusNotFoundException("Bus with id = "+busId+" is not found");
		}
		busService.deleteById(busId);
		
		return ResponseEntity.ok().body("Successfully deleted");
	}
	
}