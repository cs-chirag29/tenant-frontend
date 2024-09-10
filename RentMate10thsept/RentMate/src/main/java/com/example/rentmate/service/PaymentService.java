package com.example.rentmate.service;

import com.example.rentmate.dto.PaymentDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(PaymentDTO paymentDTO) throws ResourceNotFoundException;
    Payment updatePayment(Integer paymentId, PaymentDTO paymentDTO) throws ResourceNotFoundException;
    Payment getPaymentById(Integer paymentId) throws ResourceNotFoundException;
    List<Payment> getAllPayments();
    void deletePayment(Integer paymentId) throws ResourceNotFoundException;
}
