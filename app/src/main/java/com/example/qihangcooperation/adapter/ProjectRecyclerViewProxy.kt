package com.example.qihangcooperation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qihangcooperation.R
import com.example.qihangcooperation.pojo.Project

class ProjectRecyclerViewProxy: RVProxy<Project, ProjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_project_item, parent, false).let {
            return ProjectViewHolder(it)
        }
    }

    override fun onBindViewHolder(
        holder: ProjectViewHolder,
        data: Project,
        index: Int,
        action: ((Any?) -> Unit)?
    ) {
        holder.apply {
            projectName.text = data.projectName
            projectInviteCode.text = data.inviteCode
            projectStatus.text = data.projectStatus
            projectCreator.text = data.projectManager?.username ?: "未知"
            createTime.text = data.projectUpdatedDate.toString()
            description.text = data.projectDescription
        }
    }

}

class ProjectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var projectName: TextView
    var projectInviteCode: TextView
    var projectStatus: TextView
    var projectCreator: TextView
    var createTime: TextView
    var description: TextView

    init {
        projectName = itemView.findViewById(R.id.recyclerview_projects_project_name)
        projectInviteCode = itemView.findViewById(R.id.recyclerview_projects_invitation_code)
        projectStatus = itemView.findViewById(R.id.recyclerview_projects_status)
        projectCreator = itemView.findViewById(R.id.recyclerview_projects_creator)
        createTime = itemView.findViewById(R.id.recyclerview_projects_create_time)
        description = itemView.findViewById(R.id.recyclerview_projects_description)
    }
}