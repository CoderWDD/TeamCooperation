package com.example.cooperation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cooperation.databinding.FragmentPageProjectsBinding;
import com.example.cooperation.databing.FragmentProjectsDataBinding;
import com.example.cooperation.databing.adapter.RecyclerViewAdapterForProjects;
import com.example.cooperation.viewmodel.RecyclerViewProjectsViewModel;


public class PageProjectsFragment extends Fragment {



    public PageProjectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentPageProjectsBinding fragmentPageProjectsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_page_projects, container, false);

        FragmentProjectsDataBinding fragmentProjectsDataBinding = new FragmentProjectsDataBinding(this.getContext());

        fragmentPageProjectsBinding.setProjectsEventHandler(fragmentProjectsDataBinding);

        fragmentPageProjectsBinding.recyclerviewProjects.setLayoutManager(new LinearLayoutManager(this.getContext()));
        fragmentPageProjectsBinding.recyclerviewProjects.setHasFixedSize(true);

        RecyclerViewAdapterForProjects recyclerViewAdapterForProjects = new RecyclerViewAdapterForProjects(new RecyclerViewProjectsViewModel().getProjects());

        fragmentPageProjectsBinding.recyclerviewProjects.setAdapter(recyclerViewAdapterForProjects);

        // Inflate the layout for this fragment
        return fragmentPageProjectsBinding.getRoot();
    }
}