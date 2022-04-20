package com.example.cooperation.viewmodel;

import com.example.cooperation.model.Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecyclerViewProjectsViewModel {
    public List<Project> getProjects(){
        List<Project> projectList = new ArrayList<>();

        // TODO 网络请求，获取projects列表

//        private int projectId;
//
//        private String status;
//
//        private String projectName;
//
//        private Date projectTime;
//
//        private String description;
//
//        private String invitationCode;
//
//        private String creator;

        Project project = new Project();
        project.setProjectId(12);
        project.setProjectName("Help");
        project.setProjectTime(new Date());
        project.setCreator("吴某人");
        project.setDescription("nothing");
        project.setStatus("Done");
        project.setInvitationCode("asdasd");


        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        return projectList;
    }
}
