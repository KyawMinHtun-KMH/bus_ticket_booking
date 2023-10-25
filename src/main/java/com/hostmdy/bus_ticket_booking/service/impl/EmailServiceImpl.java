package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.hostmdy.bus_ticket_booking.domain.Order;
import com.hostmdy.bus_ticket_booking.exception.OrderNotFoundException;
import com.hostmdy.bus_ticket_booking.payload.AttachmentEmailRequest;
import com.hostmdy.bus_ticket_booking.repository.OrderRepository;
import com.hostmdy.bus_ticket_booking.service.EmailService;
import com.hostmdy.bus_ticket_booking.utility.MailConstructor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
	
	private final JavaMailSender sender;
	private final MailConstructor mailConstructor;
	private final OrderRepository orderRepository;
	private final Environment env;

	@Override
	public void sendEmail(Long orderId) {
		// TODO Auto-generated method stub
       Optional<Order> orderOpt = orderRepository.findById(orderId);
		
		if(orderOpt.isEmpty()) {
			throw new OrderNotFoundException("order with id="+orderId+" is not found");
		}
		Order order = orderOpt.get();
		SimpleMailMessage mail = mailConstructor.constructSimpleMail(order.getPassenger().getEmail(),env.getProperty("order_reject_subject"),env.getProperty("order_reject_text"));
		sender.send(mail);
	}

	@Override
	public void sendOrderConfirmEmail(Long orderId) {
		// TODO Auto-generated method stub
		Optional<Order> orderOpt = orderRepository.findById(orderId);
		
		if(orderOpt.isEmpty()) {
			throw new OrderNotFoundException("order with id="+orderId+" is not found");
		}
		Order order = orderOpt.get();
		
		MimeMessagePreparator mail = mailConstructor.constructTemplateMail(order.getPassenger().getEmail(),env.getProperty("order_email_subject"),order);
		sender.send(mail);
	}

	@Override
	public void sendAttachmentEmail(AttachmentEmailRequest email) {
		// TODO Auto-generated method stub
		MimeMessagePreparator mail = mailConstructor.constructAttachmentMail(email.getTo(),email.getSubject(),email.getFilePath(),email.getText());
		sender.send(mail);
	}

}
