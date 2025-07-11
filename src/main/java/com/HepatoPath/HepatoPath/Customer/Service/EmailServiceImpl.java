package com.HepatoPath.HepatoPath.Customer.Service;

import com.HepatoPath.HepatoPath.Customer.Service.Interfaces.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendVerificationEmail(String toEmail, String otp) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromEmail, "HepatoPath");
            helper.setTo(toEmail);
            helper.setSubject("Verify Your Account");

            // Stylize each OTP digit
            StringBuilder styledOtp = new StringBuilder();
            for (char digit : otp.toCharArray()) {
                styledOtp.append("<span style='display: inline-block; " +
                                "margin: 5px; padding: 12px 20px; " +
                                "font-size: 24px; font-weight: 600; " +
                                "border: 1px solid #dcdcdc; border-radius: 8px; " +
                                "background-color: #ffffff; color: #2c3e50; " +
                                "box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); " +
                                "font-family: \"Segoe UI\", Tahoma, Geneva, Verdana, sans-serif;'>")
                        .append(digit)
                        .append("</span>");
            }

            String htmlContent = "<html><body>" +
                    "<div style='max-width: 600px; margin: auto; padding: 30px; background-color: #f9f9f9; " +
                    "border: 1px solid #e0e0e0; border-radius: 10px; font-family: \"Segoe UI\", sans-serif;'>" +
                    "<h2 style='color: #2E86C1; text-align: center;'>Your Verification Code</h2>" +
                    "<p style='font-size: 16px; color: #333333; text-align: center;'>Use the code below to verify your account:</p>" +
                    "<div style='text-align: center; margin-top: 20px;'>" + styledOtp + "</div>" +
                    "<p style='margin-top: 30px; font-size: 14px; color: #777777; text-align: center;'>If you didn’t request this, please ignore this email.</p>" +
                    "</div></body></html>";

            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendPasswordResetEmail(String toEmail, String resetLink) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromEmail, "HepatoPath Password Reset");
            helper.setTo(toEmail);
            helper.setSubject("Password Reset Request");

            String htmlContent = "<html><body>" +
                    "<div style='max-width: 600px; margin: auto; padding: 30px; background-color: #fdfdfd; " +
                    "border: 1px solid #eaeaea; border-radius: 10px; font-family: \"Segoe UI\", sans-serif;'>" +
                    "<h2 style='color: #E74C3C; text-align: center;'>Password Reset</h2>" +
                    "<p style='font-size: 16px; color: #555555; text-align: center;'>Click the button below to reset your password:</p>" +
                    "<div style='text-align: center; margin-top: 20px;'>" +
                    "<a href='" + resetLink + "' style='display: inline-block; " +
                    "background-color: #28a745; color: #ffffff; font-size: 16px; " +
                    "padding: 14px 24px; text-decoration: none; border-radius: 6px; " +
                    "box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); " +
                    "transition: background-color 0.3s ease;'>Reset Password</a>" +
                    "</div>" +
                    "<p style='margin-top: 30px; font-size: 14px; color: #888888; text-align: center;'>If you did not request this, please ignore this email.</p>" +
                    "</div></body></html>";

            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



}
