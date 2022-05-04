package com.example.cooperation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class FragmentPageItemDetails extends Fragment {


    public FragmentPageItemDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO 取出Bundle里的item，key：task_item
        return inflater.inflate(R.layout.fragment_page_item_details, container, false);
    }
}