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

    public static String EXTRA_FIRST_NAME = "com.demo.bradperkins.broadcastingcrud.PERSON_FIRST_NAME";
    public static String EXTRA_LAST_NAME = "com.demo.bradperkins.broadcastingcrud.PERSON_LAST_NAME";
    public static String EXTRA_AGE = "com.demo.bradperkins.broadcastingcrud.PERSON_AGE";



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
        EXTRA_FIRST_NAME = addFragment.firstNameET.getText().toString().trim();
        EXTRA_LAST_NAME = addFragment.lastNameET.getText().toString().trim();
        EXTRA_AGE = addFragment.personAgeET.getText().toString().trim();

        if (EXTRA_FIRST_NAME.isEmpty() || EXTRA_LAST_NAME.isEmpty() || EXTRA_AGE.isEmpty()) {
            Toast.makeText(this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
        } else {

            //SENDS DATA To RECEIVER
            sendToReceiver(EXTRA_FIRST_NAME, EXTRA_LAST_NAME, EXTRA_AGE);

            saveTheData(EXTRA_FIRST_NAME, EXTRA_LAST_NAME, EXTRA_AGE);
        }
        finish();
    }

    public void sendToReceiver(String first, String last, String age){
        Intent intent = new Intent(ACTION_SAVE_DATA);
        intent.putExtra("EXTRA_FIRST_NAME", first);
        intent.putExtra("EXTRA_LAST_NAME", last);
        intent.putExtra("EXTRA_AGE", age);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        this.sendBroadcast(intent);
    }



    public void saveTheData(String first, String last, String age){

        person = new Person(first, last, age);
        personList.add(person);
        FileHelper.writeData(personList, this);

    }

}

