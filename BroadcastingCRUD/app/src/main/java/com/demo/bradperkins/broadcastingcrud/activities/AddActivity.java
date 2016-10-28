package com.demo.bradperkins.broadcastingcrud.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.demo.bradperkins.broadcastingcrud.R;
import com.demo.bradperkins.broadcastingcrud.fragments.AddFragment;
import com.demo.bradperkins.broadcastingcrud.utilities.FileHelper;
import com.demo.bradperkins.broadcastingcrud.utilities.Person;

import java.util.ArrayList;


public class AddActivity extends AppCompatActivity {

    private static final String ACTION_SAVE_DATA = "com.demo.bradperkins.broadcastingcrud.ACTION_SAVE_DATA";

    AddFragment addFragment;

    Person person;//
    ArrayList<Person> personList;///
    FileHelper fileHelper = new FileHelper();//////

    String firstName = "";
    String lastName = "";
    String personAge = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ////////
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.add_placeHolder, new AddFragment()).commit();
        }

        setTitle("Add Person");
        personList = fileHelper.readData(this);//

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_person) {
            personData();
        }
        return super.onOptionsItemSelected(item);
    }


    public void personData(){
        addFragment = (AddFragment) getSupportFragmentManager().findFragmentById(R.id.add_placeHolder);
        firstName = addFragment.firstNameET.getText().toString().trim();
        lastName = addFragment.lastNameET.getText().toString().trim();
        personAge = addFragment.personAgeET.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || personAge.isEmpty()) {
            Toast.makeText(this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
        } else {
            //TODO send data to MyReceiver

//            sendToReceiver(firstName, lastName, personAge);
            person = new Person(firstName, lastName, personAge);
            personList.add(person);
            fileHelper.writeData(personList, this);
            Toast.makeText(this, "New Person Added: " + firstName + lastName, Toast.LENGTH_SHORT).show();
        }

        finish();

    }

    public void sendToReceiver(String first, String last, String age){
        Intent intent = new Intent(ACTION_SAVE_DATA);
        intent.putExtra("first", first);
        intent.putExtra("last", last);
        intent.putExtra("age", age);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        this.sendBroadcast(intent);
    }

}

