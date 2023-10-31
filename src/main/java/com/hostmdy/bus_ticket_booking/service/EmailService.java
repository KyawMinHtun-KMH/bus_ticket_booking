package com.hostmdy.bus_ticket_booking.service;

import com.hostmdy.bus_ticket_booking.payload.AttachmentEmailRequest;

public interface EmailService {
	
	void sendEmail(Long orderId);
	
	void sendOrderConfirmEmail(Long orderId);
	
	void sendMailConfirmCode(String to,String code);
	
	void sendAttachmentEmail(AttachmentEmailRequest email);

}
