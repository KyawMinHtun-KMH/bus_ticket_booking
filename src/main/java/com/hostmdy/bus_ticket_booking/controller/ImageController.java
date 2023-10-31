package com.hostmdy.bus_ticket_booking.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.bus_ticket_booking.domain.Ticket;
import com.hostmdy.bus_ticket_booking.service.TicketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {
	
	private final Environment env;
	private final TicketService ticketService;
	
	@PostMapping("/upload/{ticketId}")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,@PathVariable Long ticketId) throws IOException{
			if(file.isEmpty()) {
				return ResponseEntity.ok("image is empty");
			}
			String uploadPath = env.getProperty("upload.path");
			String imageUuid = UUID.randomUUID().toString();

            String fileName = imageUuid+".jpg";
  
            Path filePath = Path.of(uploadPath+fileName);
            Optional<Ticket> ticketOpt = ticketService.getTicketById(ticketId);
            Ticket ticket = ticketOpt.get();
            ticket.setImage(fileName);
            ticketService.save(ticket);

            try {
				Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            return ResponseEntity.ok("image uploaded");

	}
	
	@GetMapping("/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/images/" + imageName);
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
	
	
	
	
}
