package com.example.qihangcooperation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qihangcooperation.R
import com.example.qihangcooperation.pojo.Task

class TaskRecyclerViewProxy: RVProxy<Task, TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_task_item, parent, false).let {
            return ProjectViewHolder(it)
        }
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        data: Task,
        index: Int,
        action: ((Any?) -> Unit)?
    ) {
        holder.apply {
            taskName.text = data.taskName
            taskStatus.text = data.status
            taskOwner.text = data.taskOwner.username
            startTime.text = data.startTime.toString()
            endTime.text = data.endTime.toString()
            description.text = data.description
        }
    }

}

class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var taskName: TextView
    var taskStatus: TextView
    var taskOwner: TextView
    var startTime: TextView
    var endTime: TextView
    var description: TextView

    init {
        taskName = itemView.findViewById(R.id.recyclerview_todo_item_item_name)
        taskStatus = itemView.findViewById(R.id.recyclerview_todo_item_status)
        taskOwner = itemView.findViewById(R.id.recyclerview_todo_item_creator)
        startTime = itemView.findViewById(R.id.recyclerview_todo_item_start_time)
        endTime = itemView.findViewById(R.id.recyclerview_todo_item_end_time)
        description = itemView.findViewById(R.id.recyclerview_todo_item_description)
    }
}