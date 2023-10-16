package com.hostmdy.bus_ticket_booking.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {
	private final Environment env;
	
	@PostMapping("/upload/{ticketId}")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,@PathVariable Long ticketId){
		
		String uploadPath = env.getProperty("upload.path");
		
		String fileName = ticketId+".jpg";
		Path filePath = Path.of(uploadPath+fileName);
		
		try {
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok("image upload is successful");
	}
	
	@GetMapping("/{imageName}")
	public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException{
		ClassPathResource resource = new ClassPathResource("static/images/" + imageName);
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
	}
}
