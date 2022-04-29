package com.example.cooperation.databing.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooperation.R;
import com.example.cooperation.databinding.RecyclerviewProjectsLayoutBinding;
import com.example.cooperation.databing.click.ProjectItemClick;
import com.example.cooperation.model.Project;

import java.util.List;

public class RecyclerViewAdapterForProjects extends RecyclerView.Adapter<RecyclerViewAdapterForProjects.MyRecyclerViewHolder> {
    ProjectItemClick projectItemClick;
    private List<Project> projectList;

    public RecyclerViewAdapterForProjects(List<Project> projectList,ProjectItemClick projectItemClick) {
        this.projectList = projectList;
        this.projectItemClick = projectItemClick;
    }

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewProjectsLayoutBinding recyclerviewProjectsLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_projects_layout, parent, false);
        return new MyRecyclerViewHolder(recyclerviewProjectsLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        holder.bindViewWithProject(holder.recyclerviewProjectsLayoutBinding, projectList.get(position));
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    class MyRecyclerViewHolder extends RecyclerView.ViewHolder{
        private RecyclerviewProjectsLayoutBinding recyclerviewProjectsLayoutBinding;

        public MyRecyclerViewHolder(@NonNull RecyclerviewProjectsLayoutBinding itemView) {
            super(itemView.getRoot());
            recyclerviewProjectsLayoutBinding = itemView;
        }

        // 将点击事件与view链接起来
        public void bindViewWithProject(@NonNull RecyclerviewProjectsLayoutBinding recyclerviewProjectsLayoutBinding,Project project){
            recyclerviewProjectsLayoutBinding.setProjects(project);
            recyclerviewProjectsLayoutBinding.executePendingBindings();
            recyclerviewProjectsLayoutBinding.setOnProjectItemClicked(projectItemClick);
        }


    }
}
