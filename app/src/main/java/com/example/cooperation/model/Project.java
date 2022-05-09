package com.example.cooperation.model;

import java.io.Serializable;
import java.util.List;

public class Project implements Serializable {
    private int projectId;

    private String status;

    private String projectName;

    private String projectTime;

    private String description;

    private String invitationCode;

    private String author;

    private int priority;

    private List<String> cooperatorsList;

    public List<String> getCooperatorsList() {
        return cooperatorsList;
    }

    public void setCooperatorsList(List<String> cooperatorsList) {
        this.cooperatorsList = cooperatorsList;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

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

    public String getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(String projectTime) {
        this.projectTime = projectTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String creator) {
        this.author = creator;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
