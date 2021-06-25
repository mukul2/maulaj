package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorModelRaw {

@SerializedName("id")
@Expose
private String id;
@SerializedName("img_url")
@Expose
private String imgUrl;
@SerializedName("name")
@Expose
private String name;
@SerializedName("experience")
@Expose
private String experience;
@SerializedName("last_seen")
@Expose
private String lastSeen;@SerializedName("gender")
@Expose
private String gender;
@SerializedName("email")
@Expose
private String email;
@SerializedName("department_id")
@Expose
private String departmentId;
@SerializedName("address")
@Expose
private String address;
@SerializedName("phone")
@Expose
private String phone;@SerializedName("specialization")
@Expose
private String specialization;
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
@SerializedName("education")
@Expose
private String education;
@SerializedName("about_me")
@Expose
private String about_me;
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
private Object identitydoc;
@SerializedName("doctor_lic_doc")
@Expose
private Object doctorLicDoc;
@SerializedName("country")
@Expose
private String country;
@SerializedName("city")
@Expose
private String city;

@SerializedName("awards")
@Expose
private String awards;

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

public Object getIdentitydoc() {
return identitydoc;
}

public void setIdentitydoc(Object identitydoc) {
this.identitydoc = identitydoc;
}

public Object getDoctorLicDoc() {
return doctorLicDoc;
}

public void setDoctorLicDoc(Object doctorLicDoc) {
this.doctorLicDoc = doctorLicDoc;
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

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}