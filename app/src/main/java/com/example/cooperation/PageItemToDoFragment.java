package com.example.cooperation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cooperation.databinding.FragmentPageItemToDoBinding;
import com.example.cooperation.viewmodel.RecyclerViewTodoViewModel;


public class PageItemToDoFragment extends Fragment {

    public PageItemToDoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPageItemToDoBinding fragmentPageItemToDoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_page_item_to_do,container,false);
        fragmentPageItemToDoBinding.recyclerviewTodo.setLayoutManager(new LinearLayoutManager(this.getContext()));
        fragmentPageItemToDoBinding.recyclerviewTodo.setHasFixedSize(true);

        SwipeRefreshLayout swipeRefreshLayout = fragmentPageItemToDoBinding.getRoot().findViewById(R.id.refresh_item_list);

        swipeRefreshLayout.setColorSchemeResources(R.color.orange_brown,R.color.orange_brown_item,R.color.orange_brown_hint);

        RecyclerViewTodoViewModel recyclerViewTodoViewModel = new RecyclerViewTodoViewModel(getContext(),fragmentPageItemToDoBinding, swipeRefreshLayout);
        recyclerViewTodoViewModel.refreshRecyclerViewItems();

        AppCompatCheckBox itemTodo = fragmentPageItemToDoBinding.getRoot().findViewById(R.id.item_status_todo);

        AppCompatCheckBox itemDoing = fragmentPageItemToDoBinding.getRoot().findViewById(R.id.item_status_doing);

        AppCompatCheckBox itemDone = fragmentPageItemToDoBinding.getRoot().findViewById(R.id.item_status_done);

        itemTodo.setOnCheckedChangeListener((compoundButton, b) -> recyclerViewTodoViewModel.refreshRecyclerViewItems());

        itemDoing.setOnCheckedChangeListener((compoundButton, b) -> recyclerViewTodoViewModel.refreshRecyclerViewItems());

        itemDone.setOnCheckedChangeListener((compoundButton, b) -> recyclerViewTodoViewModel.refreshRecyclerViewItems());


        swipeRefreshLayout.setOnRefreshListener(() -> recyclerViewTodoViewModel.refreshRecyclerViewItems());

        return fragmentPageItemToDoBinding.getRoot();
    }
}