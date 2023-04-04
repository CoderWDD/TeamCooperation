package com.example.qihangcooperation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qihangcooperation.R
import com.example.qihangcooperation.databinding.RecyclerviewProjectItemBinding
import com.example.qihangcooperation.pojo.Project

class ProjectRecyclerViewProxy: RVProxy<Project, ProjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = RecyclerviewProjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(view)
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

class ProjectViewHolder(itemView: RecyclerviewProjectItemBinding): RecyclerView.ViewHolder(itemView.root) {
    var projectName: TextView
    var projectInviteCode: TextView
    var projectStatus: TextView
    var projectCreator: TextView
    var createTime: TextView
    var description: TextView

    init {
        projectName = itemView.recyclerviewProjectsProjectName
        projectInviteCode = itemView.recyclerviewProjectsInvitationCode
        projectStatus = itemView.recyclerviewProjectsStatus
        projectCreator = itemView.recyclerviewProjectsCreator
        createTime = itemView.recyclerviewProjectsCreateTime
        description = itemView.recyclerviewProjectsDescription
    }
}