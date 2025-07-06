package com.HepatoPath.HepatoPath.Customer.Service.Interfaces;

import com.HepatoPath.HepatoPath.Customer.DTO.RegisterRequest;
import com.HepatoPath.HepatoPath.Customer.Model.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerAuthService
{
    ResponseEntity<String> registerCustomer (RegisterRequest request, HttpSession session);
    ResponseEntity<String> verifyOtp (String otp, HttpSession session);
    void updatePassword(String email, String newPassword);
    List<Customer> getAllCustomersWithFaceData();
}
