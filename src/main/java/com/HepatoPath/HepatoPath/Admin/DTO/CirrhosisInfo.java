package com.HepatoPath.HepatoPath.Admin.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CirrhosisInfo
{
    private Integer id;

    @JsonProperty("N_Days")
    private String N_Days;

    @JsonProperty("Drug")
    private String Drug;

    @JsonProperty("Age")
    private String Age;

    @JsonProperty("Sex")
    private String Sex;

    @JsonProperty("Ascites")
    private String Ascites;

    @JsonProperty("Hepatomegaly")
    private String Hepatomegaly;

    @JsonProperty("Spiders")
    private String Spiders;

    @JsonProperty("Edema")
    private String Edema;

    @JsonProperty("Bilirubin")
    private Double Bilirubin;

    @JsonProperty("Cholesterol")
    private double Cholesterol;

    @JsonProperty("Albumin")
    private double Albumin;

    @JsonProperty("Copper")
    private double Copper;

    @JsonProperty("Alk_Phos")
    private double Alk_Phos;

    @JsonProperty("SGOT")
    private double SGOT;

    @JsonProperty("Tryglicerides")
    private double Tryglicerides;

    @JsonProperty("Platelets")
    private double Platelets;

    @JsonProperty("Prothrombin")
    private double Prothrombin;

    @JsonProperty("Stage")
    private double Stage;

    public String getN_Days() {
        return N_Days;
    }

    public void setN_Days(String n_Days) {
        N_Days = n_Days;
    }

    public String getDrug() {
        return Drug;
    }

    public void setDrug(String drug) {
        Drug = drug;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAscites() {
        return Ascites;
    }

    public void setAscites(String ascites) {
        Ascites = ascites;
    }



    public String getSpiders() {
        return Spiders;
    }

    public void setSpiders(String spiders) {
        Spiders = spiders;
    }

    public String getEdema() {
        return Edema;
    }

    public void setEdema(String edema) {
        Edema = edema;
    }

    public String getHepatomegaly() {
        return Hepatomegaly;
    }

    public void setHepatomegaly(String hepatomegaly) {
        Hepatomegaly = hepatomegaly;
    }

    public Double getBilirubin() {
        return Bilirubin;
    }

    public void setBilirubin(Double bilirubin) {
        Bilirubin = bilirubin;
    }

    public double getCholesterol() {
        return Cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        Cholesterol = cholesterol;
    }

    public double getAlbumin() {
        return Albumin;
    }

    public void setAlbumin(double albumin) {
        Albumin = albumin;
    }

    public double getCopper() {
        return Copper;
    }

    public void setCopper(double copper) {
        Copper = copper;
    }

    public double getAlk_Phos() {
        return Alk_Phos;
    }

    public void setAlk_Phos(double alk_Phos) {
        Alk_Phos = alk_Phos;
    }

    public double getSGOT() {
        return SGOT;
    }

    public void setSGOT(double SGOT) {
        this.SGOT = SGOT;
    }

    public double getTryglicerides() {
        return Tryglicerides;
    }

    public void setTryglicerides(double tryglicerides) {
        Tryglicerides = tryglicerides;
    }

    public double getPlatelets() {
        return Platelets;
    }

    public void setPlatelets(double platelets) {
        Platelets = platelets;
    }

    public double getProthrombin() {
        return Prothrombin;
    }

    public void setProthrombin(double prothrombin) {
        Prothrombin = prothrombin;
    }

    public double getStage() {
        return Stage;
    }

    public void setStage(double stage) {
        Stage = stage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }
}
