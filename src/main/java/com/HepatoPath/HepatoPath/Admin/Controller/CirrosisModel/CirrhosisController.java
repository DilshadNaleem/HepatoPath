package com.HepatoPath.HepatoPath.Admin.Controller.CirrosisModel;

import com.HepatoPath.HepatoPath.Admin.DTO.CirrhosisTrainingResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Admin/Cirrhosis")
public class CirrhosisController {
    private static final String API_URL = "http://localhost:5000/api/cirrhosis/train";
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/train")
    public String showTrainingPage(Model model) {
        // Initialize empty model attributes for the form
        return "/Admin/CirrhosisPredictionModel/CirrhosisTrain";
    }

    @PostMapping("/train")
    public String startTraining(Model model) {
        try {
            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create request entity with empty body since your Flask endpoint doesn't need parameters
            HttpEntity<String> request = new HttpEntity<>("{}", headers);

            // Make POST request to Flask API
            CirrhosisTrainingResponse response = restTemplate.postForObject(
                    API_URL,
                    request,
                    CirrhosisTrainingResponse.class
            );

            if (response == null || !"success".equals(response.getStatus())) {
                throw new RuntimeException(response != null ? response.getMessage() : "No response from server");
            }

            // Add all response data to the model
            model.addAttribute("success", true);
            model.addAttribute("message", response.getMessage());
            model.addAttribute("metrics", response.getMetrics());
            model.addAttribute("selectedFeatures", response.getSelectedFeatures());

            // Add visualization images
            if (response.getVisualizations() != null) {
                model.addAttribute("featureImportanceImage", response.getVisualizations().getFeature_importance());
                model.addAttribute("confusionMatrixImage", response.getVisualizations().getConfusion_matrix());
                model.addAttribute("rocCurveImage", response.getVisualizations().getRoc_curve());
            }

        } catch (Exception e) {
            model.addAttribute("success", false);
            model.addAttribute("message", "Training failed: " + e.getMessage());
        }

        return "/Admin/CirrhosisPredictionModel/CirrhosisTrain";
    }
}



