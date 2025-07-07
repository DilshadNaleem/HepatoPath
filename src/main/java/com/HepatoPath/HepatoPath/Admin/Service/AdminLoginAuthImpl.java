package com.HepatoPath.HepatoPath.Admin.Service;

import com.HepatoPath.HepatoPath.Admin.DTO.AdminLoginRequest;
import com.HepatoPath.HepatoPath.Admin.Model.Admin;
import com.HepatoPath.HepatoPath.Admin.Repository.AdminRepository;
import com.HepatoPath.HepatoPath.Admin.Service.Interfaces.AdminLoginAuth;
import com.HepatoPath.HepatoPath.Customer.Service.HashPassword;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginAuthImpl  implements AdminLoginAuth
{
    private final AdminRepository adminRepository;
    private final HashPassword hashPassword;

    public AdminLoginAuthImpl(AdminRepository adminRepository,
                              HashPassword hashPassword)
    {
        this.adminRepository = adminRepository;
        this.hashPassword = hashPassword;
    }

    @Override
    public ResponseEntity<String> loginAdmin(AdminLoginRequest loginRequest, HttpSession session) {
        String normalizedEmail = loginRequest.getEmail().toLowerCase().trim();
        String normalizedPassword = loginRequest.getPassword().trim();

        Admin admin = adminRepository.findByEmail(normalizedEmail);

        if(admin == null)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Account not verified. Please verify your account to Login");
        }

        String hashpsw= hashPassword.hashPassword(normalizedPassword);
        String storedPassword = admin.getPassword().trim();

        System.out.println("Generated hashed Password : " + hashpsw);
        System.out.println("Stored hashed Password : " + admin.getPassword().trim());

        if(storedPassword.equals(hashpsw))
        {
            session.setAttribute("email", normalizedEmail);
            System.out.println("Sessioned Email " + session.getAttribute("email"));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/Admin/AdminDashboard");
            return new ResponseEntity<>(headers,HttpStatus.FOUND);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or Password!");
        }
    }
}
