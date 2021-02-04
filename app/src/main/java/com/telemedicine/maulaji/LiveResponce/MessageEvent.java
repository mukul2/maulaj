package com.telemedicine.maulaji.LiveResponce;

public class MessageEvent {
    private String message;
    private boolean isSuccess = false;

    public MessageEvent() {
    }

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}