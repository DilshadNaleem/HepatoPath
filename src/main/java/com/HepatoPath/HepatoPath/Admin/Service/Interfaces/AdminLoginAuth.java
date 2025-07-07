package com.HepatoPath.HepatoPath.Admin.Service.Interfaces;

import com.HepatoPath.HepatoPath.Admin.DTO.AdminLoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AdminLoginAuth
{
    ResponseEntity<String> loginAdmin (AdminLoginRequest loginRequest, HttpSession session);
}
