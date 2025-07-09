package com.HepatoPath.HepatoPath.Admin.Controller.CirrosisModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Admin")
public class SampleController {
    private static final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/Sample")
    public String getSample(Model model) {
        final String API_URL = "http://localhost:5000/api/cirrhosis/data_sample";

        try {
            // Assuming the API returns a list of sample data
            List<Map<String, Object>> samples = restTemplate.getForObject(API_URL, List.class);

            if (samples != null && !samples.isEmpty()) {
                model.addAttribute("samples", samples);
                model.addAttribute("success", true);
            } else {
                model.addAttribute("error", "No sample data available");
                model.addAttribute("success", false);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Failed to fetch sample data: " + e.getMessage());
            model.addAttribute("success", false);
        }

        return "Admin/CirrhosisPredictionModel/Sample";
    }
}