package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorDetailProfile {

@SerializedName("id")
@Expose
private String id;
@SerializedName("img_url")
@Expose
private String imgUrl;
@SerializedName("name")
@Expose
private String name;
@SerializedName("last_seen")
@Expose
private String lastSeen;
@SerializedName("email")
@Expose
private String email;
@SerializedName("department_id")
@Expose
private String departmentId;
@SerializedName("address")
@Expose
private String address;
@SerializedName("latitude")
@Expose
private String latitude;
@SerializedName("longitude")
@Expose
private String longitude;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("department")
@Expose
private String department;
@SerializedName("doctor_type")
@Expose
private String doctorType;
@SerializedName("home_visit_status")
@Expose
private String homeVisitStatus;
@SerializedName("urgent_care_status")
@Expose
private String urgentCareStatus;
@SerializedName("profile")
@Expose
private String profile;
@SerializedName("x")
@Expose
private Object x;
@SerializedName("y")
@Expose
private Object y;
@SerializedName("ion_user_id")
@Expose
private String ionUserId;
@SerializedName("hospital_id")
@Expose
private String hospitalId;
@SerializedName("identitydoc")
@Expose
private String identitydoc;
@SerializedName("doctor_lic_doc")
@Expose
private String doctorLicDoc;
@SerializedName("proof_of_address")
@Expose
private String proofOfAddress;
@SerializedName("country")
@Expose
private String country;
@SerializedName("city")
@Expose
private String city;
@SerializedName("state_province")
@Expose
private String stateProvince;
@SerializedName("postal_code")
@Expose
private String postalCode;
@SerializedName("gender")
@Expose
private String gender;
@SerializedName("date_of_birth")
@Expose
private String dateOfBirth;
@SerializedName("about_me")
@Expose
private String aboutMe;
@SerializedName("pricing")
@Expose
private String pricing;
@SerializedName("urgent_fee")
@Expose
private String urgentFee;
@SerializedName("home_fee")
@Expose
private String homeFee;
@SerializedName("services")
@Expose
private String services;
@SerializedName("specialization")
@Expose
private String specialization;
@SerializedName("clinic_info")
@Expose
private String clinicInfo;
@SerializedName("education")
@Expose
private String education;
@SerializedName("experience")
@Expose
private String experience;
@SerializedName("awards")
@Expose
private String awards;
@SerializedName("is_approved")
@Expose
private String isApproved;

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

public String getLastSeen() {
return lastSeen;
}

public void setLastSeen(String lastSeen) {
this.lastSeen = lastSeen;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getDepartmentId() {
return departmentId;
}

public void setDepartmentId(String departmentId) {
this.departmentId = departmentId;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getLatitude() {
return latitude;
}

public void setLatitude(String latitude) {
this.latitude = latitude;
}

public String getLongitude() {
return longitude;
}

public void setLongitude(String longitude) {
this.longitude = longitude;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public String getDepartment() {
return department;
}

public void setDepartment(String department) {
this.department = department;
}

public String getDoctorType() {
return doctorType;
}

public void setDoctorType(String doctorType) {
this.doctorType = doctorType;
}

public String getHomeVisitStatus() {
return homeVisitStatus;
}

public void setHomeVisitStatus(String homeVisitStatus) {
this.homeVisitStatus = homeVisitStatus;
}

public String getUrgentCareStatus() {
return urgentCareStatus;
}

public void setUrgentCareStatus(String urgentCareStatus) {
this.urgentCareStatus = urgentCareStatus;
}

public String getProfile() {
return profile;
}

public void setProfile(String profile) {
this.profile = profile;
}

public Object getX() {
return x;
}

public void setX(Object x) {
this.x = x;
}

public Object getY() {
return y;
}

public void setY(Object y) {
this.y = y;
}

public String getIonUserId() {
return ionUserId;
}

public void setIonUserId(String ionUserId) {
this.ionUserId = ionUserId;
}

public String getHospitalId() {
return hospitalId;
}

public void setHospitalId(String hospitalId) {
this.hospitalId = hospitalId;
}

public String getIdentitydoc() {
return identitydoc;
}

public void setIdentitydoc(String identitydoc) {
this.identitydoc = identitydoc;
}

public String getDoctorLicDoc() {
return doctorLicDoc;
}

public void setDoctorLicDoc(String doctorLicDoc) {
this.doctorLicDoc = doctorLicDoc;
}

public String getProofOfAddress() {
return proofOfAddress;
}

public void setProofOfAddress(String proofOfAddress) {
this.proofOfAddress = proofOfAddress;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getStateProvince() {
return stateProvince;
}

public void setStateProvince(String stateProvince) {
this.stateProvince = stateProvince;
}

public String getPostalCode() {
return postalCode;
}

public void setPostalCode(String postalCode) {
this.postalCode = postalCode;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getDateOfBirth() {
return dateOfBirth;
}

public void setDateOfBirth(String dateOfBirth) {
this.dateOfBirth = dateOfBirth;
}

public String getAboutMe() {
return aboutMe;
}

public void setAboutMe(String aboutMe) {
this.aboutMe = aboutMe;
}

public String getPricing() {
return pricing;
}

public void setPricing(String pricing) {
this.pricing = pricing;
}

public String getUrgentFee() {
return urgentFee;
}

public void setUrgentFee(String urgentFee) {
this.urgentFee = urgentFee;
}

public String getHomeFee() {
return homeFee;
}

public void setHomeFee(String homeFee) {
this.homeFee = homeFee;
}

public String getServices() {
return services;
}

public void setServices(String services) {
this.services = services;
}

public String getSpecialization() {
return specialization;
}

public void setSpecialization(String specialization) {
this.specialization = specialization;
}

public String getClinicInfo() {
return clinicInfo;
}

public void setClinicInfo(String clinicInfo) {
this.clinicInfo = clinicInfo;
}

public String getEducation() {
return education;
}

public void setEducation(String education) {
this.education = education;
}

public String getExperience() {
return experience;
}

public void setExperience(String experience) {
this.experience = experience;
}

public String getAwards() {
return awards;
}

public void setAwards(String awards) {
this.awards = awards;
}

public String getIsApproved() {
return isApproved;
}

public void setIsApproved(String isApproved) {
this.isApproved = isApproved;
}

}