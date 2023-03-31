package com.example.qihangcooperation.pages.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.qihangcooperation.base.BaseFragment
import com.example.qihangcooperation.databinding.FragmentPageItemToDoBinding
import com.example.qihangcooperation.viewmodel.ProjectViewModel


class PageItemToDoFragment : BaseFragment<FragmentPageItemToDoBinding>(FragmentPageItemToDoBinding::inflate) {
    private lateinit var viewModel: ProjectViewModel
    override fun onCreateView() {
        // set the viewModelStoreOwner of the viewModel to activity
        viewModel = ViewModelProvider(requireActivity())[ProjectViewModel::class.java]
    }
}