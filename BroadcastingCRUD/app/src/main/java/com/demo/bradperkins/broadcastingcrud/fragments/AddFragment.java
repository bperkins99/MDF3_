package com.demo.bradperkins.broadcastingcrud.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.bradperkins.broadcastingcrud.R;
import com.demo.bradperkins.broadcastingcrud.activities.AddActivity;
import com.demo.bradperkins.broadcastingcrud.utilities.FileHelper;
import com.demo.bradperkins.broadcastingcrud.utilities.Person;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance() {
        Bundle args = new Bundle();
        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        return view;

    }

}
