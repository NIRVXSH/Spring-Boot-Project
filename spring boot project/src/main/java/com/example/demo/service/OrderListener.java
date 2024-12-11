package com.example.demo.service;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Request.TxnOrderReq;

import jakarta.jms.Message;

@Service
public class OrderListener {

    
    @JmsListener(destination = "${spring.activemq.queue-name}")
    public void receiveMessage(Message orderDetails) {
        System.out.println("Received order message: " + orderDetails);
        
       
        processOrder(orderDetails);
    }

   
    private void processOrder(Message orderDetails) {
        
        System.out.println("Processing order: " + orderDetails);
       
    }
}
