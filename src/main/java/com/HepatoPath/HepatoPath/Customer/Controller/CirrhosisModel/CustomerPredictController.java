package com.HepatoPath.HepatoPath.Customer.Controller.CirrhosisModel;

import com.HepatoPath.HepatoPath.Admin.Controller.CirrosisModel.AdminPredictController;
import com.HepatoPath.HepatoPath.Admin.DTO.CirrhosisInfo;
import com.HepatoPath.HepatoPath.Admin.DTO.PredictionResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/Customer")
public class CustomerPredictController
{
    private static final Logger logger = LoggerFactory.getLogger(AdminPredictController.class);
    private static final String API_URL = "http://localhost:5000/api/cirrhosis/predict";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/PredictCirrhosis")
    public String showPredictionForm(Model model) {
        logger.info("Displaying cirrhosis prediction form");
        model.addAttribute("cirrhosisInfo", new CirrhosisInfo());
        return "Customer/CirrhosisPredictionModel/CustomerPredict";
    }

    @PostMapping("/PredictCirrhosis")
    public String predictCirrhosis(@ModelAttribute CirrhosisInfo cirrhosisInfo, Model model) {
        try {
            // Log incoming request data
            System.out.println("Received form data:");
            System.out.println("ID: " + cirrhosisInfo.getId());
            System.out.println("N_Days: " + cirrhosisInfo.getN_Days());
            System.out.println("Age: " + cirrhosisInfo.getAge());
            System.out.println("Bilirubin: " + cirrhosisInfo.getBilirubin());
            System.out.println("Cholesterol: " + cirrhosisInfo.getCholesterol());
            System.out.println("Albumin: " + cirrhosisInfo.getAlbumin());
            System.out.println("Copper: " + cirrhosisInfo.getCopper());
            System.out.println("Alk_Phos: " + cirrhosisInfo.getAlk_Phos());
            System.out.println("SGOT: " + cirrhosisInfo.getSGOT());
            System.out.println("Tryglicerides: " + cirrhosisInfo.getTryglicerides());
            System.out.println("Platelets: " + cirrhosisInfo.getPlatelets());
            System.out.println("Prothrombin: " + cirrhosisInfo.getProthrombin());
            System.out.println("Stage: " + cirrhosisInfo.getStage());
            System.out.println("Drug: " + cirrhosisInfo.getDrug());
            System.out.println("Sex: " + cirrhosisInfo.getSex());
            System.out.println("Ascites: " + cirrhosisInfo.getAscites());
            System.out.println("Hepatomegaly: " + cirrhosisInfo.getHepatomegaly());
            System.out.println("Spiders: " + cirrhosisInfo.getSpiders());
            System.out.println("Edema: " + cirrhosisInfo.getEdema());

            // Or print as JSON
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(cirrhosisInfo);
            System.out.println("Form data as JSON:");
            System.out.println(json);

            logger.info("Received prediction request for patient ID: {}", cirrhosisInfo.getId());
            logger.debug("Full request data: {}", cirrhosisInfo.toString());

            // Convert to JSON for better logging
            String requestJson = objectMapper.writeValueAsString(cirrhosisInfo);
            logger.debug("Request JSON: {}", requestJson);

            // Log specific fields that might cause issues
            logger.debug("Drug value being sent: {}", cirrhosisInfo.getDrug());
            logger.debug("Sex value being sent: {}", cirrhosisInfo.getSex());
            logger.debug("Stage value being sent: {}", cirrhosisInfo.getStage());

            // Call Flask API
            logger.info("Sending request to Flask API at: {}", API_URL);
            PredictionResult result = restTemplate.postForObject(
                    API_URL,
                    cirrhosisInfo,
                    PredictionResult.class
            );

            // Log successful response
            logger.info("Received successful prediction response");
            if (result != null) {
                logger.debug("Prediction result: Status={}, Confidence={}%",
                        result.getPredictedStatus(),
                        result.getConfidence());
                logger.debug("Full probabilities: {}", result.getProbabilities());
            }

            // Add results to model
            model.addAttribute("result", result);
            model.addAttribute("confidencePercentage", String.format("%.2f%%", result.getConfidence()));
            model.addAttribute("probabilities", result.getProbabilities());

        } catch (Exception e) {
            // Log the full error stack trace
            logger.error("Prediction failed for patient ID: {}", cirrhosisInfo.getId(), e);
            logger.error("Error details: {}", e.getMessage());

            // Add error to model
            String errorMsg = "Prediction failed: " + e.getMessage();
            if (e.getMessage().contains("Drug")) {
                errorMsg += " (Please check the Drug value)";
            }
            model.addAttribute("error", errorMsg);
        }

        return "Customer/CirrhosisPredictionModel/CustomerPredict";
    }
}
