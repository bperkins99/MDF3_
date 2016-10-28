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


    public EditText firstNameET;//
    public EditText lastNameET;//
    public EditText personAgeET;//

    ArrayList<Person> personList;//

    FileHelper fileHelper = new FileHelper();//

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

        firstNameET = (EditText)view.findViewById(R.id.firstNameET);//
        lastNameET = (EditText)view.findViewById(R.id.lastNameET);//
        personAgeET = (EditText)view.findViewById(R.id.ageET);//

        personList = fileHelper.readData(getActivity());//
        return view;
    }
}
