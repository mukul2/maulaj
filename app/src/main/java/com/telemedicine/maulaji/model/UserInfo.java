package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("salt")
    @Expose
    private Object salt;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("activation_code")
    @Expose
    private Object activationCode;
    @SerializedName("forgotten_password_code")
    @Expose
    private Object forgottenPasswordCode;
    @SerializedName("forgotten_password_time")
    @Expose
    private Object forgottenPasswordTime;
    @SerializedName("remember_code")
    @Expose
    private Object rememberCode;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("first_name")
    @Expose
    private Object firstName;
    @SerializedName("last_name")
    @Expose
    private Object lastName;
    @SerializedName("company")
    @Expose
    private Object company;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("hospital_ion_id")
    @Expose
    private String hospitalIonId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("patient_id")
    @Expose
    private String patientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getSalt() {
        return salt;
    }

    public void setSalt(Object salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(Object activationCode) {
        this.activationCode = activationCode;
    }

    public Object getForgottenPasswordCode() {
        return forgottenPasswordCode;
    }

    public void setForgottenPasswordCode(Object forgottenPasswordCode) {
        this.forgottenPasswordCode = forgottenPasswordCode;
    }

    public Object getForgottenPasswordTime() {
        return forgottenPasswordTime;
    }

    public void setForgottenPasswordTime(Object forgottenPasswordTime) {
        this.forgottenPasswordTime = forgottenPasswordTime;
    }

    public Object getRememberCode() {
        return rememberCode;
    }

    public void setRememberCode(Object rememberCode) {
        this.rememberCode = rememberCode;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public String getHospitalIonId() {
        return hospitalIonId;
    }

    public void setHospitalIonId(String hospitalIonId) {
        this.hospitalIonId = hospitalIonId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

}