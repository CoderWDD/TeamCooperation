package com.example.cooperation.model;

import java.util.Date;

public class ProjectModifyInfo {
    private String status;

    private String projectName;

    private Date projectTime;

    private String description;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(Date projectTime) {
        this.projectTime = projectTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
