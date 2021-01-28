package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineVaritiModel {

@SerializedName("body")
@Expose
private String body;
@SerializedName("medicine_id")
@Expose
private Integer medicineId;
@SerializedName("id")
@Expose
private Integer id;

public String getBody() {
return body;
}

public void setBody(String body) {
this.body = body;
}

public Integer getMedicineId() {
return medicineId;
}

public void setMedicineId(Integer medicineId) {
this.medicineId = medicineId;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

}