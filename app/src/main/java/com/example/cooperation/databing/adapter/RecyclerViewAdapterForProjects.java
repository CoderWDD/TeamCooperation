package com.example.cooperation.databing.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooperation.R;
import com.example.cooperation.databinding.RecyclerviewProjectsLayoutBinding;
import com.example.cooperation.model.Project;

import java.util.List;

public class RecyclerViewAdapterForProjects extends RecyclerView.Adapter<RecyclerViewAdapterForProjects.MyRecyclerViewHolder> {

    private List<Project> projectList;

    public RecyclerViewAdapterForProjects(List<Project> projectList) {
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewProjectsLayoutBinding recyclerviewProjectsLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_projects_layout, parent, false);
        return new MyRecyclerViewHolder(recyclerviewProjectsLayoutBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        holder.recyclerviewProjectsLayoutBinding.setProjects(projectList.get(position));
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    class MyRecyclerViewHolder extends RecyclerView.ViewHolder{
        RecyclerviewProjectsLayoutBinding recyclerviewProjectsLayoutBinding;
        public MyRecyclerViewHolder(@NonNull RecyclerviewProjectsLayoutBinding itemView) {
            super(itemView.getRoot());
            recyclerviewProjectsLayoutBinding = itemView;
        }
    }
}
