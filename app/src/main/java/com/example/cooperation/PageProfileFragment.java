package com.example.cooperation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.cooperation.databinding.FragmentPageProfileBinding;
import com.example.cooperation.databing.FragmentViewProfileDataBinding;


public class PageProfileFragment extends Fragment {



    public PageProfileFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentPageProfileBinding fragmentPageProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_page_profile, container, false);

        FragmentViewProfileDataBinding fragmentViewProfileDataBinding = new FragmentViewProfileDataBinding(getContext());

        fragmentPageProfileBinding.setProfileEventHandler(new FragmentViewProfileDataBinding(getContext()));

        return fragmentPageProfileBinding.getRoot();
    }
}