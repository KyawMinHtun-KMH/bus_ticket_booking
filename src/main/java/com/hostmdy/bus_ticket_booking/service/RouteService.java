package com.hostmdy.bus_ticket_booking.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.bus_ticket_booking.domain.Route;

public interface RouteService {
	Route save(Route route);
	
	Optional<Route> getRouteById(Long routeId);
	
	List<Route> getAllRoute();
	
}
