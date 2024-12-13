package com.example.demo.controller;



import com.example.demo.dto.Request.TxnOrderReq;
import com.example.demo.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/np/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Endpoint to place an order
    @PostMapping("/place-order")
    public String placeOrder(@RequestBody TxnOrderReq orderDetails) {
        orderService.placeOrder(orderDetails);
        return "Order placed successfully!";
    }
}
