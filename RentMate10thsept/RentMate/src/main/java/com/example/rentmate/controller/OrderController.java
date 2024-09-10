package com.example.rentmate.controller;

import com.example.rentmate.dto.OrderDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Order;
import com.example.rentmate.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderDTO orderDTO) throws ResourceNotFoundException {
        Order order =orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderDTO orderDTO) throws ResourceNotFoundException {
        Order order = orderService.updateOrder(id, orderDTO);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws ResourceNotFoundException {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) throws ResourceNotFoundException {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
