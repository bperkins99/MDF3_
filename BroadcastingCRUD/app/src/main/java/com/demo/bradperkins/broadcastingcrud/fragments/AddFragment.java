package com.demo.bradperkins.broadcastingcrud.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.demo.bradperkins.broadcastingcrud.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    EditText firstNameET, lastNameET, personAgeET;


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        firstNameET = (EditText) view.findViewById(R.id.firstNameET);
        lastNameET = (EditText) view.findViewById(R.id.lastNameET);
        personAgeET = (EditText) view.findViewById(R.id.ageET);


        return view;

    }

}
