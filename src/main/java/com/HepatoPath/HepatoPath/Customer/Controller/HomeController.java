package com.HepatoPath.HepatoPath.Customer.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String home()
    {
        return "/Customer/Signing";
    }

    @GetMapping("/Customer/Signing")
    public String Signing() {
        return "/Customer/Signing";
    }


    @GetMapping("/Customer/Dashboard")
    public String Dashboard(HttpSession session)
    {
        if (session.getAttribute("email") == null)
        {
            return "/Customer/Signing";
        }
        return "/Customer/Dashboard";
    }

    @GetMapping("/Customer/EditProfile")
    public String profile()
    {
        return "/Customer/edit_profile";
    }


    @GetMapping("/Customer/verification")
    public String showverificationPage()
    {
        return "/Customer/verification";
    }

    @GetMapping("/Customer/ResetPassword")
    public String ResetPasswordForm()
    {
        return "/Customer/Recover_Psw";
    }

    @GetMapping("/Customer/DiseaseClassifier")
    public String Diseae()
    {
        return "/Customer/DiseaseClassifier";
    }

    @GetMapping("/Customer/DiseaseSymptom")
    public String DiseaseSymptom()
    {
        return "/Customer/DiseaseSymptom";
    }

    @GetMapping("/Customer/FaceLogin")
    public String FaceLogin()
    {
        return "/Customer/face-login";
    }

    @GetMapping("/Customer/UpdateFace")
    public String UpdateFace()
    {
        return "/Customer/Update_Face";
    }

    @GetMapping("/Customer/FaceEnrollment")
    public String FaceEnrollment()
    {
        return "/Customer/face_enrollment";
    }
}
