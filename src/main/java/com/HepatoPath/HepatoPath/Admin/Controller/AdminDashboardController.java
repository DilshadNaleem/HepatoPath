package com.HepatoPath.HepatoPath.Admin.Controller;

import com.HepatoPath.HepatoPath.Admin.Model.Admin;
import com.HepatoPath.HepatoPath.Admin.Repository.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController
{

    private final AdminRepository adminRepository;

    public AdminDashboardController(AdminRepository adminRepository)
    {
        this.adminRepository = adminRepository;
    }


    @GetMapping("/Admin/Dashboard")
    public String showAdminName(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");


        if(email == null || email.isEmpty()) {
            return "redirect:/Admin/Signing";
        }

        Admin admin = adminRepository.findByEmailAndStatus(email,1);

        if (admin == null)
        {
            return "redirect:/Admin/Signing";
        }

        model.addAttribute("adminName", admin.getFirstName());
        model.addAttribute("adminImage", admin.getImage());


        System.out.println("Image Path: " + admin.getImage());
        return "/Admin/AdminDashboard";
    }
}
