package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalModel {

@SerializedName("id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("address")
@Expose
private String address;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("package")
@Expose
private String _package;
@SerializedName("p_limit")
@Expose
private String pLimit;
@SerializedName("d_limit")
@Expose
private String dLimit;
@SerializedName("module")
@Expose
private String module;
@SerializedName("ion_user_id")
@Expose
private String ionUserId;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
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

public String getPackage() {
return _package;
}

public void setPackage(String _package) {
this._package = _package;
}

public String getPLimit() {
return pLimit;
}

public void setPLimit(String pLimit) {
this.pLimit = pLimit;
}

public String getDLimit() {
return dLimit;
}

public void setDLimit(String dLimit) {
this.dLimit = dLimit;
}

public String getModule() {
return module;
}

public void setModule(String module) {
this.module = module;
}

public String getIonUserId() {
return ionUserId;
}

public void setIonUserId(String ionUserId) {
this.ionUserId = ionUserId;
}

}