package com.example.qihangcooperation.pages

import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.qihangcooperation.R
import com.example.qihangcooperation.application.CooperationApplication
import com.example.qihangcooperation.base.BaseActivity
import com.example.qihangcooperation.databinding.ActivityProjectAddItemBinding
import com.example.qihangcooperation.pojo.Project
import com.example.qihangcooperation.pojo.Task
import com.example.qihangcooperation.util.ResponseHandler
import com.example.qihangcooperation.viewmodel.ProjectViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ProjectAddItemActivity : BaseActivity<ActivityProjectAddItemBinding>(ActivityProjectAddItemBinding::inflate) {
    private val username = CooperationApplication.getUser().username
    private lateinit var project: Project
    private lateinit var viewModel: ProjectViewModel
    override fun onCreate() {
        viewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        val bundle = intent.extras
        project = bundle!!.getSerializable("project") as Project

        initTaskView()
        initClickListener()
    }

    private fun initTaskView(){
        // 配置Status Spinner
        val spinnerStatus = viewBinding.spinnerStatus
        val statusArray = resources.getStringArray(R.array.status)
        val adapterStatus = ArrayAdapter(this, R.layout.status_spinner_text, statusArray)
        adapterStatus.setDropDownViewResource(R.layout.status_spinner_text)
        spinnerStatus.adapter = adapterStatus

        // 配置Priority Spinner
        val spinnerItemPriority = viewBinding.spinnerItemAddPriority
        val priorityArray = resources.getStringArray(R.array.priority)
        val adapterPriority = ArrayAdapter(this, R.layout.status_spinner_text, priorityArray)
        adapterPriority.setDropDownViewResource(R.layout.status_spinner_text)
        spinnerItemPriority.adapter = adapterPriority

        // 配置Cooperator Spinner
        val cooperator = getProjectCooperators()
        val spinnerItemCooperator = viewBinding.spinnerExecutor
        val adapterCooperators = ArrayAdapter(this, R.layout.status_spinner_text, cooperator)
        adapterCooperators.setDropDownViewResource(R.layout.status_spinner_text)
        spinnerItemCooperator.adapter = adapterCooperators

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val time = simpleDateFormat.format(Date())
        viewBinding.apply {
            taskCreator.text = username
            taskCreateTime.text = time
        }
    }

    private fun initClickListener(){
        viewBinding.buttonItemAdd.setOnClickListener {
            val taskName = viewBinding.taskName.text.toString()
            val taskDescription = viewBinding.taskDescription.text.toString()
            val taskStatus = viewBinding.spinnerStatus.selectedItem.toString()
            val taskExecutor = viewBinding.spinnerExecutor.selectedItem.toString()
            val owner = project.user?.filter { it.username == taskExecutor }?.toList() ?: listOf()
            val task = Task(
                taskName = taskName,
                description = taskDescription,
                status = taskStatus,
                taskOwner = owner[0],
            )
            lifecycleScope.launch{
                viewModel.createTask(projectId = project.projectId ?: 0, task = task).collect{
                    when (it) {
                        is ProjectViewModel.ProjectAndTaskState.Success -> {
                            ResponseHandler.handleSuccess("创建任务成功", this@ProjectAddItemActivity)
                            delay(500)
                            finish()
                        }
                        is ProjectViewModel.ProjectAndTaskState.Failed -> {
                            ResponseHandler.handleError(it.reason, this@ProjectAddItemActivity)
                        }
                        is ProjectViewModel.ProjectAndTaskState.Loading -> {}
                    }
                }
            }
        }
    }

    private fun getProjectCooperators(): List<String> = project.user?.map { username }?.toList() ?: listOf()
}