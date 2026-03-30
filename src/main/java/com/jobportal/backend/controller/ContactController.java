package com.jobportal.backend.controller;

import com.jobportal.backend.entity.ContactMessage;
import com.jobportal.backend.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody ContactMessage contactMessage) {
        contactMessageRepository.save(contactMessage);
        return ResponseEntity.ok("Message sent successfully!");
    }

    @GetMapping("/messages")
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAll();
    }
}