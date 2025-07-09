package com.HepatoPath.HepatoPath.Admin.Controller.CirrosisModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Controller
@RequestMapping("/Admin")
public class ModelInfo {
    private static final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ModelInfo")
    public String getModelInfo(Model model) {
        final String API_URL = "http://localhost:5000/api/cirrhosis/model_info";

        try {
            Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);

            if (response != null && !response.containsKey("error")) {
                model.addAttribute("metrics", response.get("metrics"));
                model.addAttribute("selectedFeatures", response.get("selected_features"));
                model.addAttribute("featureNames", response.get("feature_names"));
                model.addAttribute("modelType", response.get("model_type"));
                model.addAttribute("lastTrained", response.get("last_trained"));
            } else {
                model.addAttribute("error", response != null ? response.get("error") : "Unable to connect to model API");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching model information: " + e.getMessage());
        }

        return "Admin/CirrhosisPredictionModel/Model_Info";
    }
}