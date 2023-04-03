package com.example.qihangcooperation.pages.home

import androidx.lifecycle.ViewModelProvider
import com.example.qihangcooperation.application.CooperationApplication
import com.example.qihangcooperation.base.BaseFragment
import com.example.qihangcooperation.databinding.FragmentPageProfileBinding
import com.example.qihangcooperation.viewmodel.UserViewModel


class PageProfileFragment : BaseFragment<FragmentPageProfileBinding>(FragmentPageProfileBinding::inflate) {
    private lateinit var viewModel: UserViewModel
    override fun onCreateView() {
        viewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        viewBinding.profileUsername.text = CooperationApplication.getUser().username
        viewBinding.profileDescription.text = CooperationApplication.getUser().nickname
    }
}