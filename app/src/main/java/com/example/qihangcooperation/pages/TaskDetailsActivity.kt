package com.example.qihangcooperation.pages

import androidx.lifecycle.ViewModelProvider
import com.example.qihangcooperation.base.BaseActivity
import com.example.qihangcooperation.databinding.ActivityTaskDetailsBinding
import com.example.qihangcooperation.pojo.Task
import com.example.qihangcooperation.viewmodel.ProjectViewModel

class TaskDetailsActivity : BaseActivity<ActivityTaskDetailsBinding>(ActivityTaskDetailsBinding::inflate) {
    private lateinit var task: Task
    private lateinit var viewModel: ProjectViewModel
    override fun onCreate() {
        viewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        intent.extras?.get("task")?.let { task = it as Task }

        initTaskView()
    }

    private fun initTaskView(){
        viewBinding.apply {
            taskDetailsName.setText(task.taskName)
            taskDetailsDescription.setText(task.description)
            taskDetailsCreateTime.text = task.startTime.toString()
            taskOwner.text = task.taskOwner.username
            taskDetailsCreator.text = task.taskOwner.username
        }
    }
}