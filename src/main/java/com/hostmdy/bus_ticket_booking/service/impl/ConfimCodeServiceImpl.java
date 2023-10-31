package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.hostmdy.bus_ticket_booking.service.ConfirmCodeService;

@Service
public class ConfimCodeServiceImpl implements ConfirmCodeService{

	@Override
	public String generateConfimationCode() {
		Random random = new Random();
        int code = 1000 + random.nextInt(9000);

        return String.valueOf(code);
	}

}
