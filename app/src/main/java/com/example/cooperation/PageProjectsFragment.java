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



//        RecyclerViewAdapterForProjects recyclerViewAdapterForProjects = new RecyclerViewAdapterForProjects(new RecyclerViewProjectsViewModel().getProjects(), new ProjectItemClick() {
//
//
//            @Override
//            public void onClicked(View view, Project project) {
//
//                MyRetrofit.InitInstance();
//
//                RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();
//
//                Call<ResponseBody> responseBodyCall = retrofitRequestInterface.projectGetCooperator(MyRetrofit.getToken(), project.getProjectId());
//
//                responseBodyCall.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.isSuccessful() && HttpStatus.OK.equals(response.body().getCode())){
//                            String cooperators = response.body().getData();
//                            Intent intent = new Intent(getContext(),PageProjectDetailsActivity.class);
//                            Bundle bundle = new Bundle();
//                            project.setCooperatorsList(cooperators);
//                            bundle.putSerializable("project_item", project);
//                            bundle.putString("cooperators",cooperators);
//                            intent.putExtras(bundle);
//                            getContext().startActivity(intent);
//                        }else {
//                            Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Toast.makeText(getContext(),"获取Cooperators失败！",Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });



//        fragmentPageProjectsBinding.recyclerviewProjects.setAdapter(recyclerViewAdapterForProjects);


        RecyclerViewProjectsViewModel recyclerViewProjectsViewModel = new RecyclerViewProjectsViewModel(fragmentPageProjectsBinding,getContext());
        recyclerViewProjectsViewModel.refreshRecyclerViewItems();

        // Inflate the layout for this fragment
        return fragmentPageProjectsBinding.getRoot();
    }
}