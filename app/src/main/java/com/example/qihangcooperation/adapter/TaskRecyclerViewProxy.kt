package com.example.qihangcooperation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qihangcooperation.R
import com.example.qihangcooperation.databinding.RecyclerviewTaskItemBinding
import com.example.qihangcooperation.pojo.Task

class TaskRecyclerViewProxy: RVProxy<Task, TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = RecyclerviewTaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(itemBinding)
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

class TaskViewHolder(itemView: RecyclerviewTaskItemBinding): RecyclerView.ViewHolder(itemView.root) {
    var taskName: TextView
    var taskStatus: TextView
    var taskOwner: TextView
    var startTime: TextView
    var endTime: TextView
    var description: TextView

    init {
        taskName = itemView.recyclerviewTodoItemItemName
        taskStatus = itemView.recyclerviewTodoItemStatus
        taskOwner = itemView.recyclerviewTodoItemCreator
        startTime = itemView.recyclerviewTodoItemStartTime
        endTime = itemView.recyclerviewTodoItemEndTime
        description = itemView.recyclerviewTodoItemDescription
    }
}