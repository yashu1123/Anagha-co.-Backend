package com.jobportal.backend.controller;

import com.jobportal.backend.entity.Order;
import com.jobportal.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/place-order")
    public Order placeOrder(@RequestBody Order order) {
        System.out.println("Received order: " + order.getName());

        // Save order in database
        Order savedOrder = orderRepository.save(order);

        // Send email to admin
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("yaswanthy81@gmail.com");   
        message.setSubject("New Order Received");
        message.setText(
                "New Order Details:\n\n" +
                "Customer Name: " + order.getName() + "\n" +
                "Phone: " + order.getPhone() + "\n" +
                "Location: " + order.getLocation() + "\n" +
                "Address: " + order.getAddress() + "\n" +
                "Pincode: " + order.getPincode() + "\n" +
                "Product: " + order.getProductName() + "\n" +
                "Quantity: " + order.getQuantity() + "\n" +
                "Payment Method: " + order.getPaymentMethod() + "\n" +
                "Total Price: ₹" + order.getTotalPrice()
        );

        mailSender.send(message);

        return savedOrder;
    }
}