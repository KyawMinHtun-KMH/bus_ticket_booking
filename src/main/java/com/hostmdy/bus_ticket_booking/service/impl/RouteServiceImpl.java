package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.exception.RouteNotFoundException;
import com.hostmdy.bus_ticket_booking.repository.RouteRepository;
import com.hostmdy.bus_ticket_booking.service.RouteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
	
	private final RouteRepository routeRepository;

	@Override
	public Route save(Route route) {
		// TODO Auto-generated method stub
		return routeRepository.save(route);
	}

	@Override
	public List<Route> getAllRoute() {
		// TODO Auto-generated method stub
		return (List<Route>) routeRepository.findAll();
	}

	@Override
	public Optional<Route> getRouteById(Long routeId) {
		Optional<Route> routeOpt = routeRepository.findById(routeId);
		if(routeOpt.isEmpty()) {
			throw new RouteNotFoundException("Route with id = "+routeId+" is not found");
		}
		// TODO Auto-generated method stub
		return routeOpt;
	}

}
