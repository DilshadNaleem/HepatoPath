package com.HepatoPath.HepatoPath.Admin.DTO;

public class Visualizations
{
    private String feature_importance;
    private String confusion_matrix;
    private String roc_curve;

    public String getFeature_importance() {
        return feature_importance;
    }

    public void setFeature_importance(String feature_importance) {
        this.feature_importance = feature_importance;
    }

    public String getConfusion_matrix() {
        return confusion_matrix;
    }

    public void setConfusion_matrix(String confusion_matrix) {
        this.confusion_matrix = confusion_matrix;
    }

    public String getRoc_curve() {
        return roc_curve;
    }

    public void setRoc_curve(String roc_curve) {
        this.roc_curve = roc_curve;
    }
}

