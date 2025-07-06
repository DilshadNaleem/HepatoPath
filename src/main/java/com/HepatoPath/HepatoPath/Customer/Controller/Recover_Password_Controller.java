package com.HepatoPath.HepatoPath.Customer.Controller;

import com.HepatoPath.HepatoPath.Customer.Model.Customer;
import com.HepatoPath.HepatoPath.Customer.Repository.CustomerRepository;
import com.HepatoPath.HepatoPath.Customer.Service.Interfaces.EmailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@Controller
public class Recover_Password_Controller
{
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    EmailService emailService;

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/Customer/ForgotPassword")
    public void processForgotPassword (@RequestParam("email") String email,
                                       HttpSession session,
                                       HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Customer customer = customerRepository.findByEmailAndStatus(email,1);

        if(customer == null)
        {
            out.println("<script type='text/javascript'>");
            out.println("alert('Email not found or account inactive.');");
            out.println("window.location.href = '/Customer/ResetPassword';");
            out.println("</script>");
            return;
        }

        String token = UUID.randomUUID().toString();
        session.setAttribute("token", token);
        session.setAttribute("email",email);

        String resetLink = "http://localhost:8081/reset-password?token=" + token;

        emailService.sendPasswordResetEmail(email,resetLink);

        out.println("<script type='text/javascript'>");
        out.print("alert('Password reset Link send to the email');");
        out.println("window.location.href = '/Customer/Signing';");
        out.println("</script>");
    }
}
