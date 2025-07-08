package com.HepatoPath.HepatoPath.Admin.DTO;

import com.HepatoPath.HepatoPath.Admin.Controller.CirrosisModel.CirrhosisController;

import java.util.List;
import java.util.Map;

public class CirrhosisTrainingResponse
{
    private String status;
    private String message;
    private Map<String, Double> metrics;
    private List<String> selectedFeatures;

    public Visualizations getVisualizations() {
        return visualizations;
    }

    public void setVisualizations(Visualizations visualizations) {
        this.visualizations = visualizations;
    }

    private Visualizations visualizations;

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Double> getMetrics() {
        return metrics;
    }

    public void setMetrics(Map<String, Double> metrics) {
        this.metrics = metrics;
    }

    public List<String> getSelectedFeatures() {
        return selectedFeatures;
    }

    public void setSelectedFeatures(List<String> selectedFeatures) {
        this.selectedFeatures = selectedFeatures;
    }


}
