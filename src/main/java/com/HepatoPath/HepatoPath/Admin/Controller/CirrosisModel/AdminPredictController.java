package com.HepatoPath.HepatoPath.Admin.Controller.CirrosisModel;

import com.HepatoPath.HepatoPath.Admin.DTO.CirrhosisInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/Admin")
public class AdminPredictController
{
    private static final String API_URL = "http://localhost:5000//api/cirrhosis/predict";
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/PredictCirrhosis")
    public String predictCirrhosis(@ModelAttribute CirrhosisInfo, Model model)
    {

    }
}
