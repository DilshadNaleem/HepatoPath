package com.HepatoPath.HepatoPath.Customer.Service.Interfaces;

public interface EmailService
{
    void sendVerificationEmail (String toEmail, String otp);
    void sendPasswordResetEmail (String toEmail, String resetLink);
}
