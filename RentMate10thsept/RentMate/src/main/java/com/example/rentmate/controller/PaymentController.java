package com.example.rentmate.controller;

import com.example.rentmate.dto.PaymentDTO;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.Lease;
import com.example.rentmate.model.Payment;
import com.example.rentmate.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody @Valid PaymentDTO paymentDTO) throws ResourceNotFoundException {
        Payment payment =paymentService.createPayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Integer id, @RequestBody @Valid PaymentDTO paymentDTO) throws ResourceNotFoundException {
        Payment payment =paymentService.updatePayment(id, paymentDTO);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/getPaymentById/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) throws ResourceNotFoundException {
        Payment payment = paymentService.getPaymentById(id);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @GetMapping("/getAllPayments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Integer id) throws ResourceNotFoundException {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
