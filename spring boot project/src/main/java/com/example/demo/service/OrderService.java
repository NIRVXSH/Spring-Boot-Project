package com.example.demo.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Request.TxnOrderReq;

@Service
public class OrderService {

    private final JmsTemplate jmsTemplate;

    @Value("${spring.activemq.queue-name}")
    private String queueName;

    public OrderService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void placeOrder(TxnOrderReq orderDetails) {
        System.out.println("Placing order: " + orderDetails);
        jmsTemplate.convertAndSend(queueName, orderDetails);
    }
}
