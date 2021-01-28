package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryModel {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("iso")
@Expose
private String iso;
@SerializedName("name")
@Expose
private String name;
@SerializedName("nicename")
@Expose
private String nicename;
@SerializedName("iso3")
@Expose
private String iso3;
@SerializedName("numcode")
@Expose
private String numcode;
@SerializedName("phonecode")
@Expose
private String phonecode;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getIso() {
return iso;
}

public void setIso(String iso) {
this.iso = iso;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getNicename() {
return nicename;
}

public void setNicename(String nicename) {
this.nicename = nicename;
}

public String getIso3() {
return iso3;
}

public void setIso3(String iso3) {
this.iso3 = iso3;
}

public String getNumcode() {
return numcode;
}

public void setNumcode(String numcode) {
this.numcode = numcode;
}

public String getPhonecode() {
return phonecode;
}

public void setPhonecode(String phonecode) {
this.phonecode = phonecode;
}

}