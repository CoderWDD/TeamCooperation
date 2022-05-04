package com.example.cooperation.model;

public class ItemListResponseBody {
    private String code;
    private String message;
    private ItemAdd[] data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ItemAdd[] getData() {
        return data;
    }

    public void setData(ItemAdd[] data) {
        this.data = data;
    }
}
