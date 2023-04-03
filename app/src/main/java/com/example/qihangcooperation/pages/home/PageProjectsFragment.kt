package com.example.qihangcooperation.pages.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.qihangcooperation.pages.ProjectDetailsActivity
import com.example.qihangcooperation.R
import com.example.qihangcooperation.adapter.*
import com.example.qihangcooperation.application.CooperationApplication
import com.example.qihangcooperation.base.BaseFragment
import com.example.qihangcooperation.constants.ProjectAndTaskStatus
import com.example.qihangcooperation.databinding.FragmentPageProjectsBinding
import com.example.qihangcooperation.pojo.Project
import com.example.qihangcooperation.util.ResponseHandler
import com.example.qihangcooperation.viewmodel.ProjectViewModel
import kotlinx.coroutines.launch


class PageProjectsFragment : BaseFragment<FragmentPageProjectsBinding>(FragmentPageProjectsBinding::inflate) {
    private lateinit var viewModel: ProjectViewModel
    private val username = CooperationApplication.getUser().username
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private lateinit var recyclerViewList: MutableList<Any>

    override fun onCreateView() {
        // set the viewModelStoreOwner of the viewModel to activity
        viewModel = ViewModelProvider(requireActivity())[ProjectViewModel::class.java]
        // set the swipeRefreshLayout
        initSwipeRefreshLayout()

        // get the projects by select
        getProjectsBySelect()

        // set the recyclerView click listener
        initRecyclerViewClickListener()
    }

    private fun initRecyclerViewClickListener(){
        viewBinding.recyclerviewProjects.setOnItemClickListener { _, position ->
            // get the project and jump to the project details page
            val project = recyclerViewList[position] as Project
            val bundle = Bundle()
            bundle.putSerializable("project", project)
            val intent = Intent(requireActivity(), ProjectDetailsActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        viewBinding.recyclerviewProjects.setOnItemLongClickListener { _, position ->
            val dialog = AlertDialog.Builder(requireContext())
            dialog.apply {
                setTitle(R.string.attention)
                setMessage(R.string.delete_message)
                // delete item
                setPositiveButton(R.string.delete_positive_button){ _, _ ->
                    val project = recyclerViewList[position] as Project
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.deleteProject(projectId = project.projectId).collect{
                            when (it){
                                is ProjectViewModel.ProjectAndTaskState.Success -> {
                                    recyclerViewAdapter?.notifyItemChanged(position)
                                }
                                is ProjectViewModel.ProjectAndTaskState.Failed -> {
                                    ResponseHandler.handleError(it.reason, requireActivity())
                                }
                                else -> {}
                            }
                        }
                    }
                }

                setNegativeButton(R.string.delete_negative_button){ _, _ ->
                    // do nothing
                }
            }.create().show()

        }
    }

    private fun initSwipeRefreshLayout(){
        viewBinding.refreshProjectList.apply {
            setColorSchemeResources(R.color.orange_brown, R.color.orange_brown_item, R.color.orange_brown_hint)
            setOnRefreshListener {
                isRefreshing = true
                getProjectsBySelect()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getProjectsBySelect(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getProjects(username).collect{ listProjects ->
                    when (listProjects) {
                        is ProjectViewModel.ProjectAndTaskState.Success -> {
                            viewBinding.refreshProjectList.isRefreshing = false
                            // check the checkBox and show pick the right status of project, finally set the adapter
                            val predicate: (Project) -> Boolean = {
                                val doing = viewBinding.projectStatusDoing.isChecked
                                val todo = viewBinding.projectStatusTodo.isChecked
                                val done = viewBinding.projectStatusDone.isChecked
                                (doing && it.projectStatus == ProjectAndTaskStatus.DOING.status) || (todo && it.projectStatus == ProjectAndTaskStatus.TODO.status) || (done && it.projectStatus == ProjectAndTaskStatus.DONE.status)
                            }
                            val filterRes = listProjects.res.filter(predicate)
                            recyclerViewList = mutableListOf(filterRes)
                            val projectProxy = ProjectRecyclerViewProxy()
                            val proxyList = mutableListOf<RVProxy<*, *>>(projectProxy)
                            if (recyclerViewAdapter != null) {
                                recyclerViewAdapter!!.notifyDataSetChanged()
                            }else {
                                recyclerViewAdapter = RecyclerViewAdapter(dataList = recyclerViewList, proxyList = proxyList)
                                viewBinding.recyclerviewProjects.adapter = recyclerViewAdapter
                            }
                        }
                        is ProjectViewModel.ProjectAndTaskState.Failed -> {
                            viewBinding.refreshProjectList.isRefreshing = false
                            ResponseHandler.handleError(listProjects.reason, requireActivity())
                        }
                        is ProjectViewModel.ProjectAndTaskState.Loading -> {
                            viewBinding.refreshProjectList.isRefreshing = true
                        }
                    }
                }
            }
        }
    }
}