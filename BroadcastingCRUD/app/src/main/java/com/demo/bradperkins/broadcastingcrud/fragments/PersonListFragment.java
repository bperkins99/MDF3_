package com.demo.bradperkins.broadcastingcrud.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.bradperkins.broadcastingcrud.utilities.FileHelper;
import com.demo.bradperkins.broadcastingcrud.utilities.Person;
import com.demo.bradperkins.broadcastingcrud.utilities.PersonArrayAdapter;
import com.demo.bradperkins.broadcastingcrud.utilities.PersonData;
import com.demo.bradperkins.broadcastingcrud.R;

import java.util.ArrayList;
import java.util.List;


public class PersonListFragment extends ListFragment {

    List<Person> personList;
    private OnPersonInterface activity;

    public interface OnPersonInterface {
        void onItemSelected(Person index);

    }

    public PersonListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateData();
    }

    @Override
    public void onResume() {
        super.onResume();
        personList = FileHelper.readData(getActivity());
        updateData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_list, container, false);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Person index = personList.get(position);
        activity.onItemSelected(index);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (OnPersonInterface) context;
    }


    public void updateData(){
        personList = FileHelper.readData(getActivity());
        PersonArrayAdapter adapter = new PersonArrayAdapter(getActivity(),
                R.layout.fragment_person_list,
                //adds the list data
                personList);

        setListAdapter(adapter);
    }

}
