package com.HepatoPath.HepatoPath.Admin.Service.Interfaces;

import com.HepatoPath.HepatoPath.Admin.DTO.AdminRegisterRequest;
import com.HepatoPath.HepatoPath.Admin.Model.Admin;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminAuthService
{
    ResponseEntity<String> registerAdmin (AdminRegisterRequest request, HttpSession session);
    ResponseEntity<String> verifyOtp (String otp, HttpSession session);
    void updatePassword(String email, String newPassword);
    List<Admin> getAllAdminWithFaceData();
}
