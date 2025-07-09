package com.HepatoPath.HepatoPath.Admin.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PredictionResult
{
    @JsonProperty("predicted_status")
    private String predictedStatus;

    private Map<String, Double> probabilities;
    private Double confidence;

    // Getters and Setters
    public String getPredictedStatus() {
        return predictedStatus;
    }

    public void setPredictedStatus(String predictedStatus) {
        this.predictedStatus = predictedStatus;
    }

    public Map<String, Double> getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(Map<String, Double> probabilities) {
        this.probabilities = probabilities;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
}
