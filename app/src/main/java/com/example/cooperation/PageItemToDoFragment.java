package com.example.cooperation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cooperation.databinding.FragmentPageItemToDoBinding;
import com.example.cooperation.databing.adapter.RecyclerViewAdapterForTodo;
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

        RecyclerViewAdapterForTodo recyclerViewAdapterForTodo = new RecyclerViewAdapterForTodo(new RecyclerViewTodoViewModel().getItems());

        fragmentPageItemToDoBinding.recyclerviewTodo.setAdapter(recyclerViewAdapterForTodo);

        // TODO 将网络地址设置进itemImage

        return fragmentPageItemToDoBinding.getRoot();
    }
}