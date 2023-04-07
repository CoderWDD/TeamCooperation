package com.example.qihangcooperation.pages

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.qihangcooperation.R
import com.example.qihangcooperation.application.CooperationApplication
import com.example.qihangcooperation.base.BaseActivity
import com.example.qihangcooperation.constants.ProjectAndTaskStatus
import com.example.qihangcooperation.databinding.ActivityProjectDetailsBinding
import com.example.qihangcooperation.pojo.Project
import com.example.qihangcooperation.pojo.User
import com.example.qihangcooperation.util.ResponseHandler
import com.example.qihangcooperation.viewmodel.ProjectViewModel
import kotlinx.coroutines.launch

class ProjectDetailsActivity : BaseActivity<ActivityProjectDetailsBinding>(ActivityProjectDetailsBinding::inflate) {
    private lateinit var viewModel: ProjectViewModel
    private var project: Project? = null
    private val username = CooperationApplication.getUser().username
    override fun onCreate() {
        viewModel = ViewModelProvider(this)[ProjectViewModel::class.java]
        project = intent.extras?.getSerializable("project") as Project?
        initProjectView()
        initClickListener()
    }

    private fun initProjectView(){
        viewBinding.apply {
            projectDetailsCreateTime.text = project?.projectUpdatedDate.toString()
            projectName.setText(project?.projectName)
            projectDetailsDescription.setText(project?.projectDescription)
            projectDetailsInvitationCode.text = project?.inviteCode
            projectDetailsCreator.text = project?.projectManager?.username
            projectDetailsCooperators.text = getProjectCoordinatorString()
        }

        // 配置Status Spinner
        val spinnerStatus = viewBinding.spinnerProjectStatus
        val stringArray = resources.getStringArray(R.array.status)
        val adapterStatus = ArrayAdapter(this, R.layout.status_spinner_text, stringArray)
        adapterStatus.setDropDownViewResource(R.layout.status_spinner_text)
        spinnerStatus.adapter = adapterStatus

        // 设置默认的status为当前item的status
        val position = adapterStatus.getPosition(project?.projectStatus ?: ProjectAndTaskStatus.TODO.status)
        spinnerStatus.setSelection(position)
    }

    private fun initClickListener(){
        viewBinding.projectDetailsModifyButton.setOnClickListener {
            val projectStatus: String = getProjectStatus()
            val projectCoordinator: Set<User>? = getProjectCoordinator()
            val project = Project(
                projectId = project?.projectId ?: 0,
                projectName = viewBinding.projectName.text.toString(),
                projectDescription = viewBinding.projectDetailsDescription.text.toString(),
                projectManager = CooperationApplication.getUser(),
                projectStatus = projectStatus,
                inviteCode = viewBinding.projectDetailsInvitationCode.text.toString(),
                user = projectCoordinator
            )
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.updateProject(projectId = project.projectId ?: 0, project = project).collect{
                        when (it){
                            is ProjectViewModel.ProjectAndTaskState.Success -> {
                                ResponseHandler.handleSuccess("修改成功", this@ProjectDetailsActivity)
                                finish()
                            }
                            is ProjectViewModel.ProjectAndTaskState.Failed -> {
                                ResponseHandler.handleError(it.reason, this@ProjectDetailsActivity)
                            }
                            else -> {}
                        }
                    }
                }
            }
        }

        viewBinding.projectDetailsAddButton.setOnClickListener {
            val intent = Intent(this, ProjectAddItemActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("project", project)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun getProjectStatus(): String = viewBinding.spinnerProjectStatus.selectedItem.toString()

    private fun getProjectCoordinator(): Set<User>? = project?.user

    private fun getProjectCoordinatorString(): String {
        val projectCoordinator = project?.user
        var projectCoordinatorString = ""
        if (projectCoordinator != null) {
            for (user in projectCoordinator) {
                projectCoordinatorString += user.username + ","
            }
        }
        return projectCoordinatorString
    }
}