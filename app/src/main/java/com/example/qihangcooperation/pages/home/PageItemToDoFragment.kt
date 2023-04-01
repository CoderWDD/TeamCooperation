package com.example.qihangcooperation.pages.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.qihangcooperation.R
import com.example.qihangcooperation.application.CooperationApplication
import com.example.qihangcooperation.base.BaseFragment
import com.example.qihangcooperation.databinding.FragmentPageItemToDoBinding
import com.example.qihangcooperation.util.ResponseHandler
import com.example.qihangcooperation.viewmodel.ProjectViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.xml.sax.ErrorHandler


class PageItemToDoFragment : BaseFragment<FragmentPageItemToDoBinding>(FragmentPageItemToDoBinding::inflate) {
    private val username = CooperationApplication.getUser().username
    private lateinit var viewModel: ProjectViewModel
    override fun onCreateView() {
        // set the viewModelStoreOwner of the viewModel to activity
        viewModel = ViewModelProvider(requireActivity())[ProjectViewModel::class.java]

        // set the swipeRefreshLayout
        initSwipeRefreshLayout()

    }

    private fun initSwipeRefreshLayout(){
        viewBinding.refreshItemList.apply {
            setColorSchemeResources(R.color.orange_brown, R.color.orange_brown_item,R.color.orange_brown_hint)
            setOnRefreshListener {
                isRefreshing = true
                getProjectsBySelect()
            }
        }
    }

    private fun getProjectsBySelect(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getProjects(username).collect{
                    when (it) {
                        is ProjectViewModel.ProjectState.Success -> {
                            viewBinding.refreshItemList.isRefreshing = false
                            // TODO: 2023/4/1 0001 check the checkBox and show pick the right status of project, finally set the adapter
                        }
                        is ProjectViewModel.ProjectState.Failed -> {
                            viewBinding.refreshItemList.isRefreshing = false
                            ResponseHandler.handleError(it.reason, requireActivity())
                        }
                        is ProjectViewModel.ProjectState.Loading -> {
                            viewBinding.refreshItemList.isRefreshing = true
                        }
                    }
                }
            }
        }
    }
}