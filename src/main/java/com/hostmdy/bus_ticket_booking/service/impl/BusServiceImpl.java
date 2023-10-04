package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.exception.BusTypeNotFoundException;
import com.hostmdy.bus_ticket_booking.repository.BusRepository;
import com.hostmdy.bus_ticket_booking.service.BusService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {
	
	private final BusRepository busRepository;

	@Override
	public Bus save(Bus busType) {
		// TODO Auto-generated method stub
		return busRepository.save(busType);
	}

	@Override
	public List<Bus> getAllBusType() {
		// TODO Auto-generated method stub
		return (List<Bus>) busRepository.findAll();
	}

	@Override
	public Optional<Bus> getBusTypeById(Long busTypeId) {
		// TODO Auto-generated method stub
		Optional<Bus> busTypeOpt = busRepository.findById(busTypeId);
		if(busTypeOpt.isEmpty()) {
			throw new BusTypeNotFoundException("BusType with id = "+busTypeId+" is not found");
		}
		return busTypeOpt;
	}

	

}
