package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrescriptionMedicineModel {

    @SerializedName("medicine_id")
    @Expose
    private Integer medicineId;
    @SerializedName("duration_type")
    @Expose
    private String durationType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duration_length")
    @Expose
    private String durationLength;
    @SerializedName("dose")
    @Expose
    private String dose;
    @SerializedName("varient")
    @Expose
    private String varient;
    @SerializedName("isAfterMeal")
    @Expose
    private Integer isAfterMeal;

    public PrescriptionMedicineModel(Integer medicineId, String durationType, String name,String varient, String durationLength, String dose, Integer isAfterMeal) {
        this.medicineId = medicineId;
        this.durationType = durationType;
        this.name = name;
        this.durationLength = durationLength;
        this.dose = dose;
        this.isAfterMeal = isAfterMeal;
        this.varient = varient;
    }

    public String getVarient() {
        return varient;
    }

    public void setVarient(String varient) {
        this.varient = varient;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    public String getDurationLength() {
        return durationLength;
    }

    public void setDurationLength(String durationLength) {
        this.durationLength = durationLength;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Integer getIsAfterMeal() {
        return isAfterMeal;
    }

    public void setIsAfterMeal(Integer isAfterMeal) {
        this.isAfterMeal = isAfterMeal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}