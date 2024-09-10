package com.example.rentmate.service;

import com.example.rentmate.dto.OrderDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderDTO orderDTO) throws ResourceNotFoundException;
    Order updateOrder(Long orderId, OrderDTO orderDTO) throws ResourceNotFoundException;
    Order getOrderById(Long orderId) throws ResourceNotFoundException;
    List<Order> getAllOrders();
    void deleteOrder(Long orderId) throws ResourceNotFoundException;
}
