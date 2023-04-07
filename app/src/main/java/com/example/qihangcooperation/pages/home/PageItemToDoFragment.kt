package com.example.qihangcooperation.pages.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.*
import com.example.qihangcooperation.pages.ProjectDetailsActivity
import com.example.qihangcooperation.R
import com.example.qihangcooperation.adapter.*
import com.example.qihangcooperation.application.CooperationApplication
import com.example.qihangcooperation.base.BaseFragment
import com.example.qihangcooperation.constants.ProjectAndTaskStatus
import com.example.qihangcooperation.databinding.FragmentPageItemToDoBinding
import com.example.qihangcooperation.pojo.Project
import com.example.qihangcooperation.pojo.Task
import com.example.qihangcooperation.util.ResponseHandler
import com.example.qihangcooperation.viewmodel.ProjectViewModel
import kotlinx.coroutines.launch


class PageItemToDoFragment : BaseFragment<FragmentPageItemToDoBinding>(FragmentPageItemToDoBinding::inflate) {
    private val username = CooperationApplication.getUser().username
    private lateinit var viewModel: ProjectViewModel
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private lateinit var recyclerViewList: MutableList<Any>

    override fun onCreateView() {
        // set the viewModelStoreOwner of the viewModel to activity
        viewModel = ViewModelProvider(requireActivity())[ProjectViewModel::class.java]

        // set the swipeRefreshLayout
        initSwipeRefreshLayout()

        // get the projects by select
        getTasksBySelect()

        // set the recyclerView click listener
        initRecyclerViewClickListener()
    }

    private fun initRecyclerViewClickListener(){
        viewBinding.recyclerviewTodo.setOnItemClickListener { _, position ->
            // get the project and jump to the project details page
            val taskP = recyclerViewList[position] as Task
            val bundle = Bundle()
            bundle.putSerializable("task", taskP)
            // todo add projectID
//            bundle.putLong("projectID", )
            val intent = Intent(requireActivity(), ProjectDetailsActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        viewBinding.recyclerviewTodo.setOnItemLongClickListener { _, position ->
            val dialog = AlertDialog.Builder(requireContext())
            dialog.apply {
                setTitle(R.string.attention)
                setMessage(R.string.delete_message)
                // delete item
                setPositiveButton(R.string.delete_positive_button){ _, _ ->
                    val task = recyclerViewList[position] as Task
                    viewLifecycleOwner.lifecycleScope.launch {
                        task.taskId?.let { taskID ->
                            viewModel.deleteTask(taskId = taskID).collect{
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
                }

                setNegativeButton(R.string.delete_negative_button){ _, _ ->
                    // do nothing
                }
            }.create().show()

        }
    }

    private fun initSwipeRefreshLayout(){
        viewBinding.refreshItemList.apply {
            setColorSchemeResources(R.color.orange_brown, R.color.orange_brown_item, R.color.orange_brown_hint)
            setOnRefreshListener {
                isRefreshing = true
                getTasksBySelect()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getTasksBySelect(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                // todo get the tasks by project list
                viewModel.getAllTaskByUser().collect{ listTasks ->
                    when (listTasks) {
                        is ProjectViewModel.ProjectAndTaskState.Success -> {
                            viewBinding.refreshItemList.isRefreshing = false
                            // check the checkBox and show pick the right status of project, finally set the adapter
                            val predicate: (Task) -> Boolean = {
                                val doing = viewBinding.itemStatusDoing.isChecked
                                val todo = viewBinding.itemStatusTodo.isChecked
                                val done = viewBinding.itemStatusDone.isChecked
                                (doing && it.status == ProjectAndTaskStatus.DOING.status) || (todo && it.status == ProjectAndTaskStatus.TODO.status) || (done && it.status == ProjectAndTaskStatus.DONE.status)
                            }
                            val filterRes = listTasks.res.filter(predicate)
                            recyclerViewList = filterRes.toMutableList()
                            val taskProxy = TaskRecyclerViewProxy()
                            val proxyList = mutableListOf<RVProxy<*, *>>(taskProxy)
                            if (recyclerViewAdapter != null) {
                                recyclerViewAdapter!!.notifyDataSetChanged()
                            }else {
                                recyclerViewAdapter = RecyclerViewAdapter(dataList = recyclerViewList, proxyList = proxyList)
                                viewBinding.recyclerviewTodo.adapter = recyclerViewAdapter
                            }
                        }
                        is ProjectViewModel.ProjectAndTaskState.Failed -> {
                            viewBinding.refreshItemList.isRefreshing = false
                            ResponseHandler.handleError(listTasks.reason, requireActivity())
                        }
                        is ProjectViewModel.ProjectAndTaskState.Loading -> {
                            viewBinding.refreshItemList.isRefreshing = true
                        }
                    }
                }
            }
        }
    }
}