package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityModel {

@SerializedName("id")
@Expose
private String id;
@SerializedName("city")
@Expose
private String city;
@SerializedName("city_ascii")
@Expose
private String cityAscii;
@SerializedName("country")
@Expose
private String country;
@SerializedName("iso2")
@Expose
private String iso2;
@SerializedName("iso3")
@Expose
private String iso3;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getCityAscii() {
return cityAscii;
}

public void setCityAscii(String cityAscii) {
this.cityAscii = cityAscii;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

public String getIso2() {
return iso2;
}

public void setIso2(String iso2) {
this.iso2 = iso2;
}

public String getIso3() {
return iso3;
}

public void setIso3(String iso3) {
this.iso3 = iso3;
}

}