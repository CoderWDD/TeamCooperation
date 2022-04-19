package com.example.cooperation.databing.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooperation.R;
import com.example.cooperation.databinding.RecyclerviewItemTodoLayoutBinding;
import com.example.cooperation.model.ItemAdd;

import java.util.List;

public class RecyclerViewAdapterForTodo extends RecyclerView.Adapter<RecyclerViewAdapterForTodo.MyRecyclerViewHolder> {
    private List<ItemAdd> itemList;

    public RecyclerViewAdapterForTodo(List<ItemAdd> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewItemTodoLayoutBinding recyclerviewItemTodoLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_item_todo_layout, parent, false);
        return new MyRecyclerViewHolder(recyclerviewItemTodoLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        ItemAdd item = itemList.get(position);
        holder.recyclerviewItemTodoLayoutBinding.setTaskItem(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class MyRecyclerViewHolder extends RecyclerView.ViewHolder{
        RecyclerviewItemTodoLayoutBinding recyclerviewItemTodoLayoutBinding;
        public MyRecyclerViewHolder(@NonNull RecyclerviewItemTodoLayoutBinding itemView) {
            // getRoot() 返回的是布局文件的最外层UI视图
            super(itemView.getRoot());
            recyclerviewItemTodoLayoutBinding = itemView;
        }
    }
}
