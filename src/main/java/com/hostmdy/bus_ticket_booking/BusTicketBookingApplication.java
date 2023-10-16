package com.hostmdy.bus_ticket_booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.domain.City;
import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.domain.Seat;
import com.hostmdy.bus_ticket_booking.domain.security.Role;
import com.hostmdy.bus_ticket_booking.repository.BusRepository;
import com.hostmdy.bus_ticket_booking.repository.RoleRepository;
import com.hostmdy.bus_ticket_booking.repository.RouteRepository;
import com.hostmdy.bus_ticket_booking.repository.SeatRepository;

@SpringBootApplication
public class BusTicketBookingApplication implements CommandLineRunner{
	@Autowired
	public RoleRepository roleRepository;
	
	@Autowired
	public SeatRepository seatRepository;
	
	@Autowired
	public RouteRepository routeRepository;
	
	@Autowired
	public BusRepository busRepository;

	public static void main(String[] args) {
		SpringApplication.run(BusTicketBookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Role role1 = new Role();
		role1.setName("ROLE_ADMIN");
		
		Role role2 = new Role();
		role2.setName("ROLE_USER");
		
		roleRepository.save(role1);
		roleRepository.save(role2);
		
		Seat seat1 = new Seat();
		seat1.setSeatNumber("1");
		
		Seat seat2 = new Seat();
		seat2.setSeatNumber("2");
		
		Seat seat3 = new Seat();
		seat3.setSeatNumber("3");
		
		Seat seat4 = new Seat();
		seat4.setSeatNumber("4");
		
		Seat seat5 = new Seat();
		seat5.setSeatNumber("5");
		
		Seat seat6 = new Seat();
		seat6.setSeatNumber("6");
		
		Seat seat7 = new Seat();
		seat7.setSeatNumber("7");
		
		Seat seat8 = new Seat();
		seat8.setSeatNumber("8");
		
		Seat seat9 = new Seat();
		seat9.setSeatNumber("9");
		
		Seat seat10 = new Seat();
		seat10.setSeatNumber("10");
		
		seatRepository.save(seat1);
		seatRepository.save(seat2);
		seatRepository.save(seat3);
		seatRepository.save(seat4);
		seatRepository.save(seat5);
		seatRepository.save(seat6);
		seatRepository.save(seat7);
		seatRepository.save(seat8);
		seatRepository.save(seat9);
		seatRepository.save(seat10);
		
		Route route1 = new Route();
		route1.setStartLocation(City.MANDALAY);
		route1.setEndLocation(City.YANGON);
		
		Route route2 = new Route();
		route2.setStartLocation(City.YANGON);
		route2.setEndLocation(City.BAGO);
		
		Route route3 = new Route();
		route3.setStartLocation(City.YANGON);
		route3.setEndLocation(City.MANDALAY);
		
		Route route4 = new Route();
		route4.setStartLocation(City.YANGON);
		route4.setEndLocation(City.PYI_OO_LWIN);
		
		routeRepository.save(route1);
		routeRepository.save(route2);
		routeRepository.save(route3);
		routeRepository.save(route4);
		
		Bus type1 = new Bus();
		type1.setCapacity(7);
		type1.setTypeName("Scania Standard");
		
		Bus type2 = new Bus();
		type2.setCapacity(5);
		type2.setTypeName("Scania VIP");
		
		busRepository.save(type1);
		busRepository.save(type2);
		
	}

}
