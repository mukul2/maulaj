package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorLoginModel {

@SerializedName("status")
@Expose
private Boolean status;
@SerializedName("message")
@Expose
private String message;
@SerializedName("access_token")
@Expose
private Object accessToken;
@SerializedName("user_info")
@Expose
private UserInfoDoc userInfo;

public Boolean getStatus() {
return status;
}

public void setStatus(Boolean status) {
this.status = status;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Object getAccessToken() {
return accessToken;
}

public void setAccessToken(Object accessToken) {
this.accessToken = accessToken;
}

public UserInfoDoc getUserInfo() {
return userInfo;
}

public void setUserInfo(UserInfoDoc userInfo) {
this.userInfo = userInfo;
}

}