package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hostmdy.bus_ticket_booking.domain.Order;
import com.hostmdy.bus_ticket_booking.domain.Passenger;
import com.hostmdy.bus_ticket_booking.domain.Payment;
import com.hostmdy.bus_ticket_booking.domain.Ticket;
import com.hostmdy.bus_ticket_booking.domain.TicketSeat;
import com.hostmdy.bus_ticket_booking.domain.User;
import com.hostmdy.bus_ticket_booking.exception.SeatNotFoundException;
import com.hostmdy.bus_ticket_booking.exception.TicketNotFoundException;
import com.hostmdy.bus_ticket_booking.exception.UsernameNotFoundException;
import com.hostmdy.bus_ticket_booking.repository.OrderRepository;
import com.hostmdy.bus_ticket_booking.repository.TicketRepository;
import com.hostmdy.bus_ticket_booking.repository.UserRepository;
import com.hostmdy.bus_ticket_booking.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final TicketRepository ticketRepository;
	private final UserRepository userRepository;

	@Override
	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}

	@Override
	@Transactional
	public Order createOrder(Set<String> seatNumber, Long ticketId, Passenger passenger, Payment payment, Long userId) {
		// TODO Auto-generated method stub
		Optional<User> userOpt = userRepository.findById(userId);
		if (userOpt.isEmpty()) {
			throw new UsernameNotFoundException("user with id = " + userId + " is not found");
		}

		Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
		if (ticketOpt.isEmpty()) {
			throw new TicketNotFoundException("Ticket with id = " + ticketId + " is not found");
		}
		Ticket ticket = ticketOpt.get();
		Set<TicketSeat> ticketSeats = ticket.getTicketSeats();

		for (final TicketSeat ticketSeat : ticketSeats) {
			for (final String seatNum : seatNumber) {
				if (ticket.getStatus()) {
					if (ticketSeat.getStatus().equals(false)) {
						throw new SeatNotFoundException("seat with " + seatNum + " can not order");
					}
					if (ticketSeat.getSeat().getSeatNumber().equals(seatNum) && ticketSeat.getStatus().equals(true)) {

						Order order = new Order();

						ticketSeat.setStatus(false);
						Integer seatAmount = seatNumber.size();
						order.setSeatNumber(seatNumber);
						order.setSeatAmount(seatAmount);
						order.setTotalPrice(seatAmount * ticket.getPrice());
						order.setPassenger(passenger);
						order.setPayment(payment);
						order.setUser(userOpt.get());
						order.setTicket(ticket);

						return saveOrder(order);
					}
				}
			}

		}
		return null;
	}

	@Override
	public Optional<Order> getOrderById(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id);
	}

	@Override
	public List<Order> getallOrdersByUser(User user) {
		// TODO Auto-generated method stub
		return orderRepository.findByUser(user);
	}

	@Override
	public void deleteOrderById(Long id) {
		// TODO Auto-generated method stub
		orderRepository.deleteById(id);
	}

	@Override
	public List<Order> getallOrdersByTicket(Long ticketId) {
		// TODO Auto-generated method stub
		Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
		if (ticketOpt.isEmpty()) {
			throw new TicketNotFoundException("Ticket with id = " + ticketId + " is not found");
		}

		return orderRepository.findByTicket(ticketOpt.get());
	}

}
