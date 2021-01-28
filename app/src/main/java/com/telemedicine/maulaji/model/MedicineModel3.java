package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineModel3 {

@SerializedName("id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("vendor")
@Expose
private String vendor;
@SerializedName("price")
@Expose
private String price;
@SerializedName("image")
@Expose
private String image;
@SerializedName("product_type")
@Expose
private String productType;
@SerializedName("description")
@Expose
private String description;

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

public String getVendor() {
return vendor;
}

public void setVendor(String vendor) {
this.vendor = vendor;
}

public String getPrice() {
return price;
}

public void setPrice(String price) {
this.price = price;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getProductType() {
return productType;
}

public void setProductType(String productType) {
this.productType = productType;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

}