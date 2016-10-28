package com.demo.bradperkins.broadcastingcrud.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.demo.bradperkins.broadcastingcrud.R;
import com.demo.bradperkins.broadcastingcrud.fragments.AddFragment;
import com.demo.bradperkins.broadcastingcrud.utilities.FileHelper;
import com.demo.bradperkins.broadcastingcrud.utilities.Person;


public class AddActivity extends AppCompatActivity {

    private static final String ADD_FRAGMENT = "add_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setTitle("Add Person");




        AddFragment savedFragment = (AddFragment) getSupportFragmentManager().findFragmentByTag(ADD_FRAGMENT);
//        getFragmentManager().beginTransaction().replace(R.id.add_placeHolder, savedFragment.newInstance()).commit();
//
        if(savedFragment == null) {
            AddFragment fragment = new AddFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.add_placeHolder, fragment, ADD_FRAGMENT);
            fragmentTransaction.commit();
        }


    }

    public void saveBtn(View view) {
        EditText firstNameET = (EditText) view.findViewById(R.id.firstNameET);
        EditText lastNameET = (EditText) view.findViewById(R.id.lastNameET);
        EditText personAgeET = (EditText) view.findViewById(R.id.ageET);

        String first = firstNameET.getText().toString().trim();
        String last = lastNameET.getText().toString().trim();
        String age = personAgeET.getText().toString().trim();

        if (first.isEmpty() || last.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
        } else {

            Person person = new Person(first, last, age);
            FileHelper.writeData(person, this);

            firstNameET.setText("");
            lastNameET.setText("");
            personAgeET.setText("");

            Toast.makeText(this, "New Person Added", Toast.LENGTH_SHORT).show();
        }

        finish();


    }
}
