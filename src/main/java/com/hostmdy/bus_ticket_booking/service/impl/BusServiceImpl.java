package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.exception.BusNotFoundException;
import com.hostmdy.bus_ticket_booking.repository.BusRepository;
import com.hostmdy.bus_ticket_booking.service.BusService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {
	
	private final BusRepository busRepository;

	@Override
	public Bus save(Bus bus) {
		// TODO Auto-generated method stub
		return busRepository.save(bus);
	}

	@Override
	public List<Bus> getAllBus() {
		// TODO Auto-generated method stub
		return (List<Bus>) busRepository.findAll();
	}

	@Override
	public Optional<Bus> getBusById(Long busId) {
		// TODO Auto-generated method stub
		Optional<Bus> busOpt = busRepository.findById(busId);
		if(busOpt.isEmpty()) {
			throw new BusNotFoundException("BusType with id = "+busId+" is not found");
		}
		return busOpt;
	}

	

}
