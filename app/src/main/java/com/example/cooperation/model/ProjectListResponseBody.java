package com.example.cooperation.model;

public class ProjectListResponseBody {
    private String code;
    private String message;
    private Project[] data;

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

    public Project[] getData() {
        return data;
    }

    public void setData(Project[] data) {
        this.data = data;
    }
}
