package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.bus_ticket_booking.domain.BusType;
import com.hostmdy.bus_ticket_booking.exception.BusTypeNotFoundException;
import com.hostmdy.bus_ticket_booking.repository.BusTypeRepository;
import com.hostmdy.bus_ticket_booking.service.BusTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusTypeServiceImpl implements BusTypeService {
	
	private final BusTypeRepository busTypeRepository;

	@Override
	public BusType save(BusType busType) {
		// TODO Auto-generated method stub
		return busTypeRepository.save(busType);
	}

	@Override
	public List<BusType> getAllBusType() {
		// TODO Auto-generated method stub
		return (List<BusType>) busTypeRepository.findAll();
	}

	@Override
	public Optional<BusType> getBusTypeById(Long busTypeId) {
		// TODO Auto-generated method stub
		Optional<BusType> busTypeOpt = busTypeRepository.findById(busTypeId);
		if(busTypeOpt.isEmpty()) {
			throw new BusTypeNotFoundException("BusType with id = "+busTypeId+" is not found");
		}
		return busTypeOpt;
	}

	

}
