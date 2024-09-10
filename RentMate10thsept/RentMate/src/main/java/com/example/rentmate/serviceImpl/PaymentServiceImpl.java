package com.example.rentmate.serviceImpl;

import com.example.rentmate.dto.PaymentDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Lease;
import com.example.rentmate.model.Order;
import com.example.rentmate.model.Payment;
import com.example.rentmate.model.Tenant;
import com.example.rentmate.repository.LeaseRepository;
import com.example.rentmate.repository.OrderRepository;
import com.example.rentmate.repository.PaymentRepository;
import com.example.rentmate.repository.TenantRepository;
import com.example.rentmate.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment createPayment(PaymentDTO paymentDTO) throws ResourceNotFoundException {
        Payment payment = new Payment();
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());

        // Validate Lease
        Lease lease = leaseRepository.findById(paymentDTO.getLeaseId())
                .orElseThrow(() -> new ResourceNotFoundException("Lease not found with id: " + paymentDTO.getLeaseId()));
        payment.setLease(lease);

        // Validate Tenant
        Tenant tenant = tenantRepository.findById(paymentDTO.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + paymentDTO.getTenantId()));
        payment.setTenant(tenant);

        // Validate Order
        Order order = orderRepository.findById(paymentDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + paymentDTO.getOrderId()));
        payment.setOrder(order);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Integer paymentId, PaymentDTO paymentDTO) throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));

        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());

        if (paymentDTO.getLeaseId() != null) {
            Lease lease = leaseRepository.findById(paymentDTO.getLeaseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lease not found with id: " + paymentDTO.getLeaseId()));
            payment.setLease(lease);
        }

        if (paymentDTO.getTenantId() != null) {
            Tenant tenant = tenantRepository.findById(paymentDTO.getTenantId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id: " + paymentDTO.getTenantId()));
            payment.setTenant(tenant);
        }

        if (paymentDTO.getOrderId() != null) {
            Order order = orderRepository.findById(paymentDTO.getOrderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + paymentDTO.getOrderId()));
            payment.setOrder(order);
        }

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Integer paymentId) throws ResourceNotFoundException {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void deletePayment(Integer paymentId) throws ResourceNotFoundException {
        if (!paymentRepository.existsById(paymentId)) {
            throw new ResourceNotFoundException("Payment not found with id: " + paymentId);
        }
        paymentRepository.deleteById(paymentId);
    }
}
