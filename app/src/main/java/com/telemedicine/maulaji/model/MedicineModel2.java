package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineModel2 {

@SerializedName("id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("category")
@Expose
private String category;
@SerializedName("price")
@Expose
private String price;
@SerializedName("box")
@Expose
private String box;
@SerializedName("s_price")
@Expose
private String sPrice;
@SerializedName("quantity")
@Expose
private String quantity;
@SerializedName("generic")
@Expose
private String generic;
@SerializedName("company")
@Expose
private String company;
@SerializedName("effects")
@Expose
private String effects;
@SerializedName("e_date")
@Expose
private String eDate;
@SerializedName("add_date")
@Expose
private String addDate;
@SerializedName("hospital_id")
@Expose
private String hospitalId;

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

public String getCategory() {
return category;
}

public void setCategory(String category) {
this.category = category;
}

public String getPrice() {
return price;
}

public void setPrice(String price) {
this.price = price;
}

public String getBox() {
return box;
}

public void setBox(String box) {
this.box = box;
}

public String getSPrice() {
return sPrice;
}

public void setSPrice(String sPrice) {
this.sPrice = sPrice;
}

public String getQuantity() {
return quantity;
}

public void setQuantity(String quantity) {
this.quantity = quantity;
}

public String getGeneric() {
return generic;
}

public void setGeneric(String generic) {
this.generic = generic;
}

public String getCompany() {
return company;
}

public void setCompany(String company) {
this.company = company;
}

public String getEffects() {
return effects;
}

public void setEffects(String effects) {
this.effects = effects;
}

public String getEDate() {
return eDate;
}

public void setEDate(String eDate) {
this.eDate = eDate;
}

public String getAddDate() {
return addDate;
}

public void setAddDate(String addDate) {
this.addDate = addDate;
}

public String getHospitalId() {
return hospitalId;
}

public void setHospitalId(String hospitalId) {
this.hospitalId = hospitalId;
}

}