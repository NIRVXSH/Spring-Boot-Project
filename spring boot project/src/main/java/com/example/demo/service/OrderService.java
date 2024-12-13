package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Request.TxnOrderReq;
import com.example.demo.entity.TxnOrders;
import com.example.demo.entity.TxnOrdersItem;
import com.example.demo.repository.TxnOrdersItemRepository;
import com.example.demo.repository.TxnOrdersRepository;

import jakarta.jms.Message;

@Service
public class OrderService {

    @Autowired
    private TxnOrdersRepository ordersRepository;

    @Autowired
    private TxnOrdersItemRepository ordersItemRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${spring.activemq.queue-name}")
    private String queueName;

    public void placeOrder(TxnOrderReq orderDetails) {
        TxnOrders order = new TxnOrders();
        orderDetails.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(orderDetails, order);
        ordersRepository.save(order);

        List<TxnOrdersItem> orderItems = orderDetails.getOrderItems().stream().map(
                item -> TxnOrdersItem.builder()
                .id(UUID.randomUUID().toString())
                .productId(item.getProductId())
                .amount(item.getAmount())
                .orderId(order.getId())
                .discountCode(item.getDiscountCode())
                .createDate(item.getCreateDate())
                .status(item.getStatus())
                .build())
                .toList();

        ordersItemRepository.saveAll(orderItems);

        jmsTemplate.convertAndSend(queueName, orderDetails);
    }

    @JmsListener(destination = "${spring.activemq.queue-name}")
    public void ProcessOrder(Message orderDetails) {
        // System.out.println("Received order message: " + orderDetails);

    }

}
