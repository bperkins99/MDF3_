package com.demo.bradperkins.broadcastingcrud.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.bradperkins.broadcastingcrud.utilities.MyReceiver;
import com.demo.bradperkins.broadcastingcrud.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        getActivity().setTitle(MyReceiver.EXTRA_FIRST_NAME + " " + MyReceiver.EXTRA_LAST_NAME);

            TextView firstName = (TextView) view.findViewById(R.id.firstNameTV);
            firstName.setText(MyReceiver.EXTRA_FIRST_NAME);

            TextView lastName = (TextView) view.findViewById(R.id.lastNameTV);
            lastName.setText(MyReceiver.EXTRA_LAST_NAME);

            TextView age = (TextView) view.findViewById(R.id.ageTV);
            String ageString = String.valueOf(MyReceiver.EXTRA_AGE);
            age.setText(ageString);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }

}
