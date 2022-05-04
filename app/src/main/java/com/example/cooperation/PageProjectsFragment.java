package com.example.cooperation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cooperation.databinding.FragmentPageProjectsBinding;
import com.example.cooperation.databing.FragmentProjectsDataBinding;
import com.example.cooperation.viewmodel.RecyclerViewProjectsViewModel;


public class PageProjectsFragment extends Fragment {



    public PageProjectsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final FragmentPageProjectsBinding fragmentPageProjectsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_page_projects, container, false);

        FragmentProjectsDataBinding fragmentProjectsDataBinding = new FragmentProjectsDataBinding(this.getContext());

        fragmentPageProjectsBinding.setProjectsEventHandler(fragmentProjectsDataBinding);

        fragmentPageProjectsBinding.recyclerviewProjects.setLayoutManager(new LinearLayoutManager(this.getContext()));
        fragmentPageProjectsBinding.recyclerviewProjects.setHasFixedSize(true);

        SwipeRefreshLayout swipeRefreshLayout = fragmentPageProjectsBinding.getRoot().findViewById(R.id.refresh_project_list);

        swipeRefreshLayout.setColorSchemeResources(R.color.orange_brown,R.color.orange_brown_item,R.color.orange_brown_hint);

        RecyclerViewProjectsViewModel recyclerViewProjectsViewModel = new RecyclerViewProjectsViewModel(fragmentPageProjectsBinding,getContext(),swipeRefreshLayout);
        recyclerViewProjectsViewModel.refreshRecyclerViewItems();

        // 实现动态显示，优化可考虑本地缓存，而不进行网络请求
        AppCompatCheckBox projectTodo = fragmentPageProjectsBinding.getRoot().findViewById(R.id.project_status_todo);

        AppCompatCheckBox projectDoing = fragmentPageProjectsBinding.getRoot().findViewById(R.id.project_status_doing);

        AppCompatCheckBox projectDone = fragmentPageProjectsBinding.getRoot().findViewById(R.id.project_status_done);

        projectTodo.setOnCheckedChangeListener((compoundButton, b) -> recyclerViewProjectsViewModel.refreshRecyclerViewItems());

        projectDoing.setOnCheckedChangeListener((compoundButton, b) -> recyclerViewProjectsViewModel.refreshRecyclerViewItems());

        projectDone.setOnCheckedChangeListener((compoundButton, b) -> recyclerViewProjectsViewModel.refreshRecyclerViewItems());

        swipeRefreshLayout.setOnRefreshListener(() -> recyclerViewProjectsViewModel.refreshRecyclerViewItems());

        // Inflate the layout for this fragment
        return fragmentPageProjectsBinding.getRoot();
    }
}