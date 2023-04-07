package com.example.qihangcooperation.pages

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.qihangcooperation.base.BaseActivity
import com.example.qihangcooperation.databinding.ActivityTaskDetailsBinding
import com.example.qihangcooperation.pojo.Task
import com.example.qihangcooperation.util.ResponseHandler
import com.example.qihangcooperation.viewmodel.ProjectViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TaskDetailsActivity : BaseActivity<ActivityTaskDetailsBinding>(ActivityTaskDetailsBinding::inflate) {
    private lateinit var task: Task
    private var projectId: Long = 0
    private lateinit var viewModel: ProjectViewModel
    override fun onCreate() {
        viewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        intent.extras?.getSerializable("task")?.let { task = it as Task }

        initTaskView()
        initClickListener()
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

    private fun initClickListener(){
        viewBinding.taskDetailsModifyButton.setOnClickListener {
            val newTask = Task(
                taskId = task.taskId,
                taskName = viewBinding.taskDetailsName.text.toString(),
                description = viewBinding.taskDetailsDescription.text.toString(),
                startTime = task.startTime,
                endTime = task.endTime,
                taskOwner = task.taskOwner,
                status = viewBinding.spinnerItemStatus.selectedItem.toString()
            )
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.updateTask(projectId, taskId = task.taskId ?: 0, task = newTask).collect{
                        when (it) {
                            is ProjectViewModel.ProjectAndTaskState.Success -> {
                                ResponseHandler.handleSuccess("修改成功", this@TaskDetailsActivity)
                                delay(500)
                                finish()
                            }
                            is ProjectViewModel.ProjectAndTaskState.Failed -> {
                                ResponseHandler.handleError(it.reason, this@TaskDetailsActivity)
                            }
                            is ProjectViewModel.ProjectAndTaskState.Loading -> {}
                        }
                    }
                }
            }
        }
    }
}