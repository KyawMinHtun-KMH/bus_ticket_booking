package com.hostmdy.bus_ticket_booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.hostmdy.bus_ticket_booking.domain.City;
import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.domain.Seat;
import com.hostmdy.bus_ticket_booking.domain.security.Role;
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
	

	public static void main(String[] args) {
		SpringApplication.run(BusTicketBookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		if(!((List<Role>)(roleRepository.findAll())).isEmpty()) {
			return;
		}
		
		if(!((List<Seat>)(seatRepository.findAll())).isEmpty()) {
			return;
		}
		
		if(!((List<Route>)(routeRepository.findAll())).isEmpty()) {
			return;
		}
		
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
		
		Seat seat11 = new Seat();
		seat11.setSeatNumber("11");
		
		Seat seat12 = new Seat();
		seat12.setSeatNumber("12");
		
		Seat seat13 = new Seat();
		seat13.setSeatNumber("13");
		
		Seat seat14 = new Seat();
		seat14.setSeatNumber("14");
		
		Seat seat15 = new Seat();
		seat15.setSeatNumber("15");
		
		Seat seat16 = new Seat();
		seat16.setSeatNumber("16");
		
		Seat seat17 = new Seat();
		seat17.setSeatNumber("17");
		
		Seat seat18 = new Seat();
		seat18.setSeatNumber("18");
		
		Seat seat19 = new Seat();
		seat19.setSeatNumber("19");
		
		Seat seat20 = new Seat();
		seat20.setSeatNumber("20");
		
		Seat seat21 = new Seat();
		seat21.setSeatNumber("21");
		
		Seat seat22 = new Seat();
		seat22.setSeatNumber("22");
		
		Seat seat23 = new Seat();
		seat23.setSeatNumber("23");
		
		Seat seat24 = new Seat();
		seat24.setSeatNumber("24");
		
		Seat seat25 = new Seat();
		seat25.setSeatNumber("25");
		
		Seat seat26 = new Seat();
		seat26.setSeatNumber("26");
		
		Seat seat27 = new Seat();
		seat27.setSeatNumber("27");
		
		Seat seat28 = new Seat();
		seat28.setSeatNumber("28");
		
		Seat seat29 = new Seat();
		seat29.setSeatNumber("29");
		
		Seat seat30 = new Seat();
		seat30.setSeatNumber("30");
		
		Seat seat31 = new Seat();
		seat31.setSeatNumber("31");
		
		Seat seat32 = new Seat();
		seat32.setSeatNumber("32");
		
		Seat seat33 = new Seat();
		seat33.setSeatNumber("33");
		
		Seat seat34 = new Seat();
		seat34.setSeatNumber("34");
		
		Seat seat35 = new Seat();
		seat35.setSeatNumber("35");
		
		Seat seat36 = new Seat();
		seat36.setSeatNumber("36");
		
		Seat seat37 = new Seat();
		seat37.setSeatNumber("37");
		
		Seat seat38 = new Seat();
		seat38.setSeatNumber("38");
		
		Seat seat39 = new Seat();
		seat39.setSeatNumber("39");
		
		Seat seat40 = new Seat();
		seat40.setSeatNumber("40");
		
		Seat seat41 = new Seat();
		seat41.setSeatNumber("41");
		
		Seat seat42 = new Seat();
		seat42.setSeatNumber("42");
		
		Seat seat43 = new Seat();
		seat43.setSeatNumber("43");
		
		Seat seat44 = new Seat();
		seat44.setSeatNumber("44");
		
		Seat seat45 = new Seat();
		seat45.setSeatNumber("45");
	
		Seat seat46 = new Seat();
		seat46.setSeatNumber("46");
		
		Seat seat47 = new Seat();
		seat47.setSeatNumber("47");
		
		Seat seat48 = new Seat();
		seat48.setSeatNumber("48");
		
		Seat seat49 = new Seat();
		seat49.setSeatNumber("49");
		
		Seat seat50 = new Seat();
		seat50.setSeatNumber("50");
		
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
		seatRepository.save(seat11);
		seatRepository.save(seat12);
		seatRepository.save(seat13);
		seatRepository.save(seat14);
		seatRepository.save(seat15);
		seatRepository.save(seat16);
		seatRepository.save(seat17);
		seatRepository.save(seat18);
		seatRepository.save(seat19);
		seatRepository.save(seat20);
		seatRepository.save(seat21);
		seatRepository.save(seat22);
		seatRepository.save(seat23);
		seatRepository.save(seat24);
		seatRepository.save(seat25);
		seatRepository.save(seat26);
		seatRepository.save(seat27);
		seatRepository.save(seat28);
		seatRepository.save(seat29);
		seatRepository.save(seat30);
		seatRepository.save(seat31);
		seatRepository.save(seat32);
		seatRepository.save(seat33);
		seatRepository.save(seat34);
		seatRepository.save(seat35);
		seatRepository.save(seat36);
		seatRepository.save(seat37);
		seatRepository.save(seat38);
		seatRepository.save(seat39);
		seatRepository.save(seat40);
		seatRepository.save(seat41);
		seatRepository.save(seat42);
		seatRepository.save(seat43);
		seatRepository.save(seat44);
		seatRepository.save(seat45);
		seatRepository.save(seat46);
		seatRepository.save(seat47);
		seatRepository.save(seat48);
		seatRepository.save(seat49);
		seatRepository.save(seat50);
		
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
		route4.setEndLocation(City.PYIN_OO_LWIN);
		
		routeRepository.save(route1);
		routeRepository.save(route2);
		routeRepository.save(route3);
		routeRepository.save(route4);
		
		
	}

}
