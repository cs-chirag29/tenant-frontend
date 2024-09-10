package com.example.rentmate.serviceImpl;

import com.example.rentmate.dto.OrderDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.*;
import com.example.rentmate.repository.*;
import com.example.rentmate.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Order createOrder(OrderDTO orderDTO) throws ResourceNotFoundException {
        Order order = new Order();
        order.setApplicationDate(orderDTO.getApplicationDate());
        order.setStartDate(orderDTO.getStartDate());
        order.setEndDate(orderDTO.getEndDate());
        order.setStatus(orderDTO.getStatus());
        order.setRentAmount(Double.parseDouble(orderDTO.getRentAmount()));
        order.setSecurityDeposit(Double.parseDouble(orderDTO.getSecurityDeposit()));
        order.setCreatedAt(orderDTO.getCreatedAt());
        order.setUpdatedAt(orderDTO.getUpdatedAt());
        order.setCompletedAt(orderDTO.getCompletedAt());

        Property property = propertyRepository.findById(orderDTO.getPropertyId())
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + orderDTO.getPropertyId()));
        order.setProperty(property);

        Unit unit = unitRepository.findById(orderDTO.getUnitId())
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + orderDTO.getUnitId()));
        order.setUnit(unit);

        Tenant tenant = tenantRepository.findById(orderDTO.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + orderDTO.getTenantId()));
        order.setTenant(tenant);

        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, OrderDTO orderDTO) throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        order.setApplicationDate(orderDTO.getApplicationDate());
        order.setStartDate(orderDTO.getStartDate());
        order.setEndDate(orderDTO.getEndDate());
        order.setStatus(orderDTO.getStatus());
        order.setRentAmount(Double.parseDouble(orderDTO.getRentAmount()));
        order.setSecurityDeposit(Double.parseDouble(orderDTO.getSecurityDeposit()));
        order.setCreatedAt(orderDTO.getCreatedAt());
        order.setUpdatedAt(orderDTO.getUpdatedAt());
        order.setCompletedAt(orderDTO.getCompletedAt());

        if (orderDTO.getPropertyId() != null) {
            Property property = propertyRepository.findById(orderDTO.getPropertyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + orderDTO.getPropertyId()));
            order.setProperty(property);
        }

        if (orderDTO.getUnitId() != null) {
            Unit unit = unitRepository.findById(orderDTO.getUnitId())
                    .orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + orderDTO.getUnitId()));
            order.setUnit(unit);
        }

        if (orderDTO.getTenantId() != null) {
            Tenant tenant = tenantRepository.findById(orderDTO.getTenantId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + orderDTO.getTenantId()));
            order.setTenant(tenant);
        }

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) throws ResourceNotFoundException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws ResourceNotFoundException {
        if (!orderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException("Order not found with id: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }
}
