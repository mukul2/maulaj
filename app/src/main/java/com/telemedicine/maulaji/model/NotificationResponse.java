package com.telemedicine.maulaji.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationResponse {

@SerializedName("message_id")
@Expose
private Integer messageId;

public Integer getMessageId() {
return messageId;
}

public void setMessageId(Integer messageId) {
this.messageId = messageId;
}

}