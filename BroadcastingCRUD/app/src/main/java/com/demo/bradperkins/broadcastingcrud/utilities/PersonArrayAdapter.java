package com.demo.bradperkins.broadcastingcrud.utilities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.demo.bradperkins.broadcastingcrud.R;
import com.demo.bradperkins.broadcastingcrud.utilities.Person;

import java.util.List;

/**
 * Created by bradperkins on 10/27/16.
 */
public class PersonArrayAdapter extends ArrayAdapter<Person> {

    private Context context;
    private List<Person> objects;

    public PersonArrayAdapter(Context context, int resource, List<Person> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Person person = objects.get(position);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.person_listitem, null);

        TextView tv = (TextView) view.findViewById(R.id.tvPersonName);
        tv.setText(person.getFirstName());

        return view;
    }


}
