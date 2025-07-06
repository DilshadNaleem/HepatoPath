package com.HepatoPath.HepatoPath.Customer.Service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashPassword
{
    public String hashPassword(String password)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.trim().getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes)
            {
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException("Error hashing password : " + e.getMessage());
        }
    }
}
