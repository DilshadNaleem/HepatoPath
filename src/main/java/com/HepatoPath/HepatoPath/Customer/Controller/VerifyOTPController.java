package com.HepatoPath.HepatoPath.Customer.Controller;

import com.HepatoPath.HepatoPath.Customer.Service.Interfaces.CustomerAuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class VerifyOTPController {
    private static final Logger logger = LoggerFactory.getLogger(VerifyOTPController.class);
    private final CustomerAuthService customerAuthService;

    public VerifyOTPController(CustomerAuthService customerAuthService) {
        this.customerAuthService = customerAuthService;
    }

    @PostMapping("/Customer/verifyOtp")
    public void verifyOtp(
            @RequestParam String otp,
            HttpSession session,
            HttpServletResponse response
    ) throws IOException {
        logger.info("OTP verification request received for OTP: {}", otp);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body><script type='text/javascript'>");

            logger.debug("Starting OTP verification process...");
            ResponseEntity<String> verificationResult = customerAuthService.verifyOtp(otp, session);

            if (verificationResult.getStatusCode() == HttpStatus.OK) {
                logger.info("OTP verification successful for session: {}", session.getId());
                out.println("alert('Account verified successfully! Please login.');");
                out.println("window.location.href = '/Customer/Signing';");
            } else {
                String errorMessage = verificationResult.getBody() != null ?
                        verificationResult.getBody().replace("'", "\\'") :
                        "Account verification failed. Please try again.";
                logger.warn("OTP verification failed for session: {}. Reason: {}", session.getId(), errorMessage);
                out.println("alert('" + errorMessage + "');");
                out.println("window.location.href = '/Customer/verification';");
            }

            out.println("</script></body></html>");
        } catch (Exception e) {
            logger.error("Exception occurred during OTP verification for session: {}", session.getId(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.println("<html><body><script type='text/javascript'>");
                out.println("alert('An error occurred during verification.');");
                out.println("window.location.href = '/Customer/verification';");
                out.println("</script></body></html>");
            }
        }
    }
}