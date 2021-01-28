package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedHModel {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("title")
@Expose
private String name;
@SerializedName("isSelected")
@Expose
private Integer isSelected;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getIsSelected() {
return isSelected;
}

public void setIsSelected(Integer isSelected) {
this.isSelected = isSelected;
}

}