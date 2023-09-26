package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.domain.BusSeat;
import com.hostmdy.bus_ticket_booking.domain.BusType;
import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.domain.Seat;
import com.hostmdy.bus_ticket_booking.domain.Ticket;
import com.hostmdy.bus_ticket_booking.exception.BusNotFoundException;
import com.hostmdy.bus_ticket_booking.repository.BusRepository;
import com.hostmdy.bus_ticket_booking.repository.BusSeatRepository;
import com.hostmdy.bus_ticket_booking.repository.BusTypeRepository;
import com.hostmdy.bus_ticket_booking.repository.RouteRepository;
import com.hostmdy.bus_ticket_booking.repository.SeatRepository;
import com.hostmdy.bus_ticket_booking.service.BusService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {

	private final BusRepository busRepository;
	private final SeatRepository seatRepository;
	private final BusTypeRepository busTypeRepository;
	private final RouteRepository routeRepository;
	private final BusSeatRepository busSeatRepository;

	@Override
	public Bus save(Bus bus) {
		// TODO Auto-generated method stub
		return busRepository.save(bus);
	}

	@Override
	public Bus createBus(Bus bus, Route route, String typeName, Double price) {
		// TODO Auto-generated method stub
		BusType busType = busTypeRepository.findByTypeName(typeName);
		Integer capacity = busType.getCapacity();
		List<Seat> seats = (List<Seat>) seatRepository.findAll();
		Integer seatAmount = 1;
		for (final Seat seat : seats) {
			if (seatAmount <= capacity) {
				bus.getBusSeats().add(new BusSeat(bus, seat));
				seatAmount++;
			} else {
				break;
			}
		}
		seatAmount = 1;

		List<Route> routes = (List<Route>) routeRepository.findAll();
		for (final Route dbroute : routes) {
			if (dbroute.getStartLocation() == route.getStartLocation()
					&& dbroute.getEndLocation() == route.getEndLocation()) {
				dbroute.getBuses().add(bus);
				bus.setRoute(dbroute);
				routeRepository.save(dbroute);
				break;
			}

		}

		busType.getBuses().add(bus);
		bus.setBusType(busType);
		busTypeRepository.save(busType);

		Ticket ticket = new Ticket(price);
		bus.setTicket(ticket);
		ticket.setBus(bus);

		return save(bus);
	}

	@Override
	public List<Bus> getAllBus() {
		// TODO Auto-generated method stub
		return (List<Bus>) busRepository.findAll();
	}

	@Override
	public Optional<Bus> getBusById(Long busId) {
		// TODO Auto-generated method stub
		return busRepository.findById(busId);
	}

	@Override
	public List<Bus> getAllBusByRoute(Route route) {
		// TODO Auto-generated method stub
		return busRepository.findByRoute(route);
	}

	@Override
	public void deleteById(Long busId) {
		// TODO Auto-generated method stub
		Optional<Bus> busOpt = busRepository.findById(busId);
		Bus bus = busOpt.get();

		bus.setRoute(null);
		bus.setBusType(null);

		save(bus);
		busRepository.delete(bus);
	}
	
	@Transactional
	@Override
	public Bus updateBus(Bus bus, Route route, String typeName, Double price) {
		// TODO Auto-generated method stub
		
		Long busId = bus.getId();
		Optional<Bus> busOpt = busRepository.findById(busId);
		if(busOpt.isEmpty()) {
			throw new BusNotFoundException("Bus with id ="+busId+" is not found");
		}
		Bus updateBus = busOpt.get();
		System.out.println("###"+updateBus);
		
		BusType busType = busTypeRepository.findByTypeName(typeName);
		Integer capacity = busType.getCapacity();
		List<Seat> seats = (List<Seat>) seatRepository.findAll();
		Integer seatAmount = 1;
		
		if(updateBus.getBusType().getTypeName() != typeName) {
			Set<BusSeat> busSeats = updateBus.getBusSeats();
			
			for(final BusSeat busSeat : busSeats) {
				
				busSeat.setBus(null);
				busSeat.setSeat(null);
				
				busSeatRepository.save(busSeat);
				
				busSeatRepository.deleteById(busSeat.getId());
//				System.out.println("######"+busSeat.getId());
				//busSeatRepository.delete(busSeat);
				//busSeatRepository.save(busSeat);
				
			}
		for (final Seat seat : seats) {
			if (seatAmount <= capacity) {
				updateBus.getBusSeats().add(new BusSeat(bus, seat));
				seatAmount++;
			} else {
				break;
			}
		}
		}else {
			updateBus.setBusSeats(updateBus.getBusSeats());
		}
		
		seatAmount = 1;
		


		List<Route> routes = (List<Route>) routeRepository.findAll();
		for (final Route dbroute : routes) {
			if (dbroute.getStartLocation() == route.getStartLocation()
					&& dbroute.getEndLocation() == route.getEndLocation()) {
				dbroute.getBuses().add(bus);
				bus.setRoute(dbroute);
				routeRepository.save(dbroute);
				break;
			}

		}

		busType.getBuses().add(bus);
		bus.setBusType(busType);
		busTypeRepository.save(busType);

		
		Ticket updateTicket = updateBus.getTicket();
		
			updateTicket.setPrice(price);
			bus.setTicket(updateTicket);
		

		return save(bus);
	}

}
