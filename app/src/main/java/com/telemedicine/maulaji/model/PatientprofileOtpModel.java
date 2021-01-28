package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientprofileOtpModel {

@SerializedName("id")
@Expose
private String id;
@SerializedName("img_url")
@Expose
private String imgUrl;
@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("doctor")
@Expose
private String doctor;
@SerializedName("address")
@Expose
private String address;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("sex")
@Expose
private String sex;
@SerializedName("birthdate")
@Expose
private String birthdate;
@SerializedName("age")
@Expose
private Object age;
@SerializedName("bloodgroup")
@Expose
private String bloodgroup;
@SerializedName("ion_user_id")
@Expose
private String ionUserId;
@SerializedName("patient_id")
@Expose
private String patientId;
@SerializedName("add_date")
@Expose
private String addDate;
@SerializedName("registration_time")
@Expose
private String registrationTime;
@SerializedName("how_added")
@Expose
private Object howAdded;
@SerializedName("hospital_id")
@Expose
private String hospitalId;
@SerializedName("account_status")
@Expose
private String accountStatus;
@SerializedName("identitydoc")
@Expose
private Object identitydoc;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getImgUrl() {
return imgUrl;
}

public void setImgUrl(String imgUrl) {
this.imgUrl = imgUrl;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getDoctor() {
return doctor;
}

public void setDoctor(String doctor) {
this.doctor = doctor;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public String getSex() {
return sex;
}

public void setSex(String sex) {
this.sex = sex;
}

public String getBirthdate() {
return birthdate;
}

public void setBirthdate(String birthdate) {
this.birthdate = birthdate;
}

public Object getAge() {
return age;
}

public void setAge(Object age) {
this.age = age;
}

public String getBloodgroup() {
return bloodgroup;
}

public void setBloodgroup(String bloodgroup) {
this.bloodgroup = bloodgroup;
}

public String getIonUserId() {
return ionUserId;
}

public void setIonUserId(String ionUserId) {
this.ionUserId = ionUserId;
}

public String getPatientId() {
return patientId;
}

public void setPatientId(String patientId) {
this.patientId = patientId;
}

public String getAddDate() {
return addDate;
}

public void setAddDate(String addDate) {
this.addDate = addDate;
}

public String getRegistrationTime() {
return registrationTime;
}

public void setRegistrationTime(String registrationTime) {
this.registrationTime = registrationTime;
}

public Object getHowAdded() {
return howAdded;
}

public void setHowAdded(Object howAdded) {
this.howAdded = howAdded;
}

public String getHospitalId() {
return hospitalId;
}

public void setHospitalId(String hospitalId) {
this.hospitalId = hospitalId;
}

public String getAccountStatus() {
return accountStatus;
}

public void setAccountStatus(String accountStatus) {
this.accountStatus = accountStatus;
}

public Object getIdentitydoc() {
return identitydoc;
}

public void setIdentitydoc(Object identitydoc) {
this.identitydoc = identitydoc;
}

}