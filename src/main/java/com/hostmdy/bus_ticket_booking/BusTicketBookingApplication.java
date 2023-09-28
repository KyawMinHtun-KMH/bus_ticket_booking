package com.hostmdy.bus_ticket_booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hostmdy.bus_ticket_booking.domain.security.Role;
import com.hostmdy.bus_ticket_booking.repository.RoleRepository;

@SpringBootApplication
public class BusTicketBookingApplication implements CommandLineRunner{
	@Autowired
	public RoleRepository roleRepository;

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
		
	}

}
